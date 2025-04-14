package EF.__FloresRoman.api_rest.Repository;
import EF.__FloresRoman.api_rest.Model.ListaDetalle;
import EF.__FloresRoman.api_rest.Repository.Projection.ListaDetalleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ListaDetalleRepository extends JpaRepository<ListaDetalle, Long> {
    List<ListaDetalleProjection> findAllProjectedBy();
}
//findAllProjectedBy se usa para recuperar ciertos campos de una entidad, no el objeto completo