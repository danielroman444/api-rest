package EF.__FloresRoman.api_rest.Security;
import EF.__FloresRoman.api_rest.Model.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    private static final long EXPIRATION_MS = 1000 * 60 * 60; // la expiracion de um jwt sera 1 hora despues de su emision
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());} //para obtener la clave de firma que certifica que esta no ha sido manipulado y proviene de una fuente confiable
    public String generateToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .claim("role", usuario.getRol().getNombre()) // Agregamos el rol
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    //Metodo para generar el json web token
    //setSubject almacena la identidad del usuario
    //claim datos adicionales que se puede incluir dentri dek jwt
    //setIssuedAt establece la fecha que fue emitido
    //setExpiration establece la fecha de expiracion
    //signWith firma el token utilizado
    //getSigningKey un metodo que obtiene la clave secreta que se usara para firmar el token
    //compact genera el token con los datos que se han configurado
    public boolean isTokenValid(String token) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
    //verifica su firma y su estructura interna, si no lanza ninguna excepcion entonces retorna TRUE
    //caso contrario sera FALSE
    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }
    public String getRolFromToken(String token) {
        return parseToken(token).getBody().get("rol", String.class);
    }
    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }
    //extractUsername lee el nombre del usuario
    //getRolFromToken lee el rol del usuario
    //parseToken verifica y decodifica el token completo
}