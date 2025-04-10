package Entidades;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "passeio")

public class PasseioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String  descricao;
    private String  duracao;
    private BigDecimal preco;
    private String localizacao;
    @ElementCollection
    @CollectionTable(name = "passeio_fotos", joinColumns = @JoinColumn(name = "passeio_id"))
    @Column(name = "foto")
    private List<String> listaFotos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "passeio_horarios", joinColumns = @JoinColumn(name = "passeio_id"))
    @Column(name = "horario")
    private List<String> listaHorarios = new ArrayList<>();


    public PasseioEntity(String titulo, String descricao, String duracao, BigDecimal preco, String localizacao, List<String> listaFotos, List<String> listaHorarios) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.preco = preco;
        this.localizacao = localizacao;
        this.listaFotos = listaFotos;
        this.listaHorarios = listaHorarios;
    }

    public PasseioEntity() {
    }

    @Override
    public String toString() {
        return "PasseioEntity{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", duracao='" + duracao + '\'' +
                ", preco=" + preco +
                ", localizacao='" + localizacao + '\'' +
                ", listaFotos=" + listaFotos +
                ", listaHorarios=" + listaHorarios +
                '}';
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public List<String> getListaHorarios() {
        return listaHorarios;
    }

    public void setListaHorarios(List<String> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public List<String> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(List<String> listaFotos) {
        this.listaFotos = listaFotos;
    }

    public void addFoto(String foto){
        this.listaFotos.add(foto);
    }

    public void addHorario(String horario){
        this.listaHorarios.add(horario);
    }

}
