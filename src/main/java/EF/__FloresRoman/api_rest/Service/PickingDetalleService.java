package EF.__FloresRoman.api_rest.Service;
import EF.__FloresRoman.api_rest.Dto.PickingDetalleDto;
import EF.__FloresRoman.api_rest.Dto.PickingDetalleRegistroDto;
import EF.__FloresRoman.api_rest.Exception.ResourceNotFoundException;
import EF.__FloresRoman.api_rest.Model.Picking;
import EF.__FloresRoman.api_rest.Model.PickingDetalle;
import EF.__FloresRoman.api_rest.Model.TablaLista;
import EF.__FloresRoman.api_rest.Repository.PickingDetalleRepository;
import EF.__FloresRoman.api_rest.Repository.PickingRepository;
import EF.__FloresRoman.api_rest.Repository.TablaListaRepository;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class PickingDetalleService {
    private final PickingDetalleRepository pickingDetalleRepository;
    private final TablaListaRepository tablaListaRepository;
    private final PickingRepository pickingRepository;
    // Listar todos los PickingDetalles
    public List<PickingDetalleDto> getAllPickingDetalles() {
        return pickingDetalleRepository.findAll().stream()
                .map(pd -> new PickingDetalleDto(
                        pd.getIdpickingdetalle(),
                        pd.getTablaLista().getIdtablalista(),
                        pd.getPicking().getIdPicking(),
                        pd.getFecha(),
                        pd.getHora()))
                .collect(Collectors.toList());
    }
    // Obtener PickingDetalle por ID
    public PickingDetalleDto getPickingDetalleById(Long id) {
        PickingDetalle pd = pickingDetalleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PickingDetalle no encontrado con ID: " + id));
        return new PickingDetalleDto(
                pd.getIdpickingdetalle(),
                pd.getTablaLista().getIdtablalista(),
                pd.getPicking().getIdPicking(),
                pd.getFecha(),
                pd.getHora());
    }
    // Registrar un nuevo PickingDetalle
    public GenericResponseDto createPickingDetalle(PickingDetalleRegistroDto dto) {
        TablaLista tablaLista = tablaListaRepository.findById(dto.getIdTablaLista())
                .orElseThrow(() -> new ResourceNotFoundException("TablaLista no encontrada con ID: " + dto.getIdTablaLista()));
        Picking picking = pickingRepository.findById(dto.getIdPicking())
                .orElseThrow(() -> new ResourceNotFoundException("Picking no encontrado con ID: " + dto.getIdPicking()));
        PickingDetalle pickingDetalle = new PickingDetalle();
        pickingDetalle.setTablaLista(tablaLista);
        pickingDetalle.setPicking(picking);
        PickingDetalle savedPickingDetalle = pickingDetalleRepository.save(pickingDetalle);
        return new GenericResponseDto("PickingDetalle registrado exitosamente", true, new PickingDetalleDto(
                savedPickingDetalle.getIdpickingdetalle(),
                tablaLista.getIdtablalista(),
                picking.getIdPicking(),
                savedPickingDetalle.getFecha(),
                savedPickingDetalle.getHora()));
    }
    // Actualizar PickingDetalle
    public GenericResponseDto updatePickingDetalle(Long id, PickingDetalleRegistroDto dto) {
        PickingDetalle existing = pickingDetalleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PickingDetalle no encontrado con ID: " + id));
        TablaLista tablaLista = tablaListaRepository.findById(dto.getIdTablaLista())
                .orElseThrow(() -> new ResourceNotFoundException("TablaLista no encontrada con ID: " + dto.getIdTablaLista()));
        Picking picking = pickingRepository.findById(dto.getIdPicking())
                .orElseThrow(() -> new ResourceNotFoundException("Picking no encontrado con ID: " + dto.getIdPicking()));
        existing.setTablaLista(tablaLista);
        existing.setPicking(picking);
        PickingDetalle updatedPickingDetalle = pickingDetalleRepository.save(existing);
        return new GenericResponseDto("PickingDetalle actualizado correctamente", true, new PickingDetalleDto(
                updatedPickingDetalle.getIdpickingdetalle(),
                tablaLista.getIdtablalista(),
                picking.getIdPicking(),
                updatedPickingDetalle.getFecha(),
                updatedPickingDetalle.getHora()));
    }
    // Eliminar PickingDetalle
    public GenericResponseDto deletePickingDetalle(Long id) {
        PickingDetalle pickingDetalle = pickingDetalleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PickingDetalle no encontrado con ID: " + id));

        pickingDetalleRepository.delete(pickingDetalle);
        return new GenericResponseDto("PickingDetalle eliminado correctamente", true, null);
    }
}