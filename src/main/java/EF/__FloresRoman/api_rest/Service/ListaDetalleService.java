package EF.__FloresRoman.api_rest.Service;
import EF.__FloresRoman.api_rest.Dto.ListaDetalleDto;
import EF.__FloresRoman.api_rest.Dto.ListaDetalleRegistroDto;
import EF.__FloresRoman.api_rest.Exception.ResourceNotFoundException;
import EF.__FloresRoman.api_rest.Model.ListaDetalle;
import EF.__FloresRoman.api_rest.Model.TablaLista;
import EF.__FloresRoman.api_rest.Repository.ListaDetalleRepository;
import EF.__FloresRoman.api_rest.Repository.TablaListaRepository;
import EF.__FloresRoman.api_rest.Repository.Projection.ListaDetalleProjection;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ListaDetalleService {
    private final ListaDetalleRepository repository;
    private final TablaListaRepository tablaListaRepository;
    public List<ListaDetalleProjection> getAllDetalles() {
        return repository.findAllProjectedBy();
    }
    public ListaDetalleDto getDetalleById(Long id) {
        ListaDetalle detalle = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detalle no encontrado con ID: " + id));
        return new ListaDetalleDto(
                detalle.getIdlistadetalle(),
                detalle.getNombreEmpresa(),
                detalle.getNomObra(),
                detalle.getPiso(),
                detalle.getTablaLista().getIdtablalista(),
                detalle.getFecha(),
                detalle.getCostoUnitario(),
                detalle.getCostoTotal()
        );
    }
    public GenericResponseDto registrarDetalle(ListaDetalleRegistroDto dto) {
        TablaLista tablaLista = tablaListaRepository.findById(dto.getIdTablaLista())
                .orElseThrow(() -> new EntityNotFoundException("TablaLista no encontrada con ID: " + dto.getIdTablaLista()));
        ListaDetalle detalle = new ListaDetalle();
        detalle.setNombreEmpresa(dto.getNombreEmpresa());
        detalle.setNomObra(dto.getNomObra());
        detalle.setPiso(dto.getPiso());
        detalle.setTablaLista(tablaLista);
        detalle.setCostoUnitario(dto.getCostoUnitario());
        detalle.setCostoTotal(dto.getCostoUnitario() * tablaLista.getCantidad());
        // La fecha se asigna automáticamente por el método @PrePersist
        ListaDetalle guardado = repository.save(detalle);
        ListaDetalleDto responseDto = new ListaDetalleDto(
                guardado.getIdlistadetalle(),
                guardado.getNombreEmpresa(),
                guardado.getNomObra(),
                guardado.getPiso(),
                guardado.getTablaLista().getIdtablalista(),
                guardado.getFecha(),
                guardado.getCostoUnitario(),
                guardado.getCostoTotal()
        );

        return new GenericResponseDto("Detalle registrado correctamente", true, responseDto);
    }
    public GenericResponseDto actualizarDetalle(Long id, ListaDetalleRegistroDto dto) {
        ListaDetalle detalle = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ListaDetalle no encontrado con ID: " + id));
        TablaLista tablaLista = tablaListaRepository.findById(dto.getIdTablaLista())
                .orElseThrow(() -> new EntityNotFoundException("TablaLista no encontrada con ID: " + dto.getIdTablaLista()));
        detalle.setNombreEmpresa(dto.getNombreEmpresa());
        detalle.setNomObra(dto.getNomObra());
        detalle.setPiso(dto.getPiso());
        detalle.setTablaLista(tablaLista);
        detalle.setCostoUnitario(dto.getCostoUnitario());
        detalle.setCostoTotal(dto.getCostoUnitario() * tablaLista.getCantidad());
        // Nota: no se actualiza la fecha
        ListaDetalle actualizado = repository.save(detalle);
        ListaDetalleDto responseDto = new ListaDetalleDto(
                actualizado.getIdlistadetalle(),
                actualizado.getNombreEmpresa(),
                actualizado.getNomObra(),
                actualizado.getPiso(),
                actualizado.getTablaLista().getIdtablalista(),
                actualizado.getFecha(), // La fecha original se mantiene
                actualizado.getCostoUnitario(),
                actualizado.getCostoTotal()
        );
        return new GenericResponseDto("Detalle actualizado correctamente", true, responseDto);
    }
    public void eliminarDetalle(Long id) {
        ListaDetalle detalle = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detalle no encontrado con ID: " + id));
        repository.delete(detalle);
    }
}