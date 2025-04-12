package EF.__FloresRoman.api_rest.Repository;
import EF.__FloresRoman.api_rest.Model.Picking;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PickingRepository extends JpaRepository<Picking, Long> {
}