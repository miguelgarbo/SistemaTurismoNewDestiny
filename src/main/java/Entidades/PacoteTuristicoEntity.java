package Entidades;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "PacoteTuristico")
public class PacoteTuristicoEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private BigDecimal precoTotal;

    private String categoria;

    @ManyToMany
    @JoinTable(
            name = "pacote_passeios",
            joinColumns = @JoinColumn(name = "idpacote"),
            inverseJoinColumns = @JoinColumn(name = "idpasseio")
    )
    private List<PasseioEntity> passeios = new ArrayList<>();

    @OneToMany(mappedBy = "pacoteTuristico")
    private List<PedidoEntity> listaPedidosFeitos = new ArrayList<>();


    public PacoteTuristicoEntity(Long id, String titulo, BigDecimal precoTotal, String categoria, List<PasseioEntity> passeios, List<PedidoEntity> listaPedidosFeitos) {
        this.id = id;
        this.titulo = titulo;
        this.precoTotal = precoTotal;
        this.categoria = categoria;
        this.passeios = passeios;
        this.listaPedidosFeitos = listaPedidosFeitos;
    }

    public PacoteTuristicoEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public String getCategoria() {
        return categoria;
    }

    public List<PasseioEntity> getpasseios() {
        return passeios;
    }

    public void setpasseios(List<PasseioEntity> passeios) {
        this.passeios = passeios;
    }

    public List<PedidoEntity> getListaPedidosFeitos() {
        return listaPedidosFeitos;
    }

    public void setListaPedidosFeitos(List<PedidoEntity> listaPedidosFeitos) {
        this.listaPedidosFeitos = listaPedidosFeitos;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void addPasseio(PasseioEntity passeio){
        this.passeios.add(passeio);
    }

    @Override
    public String toString() {
        return "PacoteTuristicoEntity{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", precoTotal=" + precoTotal +
                ", categoria='" + categoria + '\'' +
                ", passeios=" + passeios +
                '}';
    }
}
