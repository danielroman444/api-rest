package EF.__FloresRoman.api_rest.Dto;
public class ClienteDto {
    private Long idcliente;
    private String nombreEmpresa;
    public ClienteDto(Long idcliente, String nombreEmpresa) {
        this.idcliente = idcliente;
        this.nombreEmpresa = nombreEmpresa;
    }
    // Getters y setters si los necesitas
    public Long getIdcliente() {return idcliente;}
    public void setIdcliente(Long idcliente) {this.idcliente = idcliente;}
    public String getNombreEmpresa() {return nombreEmpresa;}
    public void setNombreEmpresa(String nombreEmpresa) {this.nombreEmpresa = nombreEmpresa;}
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo
//Este es el Dto de salido