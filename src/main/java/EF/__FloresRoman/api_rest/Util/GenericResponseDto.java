package EF.__FloresRoman.api_rest.Util;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class GenericResponseDto {
    private String message;
    private boolean success;
    private Object data;
    public GenericResponseDto(String message, boolean success, Object data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }
}