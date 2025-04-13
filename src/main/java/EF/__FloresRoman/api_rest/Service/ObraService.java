package EF.__FloresRoman.api_rest.Service;
import EF.__FloresRoman.api_rest.Dto.ObraDto;
import EF.__FloresRoman.api_rest.Dto.ObraRegistroDto;
import EF.__FloresRoman.api_rest.Repository.Projection.ObraProjection;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import EF.__FloresRoman.api_rest.Model.Obra;
import EF.__FloresRoman.api_rest.Repository.ObraRepository;
import EF.__FloresRoman.api_rest.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ObraService {
    private final ObraRepository obraRepository;
    // Obtener todas las obras (proyección)
    public List<ObraProjection> getAllObras() {
        return obraRepository.findAllProjectedBy();
    }
    // Obtener una obra por ID
    public ObraDto getObraById(Long id) {
        Obra obra = obraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Obra no encontrada con ID: " + id));
        return new ObraDto(obra.getIdobra(), obra.getNomObra(), obra.getPiso());
    }
    // Crear una nueva obra
    public GenericResponseDto createObra(ObraRegistroDto dto) {
        Obra nueva = new Obra();
        nueva.setNomObra(dto.getNomObra());
        nueva.setPiso(dto.getPiso());
        obraRepository.save(nueva);
        return new GenericResponseDto("Obra registrada correctamente", true, null);
    }
    // Actualizar una obra existente
    public GenericResponseDto updateObra(Long id, ObraRegistroDto dto) {
        Obra existente = obraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Obra no encontrada con ID: " + id));
        existente.setNomObra(dto.getNomObra());
        existente.setPiso(dto.getPiso());
        obraRepository.save(existente);
        return new GenericResponseDto("Obra actualizada correctamente", true, null);
    }
    // Eliminar una obra
    public GenericResponseDto deleteObra(Long id) {
        if (!obraRepository.existsById(id)) {
            throw new ResourceNotFoundException("Obra no encontrada con ID: " + id);
        }
        obraRepository.deleteById(id);
        return new GenericResponseDto("Obra eliminada correctamente", true, null);
    }
}