package EF.__FloresRoman.api_rest.Dto;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class LoginRequestDto {
    private String username;
    private String password;
}