package Model.Entidades;

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
    private List<PedidoEntity> pedidos = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "pacote_categoria",
            joinColumns = @JoinColumn(name = "pacote_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<CategoriaEntity> categorias = new ArrayList<>();


    public PacoteTuristicoEntity(Long id, String titulo, BigDecimal precoTotal, List<PasseioEntity> passeios, List<PedidoEntity> pedidos, List<CategoriaEntity> categorias) {
        this.id = id;
        this.titulo = titulo;
        this.precoTotal = precoTotal;
        this.passeios = passeios;
        this.pedidos = pedidos;
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

    public List<PedidoEntity> getpedidos() {
        return pedidos;
    }

    public void setpedidos(List<PedidoEntity> pedidos) {
        this.pedidos = pedidos;
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


}
