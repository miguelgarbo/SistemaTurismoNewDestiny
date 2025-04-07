package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "PacoteTuristico")
public class PacoteTuristicoEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private double precoTotal;
    private String categoria;

    public PacoteTuristicoEntity(int id, String titulo, double precoTotal, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.precoTotal = precoTotal;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "PacoteTuristicoEntity{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", precoTotal=" + precoTotal +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    public PacoteTuristicoEntity(){

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
}
