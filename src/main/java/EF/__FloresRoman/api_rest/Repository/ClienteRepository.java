package EF.__FloresRoman.api_rest.Repository;
import EF.__FloresRoman.api_rest.Model.Cliente;
import EF.__FloresRoman.api_rest.Repository.Projection.ClienteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c.idCliente AS id, c.nombreEmpresa AS nombreEmpresa FROM Cliente c")
    List<ClienteProjection> findAllProjectedBy();
    @Query("SELECT c.idCliente AS id, c.nombreEmpresa AS nombreEmpresa FROM Cliente c WHERE c.idCliente = :id")
    Optional<ClienteProjection> findProjectedById(@Param("id") Long id);
}
//selecciona el id de la entidad cliente le da el alias id
//selecciona el nombreEmpresa deja el mismo nombre con el alias
//Se trabaja con la tabla cliente y busca solo el cliente cuyo id coincida con el parametro id