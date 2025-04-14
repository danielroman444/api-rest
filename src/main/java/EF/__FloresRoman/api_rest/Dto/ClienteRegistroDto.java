package EF.__FloresRoman.api_rest.Dto;
import jakarta.validation.constraints.NotBlank;
public class ClienteRegistroDto {
    @NotBlank(message = "El nombre de la empresa es obligatorio") //Que no sea nulo, vacio o no tenga espacios en blanco
    private String nombreEmpresa;
    public String getNombreEmpresa() {return nombreEmpresa;}
    public void setNombreEmpresa(String nombreEmpresa) {this.nombreEmpresa = nombreEmpresa;}
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo o ingresa
//Este es el Dto de entrada