package EF.__FloresRoman.api_rest.Controller;
import EF.__FloresRoman.api_rest.Dto.InventarioDto;
import EF.__FloresRoman.api_rest.Dto.InventarioRegistroDto;
import EF.__FloresRoman.api_rest.Service.InventarioService;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {
    private final InventarioService service;
    @GetMapping
    public List<InventarioDto> listar() {
        return service.getAllInventarios();
    }
    @GetMapping("/{id}")
    public InventarioDto obtenerPorId(@PathVariable Long id) {
        return service.getInventarioById(id);
    }
    @PostMapping
    public ResponseEntity<GenericResponseDto> registrar(@RequestBody InventarioRegistroDto dto) {
        return ResponseEntity.ok(service.registrarInventario(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto> actualizar(@PathVariable Long id, @RequestBody InventarioRegistroDto dto) {
        return ResponseEntity.ok(service.actualizarInventario(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.deleteInventario(id);
        return ResponseEntity.noContent().build();
    }
}
