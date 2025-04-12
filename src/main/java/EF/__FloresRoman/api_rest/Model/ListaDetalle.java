package EF.__FloresRoman.api_rest.Model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Entity
@Table(name = "lista_detalle")
@Data
@NoArgsConstructor
public class ListaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idlistadetalle;
    private String nombreEmpresa;
    private String nomObra;
    private String piso;
    @ManyToOne
    @JoinColumn(name = "id_tabla_lista")
    private TablaLista tablaLista;
    @Column(updatable = false)
    private LocalDate fecha;
    private Double costoUnitario;
    private Double costoTotal;
    @PrePersist
    public void prePersist() {
        this.fecha = LocalDate.now();
    }
}