package EF.__FloresRoman.api_rest.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data //Genera automaticamente setter, getter, toString, EqualsAndHashCode  y RequiredArgsConstructor
@AllArgsConstructor //Genera automaticamente un constructor con todos los campos
public class TablaListaDto {
    private Long idtablalista;
    private Long idProduct;
    private Integer cantidad;
    private Double pesoNeto;
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo
//Este es el Dto de salida