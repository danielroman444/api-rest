package EF.__FloresRoman.api_rest.Repository;
import EF.__FloresRoman.api_rest.Model.Obra;
import EF.__FloresRoman.api_rest.Repository.Projection.ObraProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ObraRepository extends JpaRepository<Obra, Long> {
    List<ObraProjection> findAllProjectedBy();
}
//findAllProjectedBy se usa para recuperar ciertos campos de una entidad, no el objeto completo