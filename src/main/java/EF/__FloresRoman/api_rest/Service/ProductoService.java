package EF.__FloresRoman.api_rest.Service;
import EF.__FloresRoman.api_rest.Dto.ProductoDto;
import EF.__FloresRoman.api_rest.Dto.ProductoRegistroDto;
import EF.__FloresRoman.api_rest.Model.Producto;
import EF.__FloresRoman.api_rest.Repository.ProductoRepository;
import EF.__FloresRoman.api_rest.Repository.Projection.ProductoProjection;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;
    public List<ProductoProjection> getAllProductos() {
        return productoRepository.findAllProjectedBy();
    }
    public ProductoDto getProductoById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
        return new ProductoDto(producto.getIdProduct(), producto.getCodigo(), producto.getDescripcion(), producto.getPeso());
    }
    public GenericResponseDto registrarProducto(ProductoRegistroDto dto) {
        if (productoRepository.existsByCodigo(dto.getCodigo())) {
            return new GenericResponseDto("Ya existe un producto con ese código", false, null);
        }
        Producto nuevo = new Producto();
        nuevo.setCodigo(dto.getCodigo());
        nuevo.setDescripcion(dto.getDescripcion());
        nuevo.setPeso(dto.getPeso());
        Producto guardado = productoRepository.save(nuevo);
        ProductoDto responseDto = new ProductoDto(
                guardado.getIdProduct(), guardado.getCodigo(), guardado.getDescripcion(), guardado.getPeso());
        return new GenericResponseDto("Producto registrado exitosamente", true, responseDto);
    }
    public GenericResponseDto actualizarProducto(Long id, ProductoRegistroDto dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
        if (!producto.getCodigo().equals(dto.getCodigo()) && productoRepository.existsByCodigo(dto.getCodigo())) {
            return new GenericResponseDto("Ya existe otro producto con ese código", false, null);
        }
        producto.setCodigo(dto.getCodigo());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPeso(dto.getPeso());
        Producto actualizado = productoRepository.save(producto);
        ProductoDto responseDto = new ProductoDto(
                actualizado.getIdProduct(), actualizado.getCodigo(), actualizado.getDescripcion(), actualizado.getPeso());
        return new GenericResponseDto("Producto actualizado correctamente", true, responseDto);
    }
    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
        productoRepository.delete(producto);
    }
}