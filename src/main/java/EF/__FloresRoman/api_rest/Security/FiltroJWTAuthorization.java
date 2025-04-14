package EF.__FloresRoman.api_rest.Security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;
@Component
@RequiredArgsConstructor
public class FiltroJWTAuthorization extends OncePerRequestFilter {
    private final JwtService jwtService; //Inyecta JwtService
    private final DetalleUsuarioService detalleUsuarioService; //Inyecta DetalleUsarioService
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            if (jwtService.isTokenValid(jwt)) {
                String username = jwtService.extractUsername(jwt);
                String rol = jwtService.getRolFromToken(jwt);
                User user = new User(
                        username,
                        "",
                        Collections.singleton(() -> "ROLE_" + rol) // Spring Security usa "ROLE_" por convención
                );
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
// recibe 3 parametros request, response y filterChain (cadena de filtros) y luego cuyo filtro obtiene encabezado Autorization que tiene el Token jwt.
// verifica que el encabezado Autorization exista y comienza con bearer, Si el encabezado es valido, el token jwt se extrae eliminando el prefijo bearer (son de 7 caracteres)
// jwtService se utiliza para validar si el token es valido, el flujo continua
// Luego extrae informacion del toke: username y rol
// se crea un User con los detalles del usuario autenticado
//Se crea un objeto de autenticacion UsernamePasswordAuthenticationToken usando el usuario creado
//Se agregan de autenticacion como la IP de la solicitud.
//Se establece el objeto authToken en el SecurityContex, hasta aqui ya el usuario esta autenticado y esta autorizado para las solicitudes
//Se pasa la solicitud a la cadena de filtros de spring security.