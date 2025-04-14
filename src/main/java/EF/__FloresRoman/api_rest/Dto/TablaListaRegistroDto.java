package EF.__FloresRoman.api_rest.Dto;
import lombok.Data;
@Data //Genera automaticamente setter, getter, toString, EqualsAndHashCode  y RequiredArgsConstructor
public class TablaListaRegistroDto {
    private Long idProduct;
    private Integer cantidad;
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo o ingresa
//Este es el Dto de entrada