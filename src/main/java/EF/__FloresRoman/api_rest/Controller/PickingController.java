package EF.__FloresRoman.api_rest.Controller;
import EF.__FloresRoman.api_rest.Dto.PickingDto;
import EF.__FloresRoman.api_rest.Dto.PickingRegistroDto;
import EF.__FloresRoman.api_rest.Service.PickingService;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/picking")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PickingController {
    private final PickingService pickingService;
    // Listar todos los pickings
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANZA')")
    @GetMapping
    public ResponseEntity<List<PickingDto>> getAllPickings() {
        return ResponseEntity.ok(pickingService.getAllPickings());
    }
    // Obtener picking por ID
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANZA')")
    @GetMapping("/{id}")
    public ResponseEntity<PickingDto> getPickingById(@PathVariable Long id) {
        return ResponseEntity.ok(pickingService.getPickingById(id));
    }
    // Registrar nuevo picking
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANZA')")
    @PostMapping
    public ResponseEntity<GenericResponseDto> createPicking(@RequestBody @Valid PickingRegistroDto dto) {
        return ResponseEntity.ok(pickingService.registrarPicking(dto));
    }
    // Actualizar solo el estado
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANZA')")
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto> updateEstado(@PathVariable Long id, @RequestBody @Valid PickingRegistroDto dto) {
        return ResponseEntity.ok(pickingService.actualizarEstado(id, dto));
    }
    // Eliminar picking
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANZA')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePicking(@PathVariable Long id) {
        pickingService.eliminarPicking(id);
        return ResponseEntity.noContent().build();
    }
}