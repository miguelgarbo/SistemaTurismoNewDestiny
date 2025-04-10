package Entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "PacoteTuristico")
public class PacoteTuristicoEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private double precoTotal;
    private String categoria;

    @ManyToMany
    @JoinTable(
            name = "pacote_passeios",
            joinColumns = @JoinColumn(name = "idpacote"),
            inverseJoinColumns = @JoinColumn(name = "idpasseio")
    )
    private List<PasseioEntity> listaPasseiosInclusos = new ArrayList<>();

    @OneToMany(mappedBy = "pacoteTuristico")
    private List<PedidoEntity> listaPedidosFeitos = new ArrayList<>();


    public PacoteTuristicoEntity(Long id, String titulo, double precoTotal, String categoria, List<PasseioEntity> listaPasseiosInclusos, List<PedidoEntity> listaPedidosFeitos) {
        this.id = id;
        this.titulo = titulo;
        this.precoTotal = precoTotal;
        this.categoria = categoria;
        this.listaPasseiosInclusos = listaPasseiosInclusos;
        this.listaPedidosFeitos = listaPedidosFeitos;
    }

    public PacoteTuristicoEntity(){

    }

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

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void addPasseio(PasseioEntity passeio){
        this.listaPasseiosInclusos.add(passeio);
    }

    @Override
    public String toString() {
        return "PacoteTuristicoEntity{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", precoTotal=" + precoTotal +
                ", categoria='" + categoria + '\'' +
                ", listaPasseiosInclusos=" + listaPasseiosInclusos +
                '}';
    }
}
