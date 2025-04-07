package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "RoteiroPersonalizado")
public class RoteiroPersonalizadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idUsuario;
    private String titulo;
    private int numeroDias;

    public RoteiroPersonalizadoEntity(int id, int idUsuario, String titulo, int numeroDias) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.numeroDias = numeroDias;
    }

    public RoteiroPersonalizadoEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    @Override
    public String toString() {
        return "RoteiroPersonalizadoEntity{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", titulo='" + titulo + '\'' +
                ", numeroDias=" + numeroDias +
                '}';
    }
}
