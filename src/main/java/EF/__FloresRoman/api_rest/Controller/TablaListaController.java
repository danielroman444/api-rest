package EF.__FloresRoman.api_rest.Controller;
import EF.__FloresRoman.api_rest.Dto.TablaListaDto;
import EF.__FloresRoman.api_rest.Dto.TablaListaRegistroDto;
import EF.__FloresRoman.api_rest.Service.TablaListaService;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/tabla-lista")
@RequiredArgsConstructor
public class TablaListaController {
    private final TablaListaService tablaListaService;
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @GetMapping
    public ResponseEntity<List<TablaListaDto>> getAll() {
        return ResponseEntity.ok(tablaListaService.getAllTablaListas());
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @GetMapping("/{id}")
    public ResponseEntity<TablaListaDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tablaListaService.getTablaListaById(id));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @PostMapping
    public ResponseEntity<GenericResponseDto> create(@RequestBody TablaListaRegistroDto dto) {
        return ResponseEntity.ok(tablaListaService.createTablaLista(dto));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto> update(@PathVariable Long id, @RequestBody TablaListaRegistroDto dto) {
        return ResponseEntity.ok(tablaListaService.updateTablaLista(id, dto));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'INGENIERO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDto> delete(@PathVariable Long id) {
        GenericResponseDto response = tablaListaService.deleteTablaLista(id);
        return ResponseEntity.ok(response);
    }
}