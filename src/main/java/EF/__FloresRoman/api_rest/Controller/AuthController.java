package EF.__FloresRoman.api_rest.Controller;
import EF.__FloresRoman.api_rest.Model.Usuario;
import EF.__FloresRoman.api_rest.Repository.UsuarioRepository;
import EF.__FloresRoman.api_rest.Security.JwtService;
import EF.__FloresRoman.api_rest.Dto.LoginRequestDto;
import EF.__FloresRoman.api_rest.Dto.UsuarioSeguridadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public ResponseEntity<UsuarioSeguridadDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        // Autenticación con el AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );
        // Buscar usuario desde base de datos
        Usuario usuario = usuarioRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        // Generar el token incluyendo el rol
        String token = jwtService.generateToken(usuario);
        return ResponseEntity.ok(new UsuarioSeguridadDto(
                usuario.getUsername(),
                usuario.getRol().getNombre(), // Asegúrate de que el método getNombre() exista
                token
        ));
    }
}