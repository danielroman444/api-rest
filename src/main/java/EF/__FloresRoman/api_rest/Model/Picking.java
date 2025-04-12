package EF.__FloresRoman.api_rest.Model;
import jakarta.persistence.*;
@Entity
@Table(name = "Picking")
public class Picking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPicking;
    private String estado;
    // Getters y Setters
    public Long getIdPicking() {return idPicking;}
    public void setIdPicking(Long idPicking) {this.idPicking = idPicking;}
    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}
}