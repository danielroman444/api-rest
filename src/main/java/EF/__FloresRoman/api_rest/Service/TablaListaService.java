package EF.__FloresRoman.api_rest.Service;
import EF.__FloresRoman.api_rest.Dto.TablaListaDto;
import EF.__FloresRoman.api_rest.Dto.TablaListaRegistroDto;
import EF.__FloresRoman.api_rest.Exception.ResourceNotFoundException;
import EF.__FloresRoman.api_rest.Model.Inventario;
import EF.__FloresRoman.api_rest.Model.Producto;
import EF.__FloresRoman.api_rest.Model.TablaLista;
import EF.__FloresRoman.api_rest.Repository.InventarioRepository;
import EF.__FloresRoman.api_rest.Repository.ProductoRepository;
import EF.__FloresRoman.api_rest.Repository.TablaListaRepository;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class TablaListaService {
    private final TablaListaRepository tablaListaRepository;
    private final ProductoRepository productoRepository;
    private final InventarioRepository inventarioRepository;
    // Listar todas
    public List<TablaListaDto> getAllTablaListas() {
        return tablaListaRepository.findAll().stream()
                .map(tl -> new TablaListaDto(
                        tl.getIdtablalista(),
                        tl.getProducto().getIdProduct(),
                        tl.getCantidad(),
                        tl.getPesoNeto()))
                .collect(Collectors.toList());
    }
    // Obtener por ID
    public TablaListaDto getTablaListaById(Long id) {
        TablaLista tl = tablaListaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TablaLista no encontrada con ID: " + id));
        return new TablaListaDto(
                tl.getIdtablalista(),
                tl.getProducto().getIdProduct(),
                tl.getCantidad(),
                tl.getPesoNeto());
    }
    // Registrar
    public GenericResponseDto createTablaLista(TablaListaRegistroDto dto) {
        Producto producto = productoRepository.findById(dto.getIdProduct())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + dto.getIdProduct()));

        TablaLista tablaLista = new TablaLista();
        tablaLista.setProducto(producto);
        tablaLista.setCantidad(dto.getCantidad());
        tablaLista.setPesoNeto(producto.getPeso() * dto.getCantidad());

        TablaLista guardado = tablaListaRepository.save(tablaLista);

        // ACTUALIZAR INVENTARIO
        Inventario inventario = inventarioRepository.findByProducto(producto).orElse(null);
        if (inventario != null) {
            int nuevaReservada = inventario.getCantidadReservada() + dto.getCantidad();
            int nuevaReal = inventario.getCantidadReal() - dto.getCantidad();

            inventario.setCantidadReservada(nuevaReservada);
            inventario.setCantidadReal(nuevaReal);
            inventarioRepository.save(inventario);
        } else {
            Inventario nuevoInventario = new Inventario();
            nuevoInventario.setProducto(producto);
            nuevoInventario.setCantidadReservada(dto.getCantidad());
            nuevoInventario.setCantidadReal(0); // O establecer un valor inicial si aplica
            inventarioRepository.save(nuevoInventario);
        }

        return new GenericResponseDto("Tabla lista registrada con éxito", true,
                new TablaListaDto(guardado.getIdtablalista(), producto.getIdProduct(),
                        guardado.getCantidad(), guardado.getPesoNeto()));
    }
    // Actualizar cantidad (y peso_neto)
    public GenericResponseDto updateTablaLista(Long id, TablaListaRegistroDto dto) {
        TablaLista existente = tablaListaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TablaLista no encontrada con ID: " + id));
        Producto producto = productoRepository.findById(dto.getIdProduct())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + dto.getIdProduct()));
        existente.setProducto(producto);
        existente.setCantidad(dto.getCantidad());
        // peso_neto se recalcula con @PreUpdate
        TablaLista actualizada = tablaListaRepository.save(existente);
        TablaListaDto responseDto = new TablaListaDto(
                actualizada.getIdtablalista(),
                producto.getIdProduct(),
                actualizada.getCantidad(),
                actualizada.getPesoNeto());
        GenericResponseDto response = new GenericResponseDto();
        response.setMessage("TablaLista actualizada correctamente");
        response.setSuccess(true);
        response.setData(responseDto);
        return response;
    }
    // Eliminar
    public GenericResponseDto deleteTablaLista(Long id) {
        TablaLista tablaLista = tablaListaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TablaLista no encontrada con ID: " + id));
        Producto producto = tablaLista.getProducto();
        int cantidadAEliminar = tablaLista.getCantidad();
        // Eliminar la entrada de TablaLista
        tablaListaRepository.delete(tablaLista);
        // ACTUALIZAR INVENTARIO
        Inventario inventario = inventarioRepository.findByProducto(producto)
                .orElseThrow(() -> new ResourceNotFoundException("Inventario no encontrado para el producto ID: " + producto.getIdProduct()));
        int nuevaReservada = inventario.getCantidadReservada() - cantidadAEliminar;
        int nuevaReal = inventario.getCantidadReal() + cantidadAEliminar;
        inventario.setCantidadReservada(Math.max(nuevaReservada, 0)); // evitar negativos
        inventario.setCantidadReal(nuevaReal);
        inventarioRepository.save(inventario);

        return new GenericResponseDto("Registro eliminado y stock actualizado", true, null);
    }
}