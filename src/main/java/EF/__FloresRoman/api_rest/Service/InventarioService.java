package EF.__FloresRoman.api_rest.Service;
import EF.__FloresRoman.api_rest.Dto.InventarioDto;
import EF.__FloresRoman.api_rest.Dto.InventarioRegistroDto;
import EF.__FloresRoman.api_rest.Exception.ResourceNotFoundException;
import EF.__FloresRoman.api_rest.Model.Inventario;
import EF.__FloresRoman.api_rest.Model.Producto;
import EF.__FloresRoman.api_rest.Repository.InventarioRepository;
import EF.__FloresRoman.api_rest.Repository.ProductoRepository;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class InventarioService {
    private final InventarioRepository inventarioRepository;
    private final ProductoRepository productoRepository;
    public List<InventarioDto> getAllInventarios() {
        return inventarioRepository.findAll().stream().map(i ->
                new InventarioDto(
                        i.getIdinventario(),
                        i.getProducto().getIdProduct(),
                        i.getCantidadReal(),
                        i.getCantidadReservada(),
                        i.getFecha()
                )).collect(Collectors.toList());
    }
    public InventarioDto getInventarioById(Long id) {
        Inventario i = inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con ID: " + id));
        return new InventarioDto(
                i.getIdinventario(),
                i.getProducto().getIdProduct(),
                i.getCantidadReal(),
                i.getCantidadReservada(),
                i.getFecha()
        );
    }
    public GenericResponseDto registrarInventario(InventarioRegistroDto dto) {
        Producto producto = productoRepository.findById(dto.getIdProduct())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + dto.getIdProduct()));
        Inventario inventario = new Inventario();
        inventario.setProducto(producto);
        inventario.setCantidadReservada(dto.getCantidadReservada());
        inventario.setCantidadReal(dto.getCantidadReal() - dto.getCantidadReservada());
        Inventario guardado = inventarioRepository.save(inventario);
        return new GenericResponseDto("Inventario registrado correctamente", true,
                new InventarioDto(guardado.getIdinventario(), producto.getIdProduct(), guardado.getCantidadReal(),
                        guardado.getCantidadReservada(), guardado.getFecha()));
    }
    public GenericResponseDto actualizarInventario(Long id, InventarioRegistroDto dto) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con ID: " + id));
        Producto producto = productoRepository.findById(dto.getIdProduct())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + dto.getIdProduct()));
        inventario.setProducto(producto);
        inventario.setCantidadReservada(dto.getCantidadReservada());
        inventario.setCantidadReal(dto.getCantidadReal() - dto.getCantidadReservada());
        Inventario actualizado = inventarioRepository.save(inventario);
        return new GenericResponseDto("Inventario actualizado correctamente", true,
                new InventarioDto(actualizado.getIdinventario(), producto.getIdProduct(), actualizado.getCantidadReal(),
                        actualizado.getCantidadReservada(), actualizado.getFecha()));
    }
    public void deleteInventario(Long id) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado con ID: " + id));
        inventarioRepository.delete(inventario);
    }
}