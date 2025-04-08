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
    private String descricao;
    private String duracao;
    private BigDecimal preco;
    private String localizacao;

    @OneToMany(mappedBy = "passeio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FotoEntity> fotos = new ArrayList<>();

    @OneToMany(mappedBy = "passeio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HorarioEntity> listaHorarios = new ArrayList<>();

    public PasseioEntity(String titulo, String descricao, String duracao, BigDecimal preco, List<HorarioEntity> listaHorarios, String localizacao, List<FotoEntity> fotos) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.preco = preco;
        this.listaHorarios = listaHorarios;
        this.localizacao = localizacao;
        this.fotos = fotos;
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
                ", listaHorarios=" + listaHorarios +
                ", localizacao='" + localizacao + '\'' +
                ", fotos=" + fotos +
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

    public List<HorarioEntity> getListaHorarios() {
        return listaHorarios;
    }

    public void setListaHorarios(List<HorarioEntity> listaHorarios) {
        this.listaHorarios = listaHorarios;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public List<FotoEntity> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoEntity> fotos) {
        this.fotos = fotos;
    }

    public void addFoto(FotoEntity foto) {
        fotos.add(foto);
        foto.setPasseio(this);
    }

    public void addHorario(HorarioEntity horario) {
        listaHorarios.add(horario);
        horario.setPasseio(this);
    }

    public void removeFoto(FotoEntity foto) {
        fotos.remove(foto);
        foto.setPasseio(null); // Remove the relationship
    }

    public void removeHorario(HorarioEntity horario) {
        listaHorarios.remove(horario);
        horario.setPasseio(null); // Remove the relationship
    }
}
