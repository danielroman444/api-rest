package EF.__FloresRoman.api_rest.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
@Data
@AllArgsConstructor
public class InventarioDto {
    private Long idinventario;
    private Long idProduct;
    private Integer cantidadReal;
    private Integer cantidadReservada;
    private LocalDate fecha;
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo
//Este es el Dto de salido