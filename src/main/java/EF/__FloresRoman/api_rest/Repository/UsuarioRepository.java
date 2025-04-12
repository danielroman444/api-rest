package EF.__FloresRoman.api_rest.Repository;
import EF.__FloresRoman.api_rest.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}