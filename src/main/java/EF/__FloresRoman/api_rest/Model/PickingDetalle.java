package EF.__FloresRoman.api_rest.Model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Table(name = "picking_detalle")
@Data
public class PickingDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpickingdetalle;
    // Relación con TablaLista
    @ManyToOne
    @JoinColumn(name = "id_tabla_list", nullable = false)
    private TablaLista tablaLista;
    // Relación con Picking (estado)
    @ManyToOne
    @JoinColumn(name = "id_picking", nullable = false)
    private Picking picking;
    private LocalDate fecha;
    private LocalTime hora;
    // Al crear un nuevo registro
    @PrePersist
    public void prePersist() {
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
    }
    // Al actualizar un registro existente
    @PreUpdate
    public void preUpdate() {
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
    }
}