package EF.__FloresRoman.api_rest.Controller;
import EF.__FloresRoman.api_rest.Dto.ClienteRegistroDto;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import EF.__FloresRoman.api_rest.Service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    @GetMapping
    public ResponseEntity<?> obtenerClientes() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.getClienteById(id));
    }
    @PostMapping
    public ResponseEntity<GenericResponseDto> registrarCliente(@Valid @RequestBody ClienteRegistroDto cliente) {
        return ResponseEntity.ok(clienteService.registrarCliente(cliente));
    }
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto> actualizarCliente(@PathVariable Long id,
                                                                @Valid @RequestBody ClienteRegistroDto cliente) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, cliente));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build(); // sin cuerpo, por ser 204
    }
}