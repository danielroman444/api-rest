package EF.__FloresRoman.api_rest.Controller;
import EF.__FloresRoman.api_rest.Dto.ObraRegistroDto;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import EF.__FloresRoman.api_rest.Service.ObraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/obras")
@RequiredArgsConstructor
public class ObraController {
    private final ObraService obraService;
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @GetMapping
    public ResponseEntity<?> obtenerObras() {
        return ResponseEntity.ok(obraService.getAllObras());
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerObraPorId(@PathVariable Long id) {
        return ResponseEntity.ok(obraService.getObraById(id));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @PostMapping
    public ResponseEntity<GenericResponseDto> registrarObra(@Valid @RequestBody ObraRegistroDto obra) {
        return ResponseEntity.ok(obraService.createObra(obra));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto> actualizarObra(@PathVariable Long id,
                                                             @Valid @RequestBody ObraRegistroDto obra) {
        return ResponseEntity.ok(obraService.updateObra(id, obra));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarObra(@PathVariable Long id) {
        obraService.deleteObra(id);
        return ResponseEntity.noContent().build();
    }
}