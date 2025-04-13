package EF.__FloresRoman.api_rest.Controller;
import EF.__FloresRoman.api_rest.Dto.ProductoDto;
import EF.__FloresRoman.api_rest.Dto.ProductoRegistroDto;
import EF.__FloresRoman.api_rest.Repository.Projection.ProductoProjection;
import EF.__FloresRoman.api_rest.Service.ProductoService;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @GetMapping
    public ResponseEntity<List<ProductoProjection>> listarTodos() {
        return ResponseEntity.ok(productoService.getAllProductos());
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.getProductoById(id));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @PostMapping
    public ResponseEntity<GenericResponseDto> registrar(@Valid @RequestBody ProductoRegistroDto dto) {
        return ResponseEntity.ok(productoService.registrarProducto(dto));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRegistroDto dto) {
        return ResponseEntity.ok(productoService.actualizarProducto(id, dto));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}