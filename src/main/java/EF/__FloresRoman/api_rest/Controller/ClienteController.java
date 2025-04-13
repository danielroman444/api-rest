package EF.__FloresRoman.api_rest.Controller;
import EF.__FloresRoman.api_rest.Dto.ClienteRegistroDto;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import EF.__FloresRoman.api_rest.Service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @GetMapping
    public ResponseEntity<?> obtenerClientes() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.getClienteById(id));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @PostMapping
    public ResponseEntity<GenericResponseDto> registrarCliente(@Valid @RequestBody ClienteRegistroDto cliente) {
        return ResponseEntity.ok(clienteService.registrarCliente(cliente));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto> actualizarCliente(@PathVariable Long id,
                                                                @Valid @RequestBody ClienteRegistroDto cliente) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, cliente));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build(); // sin cuerpo, por ser 204
    }
}