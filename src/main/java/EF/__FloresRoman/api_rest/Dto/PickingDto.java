package EF.__FloresRoman.api_rest.Dto;
import lombok.Data;
@Data //Genera automaticamente setter, getter, toString, EqualsAndHashCode  y RequiredArgsConstructor
public class PickingDto {
    private Long idPicking;
    private String estado;
    // Constructor para convertir la entidad Picking a DTO
    public PickingDto(Long idPicking, String estado) {
        this.idPicking = idPicking;
        this.estado = estado;
    }
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo
//Este es el Dto de salido