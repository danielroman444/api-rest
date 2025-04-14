package EF.__FloresRoman.api_rest.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
@Data //Genera automaticamente setter, getter, toString, EqualsAndHashCode  y RequiredArgsConstructor
@AllArgsConstructor //Genera automaticamente un constructor con todos los campos
public class PickingDetalleDto {
    private Long idpickingdetalle;
    private Long idTablaLista;
    private Long idPicking;
    private LocalDate fecha;
    private LocalTime hora;
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo
//Este es el Dto de salido