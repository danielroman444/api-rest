package EF.__FloresRoman.api_rest.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor //Genera automaticamente un constructor con todos los campos
public class UsuarioSeguridadDto {
    private String username;
    private String rol;
    private String token;
}