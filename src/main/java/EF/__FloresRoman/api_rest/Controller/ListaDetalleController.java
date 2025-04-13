package EF.__FloresRoman.api_rest.Controller;
import EF.__FloresRoman.api_rest.Dto.ListaDetalleRegistroDto;
import EF.__FloresRoman.api_rest.Service.ListaDetalleService;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/listadetalle")
@RequiredArgsConstructor
public class ListaDetalleController {
    private final ListaDetalleService service;
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @GetMapping
    public ResponseEntity<?> listarDetalles() {
        return ResponseEntity.ok(service.getAllDetalles());
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDetalleById(id));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @PostMapping
    public ResponseEntity<GenericResponseDto> registrarDetalle(@RequestBody ListaDetalleRegistroDto dto) {
        return ResponseEntity.ok(service.registrarDetalle(dto));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto> actualizarDetalle(@PathVariable Long id, @RequestBody ListaDetalleRegistroDto dto) {
        return ResponseEntity.ok(service.actualizarDetalle(id, dto));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDetalle(@PathVariable Long id) {
        service.eliminarDetalle(id);
        return ResponseEntity.ok("Detalle eliminado correctamente");
    }
}