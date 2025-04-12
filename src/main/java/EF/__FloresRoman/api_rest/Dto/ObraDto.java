package EF.__FloresRoman.api_rest.Dto;
public class ObraDto {
    private Long idobra;
    private String nomObra;
    private Integer piso;
    public ObraDto(Long idobra, String nomObra, Integer piso) {
        this.idobra = idobra;
        this.nomObra = nomObra;
        this.piso = piso;
    }
    // Getters y setters si usas Lombok o manual
    public Long getIdobra() {return idobra;}
    public void setIdobra(Long idobra) {this.idobra = idobra;}
    public String getNomObra() {return nomObra;}
    public void setNomObra(String nomObra) {this.nomObra = nomObra;}
    public Integer getPiso() {return piso;}
    public void setPiso(Integer piso) {this.piso = piso;}
}
//(Data Transfer Object) se muestra los datos con los que el cliente interactuo
//Este es el Dto de salido