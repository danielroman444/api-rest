package EF.__FloresRoman.api_rest.Repository;
import EF.__FloresRoman.api_rest.Model.Inventario;
import EF.__FloresRoman.api_rest.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByProducto(Producto producto);
}
