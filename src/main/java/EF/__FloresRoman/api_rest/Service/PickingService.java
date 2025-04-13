package EF.__FloresRoman.api_rest.Service;
import EF.__FloresRoman.api_rest.Dto.PickingDto;
import EF.__FloresRoman.api_rest.Dto.PickingRegistroDto;
import EF.__FloresRoman.api_rest.Model.Picking;
import EF.__FloresRoman.api_rest.Repository.PickingRepository;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PickingService {
    private final PickingRepository pickingRepository;
    // Registrar nuevo picking
    public GenericResponseDto registrarPicking(PickingRegistroDto dto) {
        Picking nuevo = new Picking();
        nuevo.setEstado(dto.getEstado());
        Picking guardado = pickingRepository.save(nuevo);
        PickingDto responseDto = new PickingDto(guardado.getIdPicking(), guardado.getEstado());
        GenericResponseDto response = new GenericResponseDto();
        response.setMessage("Picking registrado correctamente");
        response.setSuccess(true);
        response.setData(responseDto);
        return response;
    }
    // Actualizar solo el estado del picking
    public GenericResponseDto actualizarEstado(Long id, PickingRegistroDto dto) {
        Picking existente = pickingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Picking no encontrado con ID: " + id));
        existente.setEstado(dto.getEstado());
        Picking actualizado = pickingRepository.save(existente);
        PickingDto responseDto = new PickingDto(actualizado.getIdPicking(), actualizado.getEstado());
        GenericResponseDto response = new GenericResponseDto();
        response.setMessage("Estado de Picking actualizado correctamente");
        response.setSuccess(true);
        response.setData(responseDto);
        return response;
    }
    // Obtener todos
    public List<PickingDto> getAllPickings() {
        return pickingRepository.findAll().stream()
                .map(p -> new PickingDto(p.getIdPicking(), p.getEstado()))
                .toList();
    }
    // Obtener por ID
    public PickingDto getPickingById(Long id) {
        Picking picking = pickingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Picking no encontrado con ID: " + id));
        return new PickingDto(picking.getIdPicking(), picking.getEstado());
    }
    // Eliminar
    public void eliminarPicking(Long id) {
        Picking picking = pickingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Picking no encontrado con ID: " + id));
        pickingRepository.delete(picking);
    }
}