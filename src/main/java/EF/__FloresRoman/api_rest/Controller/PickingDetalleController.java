package EF.__FloresRoman.api_rest.Controller;
import EF.__FloresRoman.api_rest.Dto.PickingDetalleDto;
import EF.__FloresRoman.api_rest.Dto.PickingDetalleRegistroDto;
import EF.__FloresRoman.api_rest.Service.PickingDetalleService;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/picking-detalle")
@RequiredArgsConstructor
public class PickingDetalleController {
    private final PickingDetalleService pickingDetalleService;
    // Obtener todos los PickingDetalles
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANZA')")
    @GetMapping
    public List<PickingDetalleDto> getAllPickingDetalles() {
        return pickingDetalleService.getAllPickingDetalles();
    }
    // Obtener un PickingDetalle por ID
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANZA')")
    @GetMapping("/{id}")
    public PickingDetalleDto getPickingDetalleById(@PathVariable Long id) {
        return pickingDetalleService.getPickingDetalleById(id);
    }
    // Crear un nuevo PickingDetalle
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANZA')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponseDto createPickingDetalle(@RequestBody PickingDetalleRegistroDto dto) {
        return pickingDetalleService.createPickingDetalle(dto);
    }
    // Actualizar un PickingDetalle
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANZA')")
    @PutMapping("/{id}")
    public GenericResponseDto updatePickingDetalle(@PathVariable Long id, @RequestBody PickingDetalleRegistroDto dto) {
        return pickingDetalleService.updatePickingDetalle(id, dto);
    }
    // Eliminar un PickingDetalle
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANZA')")
    @DeleteMapping("/{id}")
    public GenericResponseDto deletePickingDetalle(@PathVariable Long id) {
        return pickingDetalleService.deletePickingDetalle(id);
    }
}