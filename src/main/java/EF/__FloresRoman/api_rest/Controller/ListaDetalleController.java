package EF.__FloresRoman.api_rest.Controller;
import EF.__FloresRoman.api_rest.Dto.ListaDetalleDto;
import EF.__FloresRoman.api_rest.Dto.ListaDetalleRegistroDto;
import EF.__FloresRoman.api_rest.Service.ListaDetalleService;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/listadetalle")
@RequiredArgsConstructor
public class ListaDetalleController {
    private final ListaDetalleService service;
    @GetMapping
    public ResponseEntity<?> listarDetalles() {
        return ResponseEntity.ok(service.getAllDetalles());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDetalleById(id));
    }
    @PostMapping
    public ResponseEntity<GenericResponseDto> registrarDetalle(@RequestBody ListaDetalleRegistroDto dto) {
        return ResponseEntity.ok(service.registrarDetalle(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto> actualizarDetalle(@PathVariable Long id, @RequestBody ListaDetalleRegistroDto dto) {
        return ResponseEntity.ok(service.actualizarDetalle(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDetalle(@PathVariable Long id) {
        service.eliminarDetalle(id);
        return ResponseEntity.ok("Detalle eliminado correctamente");
    }
}