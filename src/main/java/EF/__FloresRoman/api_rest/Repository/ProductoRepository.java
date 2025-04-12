package EF.__FloresRoman.api_rest.Repository;
import EF.__FloresRoman.api_rest.Model.Producto;
import EF.__FloresRoman.api_rest.Repository.Projection.ProductoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<ProductoProjection> findAllProjectedBy();
    boolean existsByCodigo(String codigo);
}
