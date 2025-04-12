package EF.__FloresRoman.api_rest.Repository;
import EF.__FloresRoman.api_rest.Model.TablaLista;
import EF.__FloresRoman.api_rest.Repository.Projection.TablaListaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TablaListaRepository extends JpaRepository<TablaLista, Long> {
    List<TablaListaProjection> findAllProjectedBy();
}