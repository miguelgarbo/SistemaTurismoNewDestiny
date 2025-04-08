package Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "RoteiroPersonalizado")
public class RoteiroPersonalizadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;
    private String titulo;
    private Long numeroDias;

    public RoteiroPersonalizadoEntity(Long idUsuario, String titulo, Long numeroDias) {
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.numeroDias = numeroDias;
    }

    public RoteiroPersonalizadoEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(Long numeroDias) {
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
