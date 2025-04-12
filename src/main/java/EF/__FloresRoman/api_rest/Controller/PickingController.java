package EF.__FloresRoman.api_rest.Controller;
import EF.__FloresRoman.api_rest.Dto.PickingDto;
import EF.__FloresRoman.api_rest.Dto.PickingRegistroDto;
import EF.__FloresRoman.api_rest.Service.PickingService;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/picking")
@RequiredArgsConstructor
public class PickingController {
    private final PickingService pickingService;
    // Listar todos los pickings
    @GetMapping
    public ResponseEntity<List<PickingDto>> getAllPickings() {
        return ResponseEntity.ok(pickingService.getAllPickings());
    }
    // Obtener picking por ID
    @GetMapping("/{id}")
    public ResponseEntity<PickingDto> getPickingById(@PathVariable Long id) {
        return ResponseEntity.ok(pickingService.getPickingById(id));
    }
    // Registrar nuevo picking
    @PostMapping
    public ResponseEntity<GenericResponseDto> createPicking(@RequestBody @Valid PickingRegistroDto dto) {
        return ResponseEntity.ok(pickingService.registrarPicking(dto));
    }
    // Actualizar solo el estado
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto> updateEstado(@PathVariable Long id, @RequestBody @Valid PickingRegistroDto dto) {
        return ResponseEntity.ok(pickingService.actualizarEstado(id, dto));
    }
    // Eliminar picking
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePicking(@PathVariable Long id) {
        pickingService.eliminarPicking(id);
        return ResponseEntity.noContent().build();
    }
}