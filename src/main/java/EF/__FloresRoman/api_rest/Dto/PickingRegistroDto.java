package EF.__FloresRoman.api_rest.Dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data //Genera automaticamente setter, getter, toString, EqualsAndHashCode  y RequiredArgsConstructor
@NoArgsConstructor //genera automaticamente un constructor sin argumentos
@AllArgsConstructor //Genera automaticamente un constructor con todos los campos
public class PickingRegistroDto{
    @NotBlank(message = "El estado no puede estar vacío")
    @Pattern(
            regexp = "VACIADO|ENVIADO|RECIBIENDO|PICKING",
            message = "El estado debe ser VACIADO, ENVIADO, RECIBIENDO o PICKING"
    )
    private String estado;
    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo o ingresa
//Este es el Dto de entrada