package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "passeio")

public class PasseioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String descricao;
    private String duracao;
    private double preco;
    private List<String> listaHorarios = new ArrayList<>();;
    private String localizacao;
    private List<String> fotos;

    public PasseioEntity(int id, String titulo, String descricao, String duracao, double preco, List<String> listaHorarios, String localizacao, List<String> fotos) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.preco = preco;
        this.listaHorarios = listaHorarios;
        this.localizacao = localizacao;
        this.fotos = fotos;
    }

    public PasseioEntity(){}

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
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

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
}
