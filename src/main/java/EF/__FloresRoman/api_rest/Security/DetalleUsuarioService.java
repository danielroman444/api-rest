package EF.__FloresRoman.api_rest.Security;
import EF.__FloresRoman.api_rest.Model.Usuario;
import EF.__FloresRoman.api_rest.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
@Service //Marca una clase como servicio
@RequiredArgsConstructor //Reduce el codigo repititivo, como la creacion de construcctores,setter y getter
public class DetalleUsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository; //Se esta inyectando el UsuarioRepository
    @Override //Sobreescribe correctamente un metodo de la clase padre
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                Collections.singleton(() -> "ROLE_" + usuario.getRol().getNombre())
        );
    }
}
// public UserDetails loadUserByUsername
//busca un usuario en la base de datos usando el repositorio y nombre de usuario, caso no encuentre un usuario con ese nombre
//lanza una excepcion de tipo UsernameNotFoundException con un mensaje
//Caso encuentre el usuario representa los datos encontrados