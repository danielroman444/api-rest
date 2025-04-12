package EF.__FloresRoman.api_rest.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "tabla_lista")
@Getter
@Setter
public class TablaLista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtablalista;
    // Relación con Producto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    private Producto producto;
    private Integer cantidad;
    @Column(name = "peso_neto")
    private Double pesoNeto;
    // Se calcula automáticamente antes de persistir o actualizar
    @PrePersist
    @PreUpdate
    public void calcularPesoNeto() {
        if (producto != null && producto.getPeso() != null && cantidad != null) {
            this.pesoNeto = producto.getPeso() * cantidad;
        } else {
            this.pesoNeto = 0.0;
        }
    }
}