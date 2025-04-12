package EF.__FloresRoman.api_rest.Controller;
import EF.__FloresRoman.api_rest.Dto.PickingDetalleDto;
import EF.__FloresRoman.api_rest.Dto.PickingDetalleRegistroDto;
import EF.__FloresRoman.api_rest.Service.PickingDetalleService;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/picking-detalle")
@RequiredArgsConstructor
public class PickingDetalleController {
    private final PickingDetalleService pickingDetalleService;
    // Obtener todos los PickingDetalles
    @GetMapping
    public List<PickingDetalleDto> getAllPickingDetalles() {
        return pickingDetalleService.getAllPickingDetalles();
    }
    // Obtener un PickingDetalle por ID
    @GetMapping("/{id}")
    public PickingDetalleDto getPickingDetalleById(@PathVariable Long id) {
        return pickingDetalleService.getPickingDetalleById(id);
    }
    // Crear un nuevo PickingDetalle
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseDto createPickingDetalle(@RequestBody PickingDetalleRegistroDto dto) {
        return pickingDetalleService.createPickingDetalle(dto);
    }
    // Actualizar un PickingDetalle
    @PutMapping("/{id}")
    public GenericResponseDto updatePickingDetalle(@PathVariable Long id, @RequestBody PickingDetalleRegistroDto dto) {
        return pickingDetalleService.updatePickingDetalle(id, dto);
    }
    // Eliminar un PickingDetalle
    @DeleteMapping("/{id}")
    public GenericResponseDto deletePickingDetalle(@PathVariable Long id) {
        return pickingDetalleService.deletePickingDetalle(id);
    }
}