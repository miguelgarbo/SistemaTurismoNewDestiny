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


    @ManyToMany
    @JoinTable(
            name = "pacote_passeios",
            joinColumns = @JoinColumn(name = "idpacote"),
            inverseJoinColumns = @JoinColumn(name = "idpasseio")
    )
    private List<PasseioEntity> passeios = new ArrayList<>();

    @OneToMany(mappedBy = "pacoteTuristico")
    private List<PedidoEntity> listaPedidosFeitos = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "pacote_categoria",
            joinColumns = @JoinColumn(name = "pacote_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<CategoriaEntity> categorias = new ArrayList<>();


    public PacoteTuristicoEntity(Long id, String titulo, BigDecimal precoTotal, List<PasseioEntity> passeios, List<PedidoEntity> listaPedidosFeitos, List<CategoriaEntity> categorias) {
        this.id = id;
        this.titulo = titulo;
        this.precoTotal = precoTotal;
        this.passeios = passeios;
        this.listaPedidosFeitos = listaPedidosFeitos;
        this.categorias = categorias;
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


    public void addPasseio(PasseioEntity passeio){
        this.passeios.add(passeio);
    }

    public void addCategoria (CategoriaEntity categoria) {
        this.categorias.add(categoria);
    }

    public List<PasseioEntity> getPasseios() {
        return passeios;
    }

    public void setPasseios(List<PasseioEntity> passeios) {
        this.passeios = passeios;
    }

    public List<CategoriaEntity> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaEntity> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "PacoteTuristicoEntity{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", precoTotal=" + precoTotal +
                ", passeios=" + passeios +
                ", listaPedidosFeitos=" + listaPedidosFeitos +
                ", categorias=" + categorias +
                '}';
    }
}
