package EF.__FloresRoman.api_rest.Model;
import jakarta.persistence.*;
@Entity
@Table(name = "obra")
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idobra;
    @Column(name = "nom_obra", nullable = false)
    private String nomObra;
    @Column(nullable = false)
    private Integer piso;
    // Getters y Setters
    public Long getIdobra() { return idobra; }
    public void setIdobra(Long idobra) { this.idobra = idobra; }
    public String getNomObra() { return nomObra; }
    public void setNomObra(String nomObra) { this.nomObra = nomObra; }
    public Integer getPiso() { return piso; }
    public void setPiso(Integer piso) { this.piso = piso; }
}