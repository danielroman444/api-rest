package EF.__FloresRoman.api_rest.Model;
import jakarta.persistence.*;
@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String nombreEmpresa;
    // Getters y Setters
    public Long getIdCliente() {return idCliente;}
    public void setIdCliente(Long idCliente) {this.idCliente = idCliente;}
    public String getNombreEmpresa() {return nombreEmpresa;}
    public void setNombreEmpresa(String nombreEmpresa) {this.nombreEmpresa = nombreEmpresa;}
}