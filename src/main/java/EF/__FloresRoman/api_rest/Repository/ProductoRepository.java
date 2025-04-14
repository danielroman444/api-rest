package EF.__FloresRoman.api_rest.Repository;
import EF.__FloresRoman.api_rest.Model.Producto;
import EF.__FloresRoman.api_rest.Repository.Projection.ProductoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<ProductoProjection> findAllProjectedBy();
    boolean existsByCodigo(String codigo);
}
//findAllProjectedBy se usa para recuperar ciertos campos de una entidad, no el objeto completo
//existsByCodigo genera una consulta derivada automaticamente a partir del nombre del metodo y sirve
//para verificar si existe un registro en la base de datos con cierto valor en el campo