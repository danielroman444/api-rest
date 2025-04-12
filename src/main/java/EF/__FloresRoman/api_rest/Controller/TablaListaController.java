package EF.__FloresRoman.api_rest.Controller;
import EF.__FloresRoman.api_rest.Dto.TablaListaDto;
import EF.__FloresRoman.api_rest.Dto.TablaListaRegistroDto;
import EF.__FloresRoman.api_rest.Service.TablaListaService;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/tabla-lista")
@RequiredArgsConstructor
public class TablaListaController {
    private final TablaListaService tablaListaService;
    @GetMapping
    public ResponseEntity<List<TablaListaDto>> getAll() {
        return ResponseEntity.ok(tablaListaService.getAllTablaListas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TablaListaDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tablaListaService.getTablaListaById(id));
    }
    @PostMapping
    public ResponseEntity<GenericResponseDto> create(@RequestBody TablaListaRegistroDto dto) {
        return ResponseEntity.ok(tablaListaService.createTablaLista(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto> update(@PathVariable Long id, @RequestBody TablaListaRegistroDto dto) {
        return ResponseEntity.ok(tablaListaService.updateTablaLista(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDto> delete(@PathVariable Long id) {
        GenericResponseDto response = tablaListaService.deleteTablaLista(id);
        return ResponseEntity.ok(response);
    }
}