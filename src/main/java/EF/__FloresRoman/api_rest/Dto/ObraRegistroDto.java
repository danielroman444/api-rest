package EF.__FloresRoman.api_rest.Dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public class ObraRegistroDto{
        @NotBlank(message = "El nombre de la empresa es obligatorio") //Que no sea nulo, vacio o no tenga espacios en blanco
        private String nomObra;
        @NotNull //que el valor no sea nulo
        private Integer piso;
        public String getNomObra() {return nomObra;}
        public void setNomObra(String nomObra) {this.nomObra = nomObra;}
        public Integer getPiso() {return piso;}
        public void setPiso(Integer piso) {this.piso = piso;}
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo o ingresa
//Este es el Dto de entrada