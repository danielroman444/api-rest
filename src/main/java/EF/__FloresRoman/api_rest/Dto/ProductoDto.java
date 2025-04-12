package EF.__FloresRoman.api_rest.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ProductoDto {
    private Long idProduct;
    private String codigo;
    private String descripcion;
    private Double peso;
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo
//Este es el Dto de salido