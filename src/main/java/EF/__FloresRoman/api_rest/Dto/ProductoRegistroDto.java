package EF.__FloresRoman.api_rest.Dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class ProductoRegistroDto {
    @NotBlank(message = "El código es obligatorio")
    private String codigo;
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
    @NotNull(message = "El peso es obligatorio")
    private Double peso;
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo o ingresa
//Este es el Dto de entrada