package EF.__FloresRoman.api_rest.Dto;
import lombok.Data;
@Data
public class ListaDetalleRegistroDto {
    private String nombreEmpresa;
    private String nomObra;
    private String piso;
    private Long idTablaLista;
    private Double costoUnitario;
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo o ingresa
//Este es el Dto de entrada