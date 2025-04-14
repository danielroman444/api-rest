package EF.__FloresRoman.api_rest.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final FiltroJWTAuthorization filtroJWTAuthorization;
    private final DetalleUsuarioService detalleUsuarioService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        //Rutas públicas sin autenticación (permitAll)
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/api/productos/**").permitAll()
                        .requestMatchers("/api/clientes/**").permitAll()
                        .requestMatchers("/api/obras/**").permitAll()
                        .requestMatchers("/api/picking/**").permitAll()
                        //Otras rutas necesitan autenticación JWT
                        .anyRequest().authenticated()
                )
                .addFilterBefore(filtroJWTAuthorization, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    //csrf().disable() desactiva proteccion CSRF
    //sessionManagement indica que no se usan sesiones
    //requestMatchers define rutas publicas
    //anyRequest().authenticated() todas las demas rutas requieren token
    //addFilterBefore añade filtro JWT para validar el tokem
    @Bean //para que gestione esta instancia y usarla en cualquier parte
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //Sirve para codificar contraseñas antes de guardarlas en la base de datos y compararlas al momento el login
    @Bean //para que gestione esta instancia y usarla en cualquier parte
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    //Permite usar el AuthenticationManager para controlar el inicio de sesion sin depender del formulario.
}