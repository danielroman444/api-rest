package EF.__FloresRoman.api_rest.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
@Data //Genera automaticamente setter, getter, toString, EqualsAndHashCode  y RequiredArgsConstructor
@AllArgsConstructor //Genera automaticamente un constructor con todos los campos
public class ListaDetalleDto {
    private Long idlistadetalle;
    private String nombreEmpresa;
    private String nomObra;
    private String piso;
    private Long idtablalista;
    private LocalDate fecha;
    private Double costoUnitario;
    private Double costoTotal;
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo
//Este es el Dto de salido