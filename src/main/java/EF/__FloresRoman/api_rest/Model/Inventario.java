package EF.__FloresRoman.api_rest.Model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
@Entity
@Table(name = "inventario")
@Data
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idinventario;
    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Producto producto;
    private Integer cantidadReal;
    private Integer cantidadReservada;
    private LocalDate fecha;
    @PrePersist
    public void setFechaRegistro() {
        this.fecha = LocalDate.now();
    }
    @PreUpdate
    public void onUpdate() {
        this.fecha = LocalDate.now(); // Se reasigna al modificar
    }
}