package EF.__FloresRoman.api_rest.Dto;
import lombok.Data;
@Data
public class TablaListaRegistroDto {
    private Long idProduct;
    private Integer cantidad;
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo o ingresa
//Este es el Dto de entrada