package Entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "RoteiroPersonalizado")
public class RoteiroPersonalizadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "roteiropersonalizado", nullable = false)
    private UsuarioEntity usuario;

    private String titulo;
    private Long numeroDias;

    @ManyToMany
    @JoinTable(name = "roteiro_passeios",
        joinColumns = @JoinColumn(name = "idpacote"),
            inverseJoinColumns = @JoinColumn(name = "idpasseio")
    )
    private List<PasseioEntity> passeios = new ArrayList<>();

    public RoteiroPersonalizadoEntity(Long id, UsuarioEntity usuario, String titulo, Long numeroDias, List<PasseioEntity> passeios) {
        this.id = id;
        this.usuario = usuario;
        this.titulo = titulo;
        this.numeroDias = numeroDias;
        this.passeios = passeios = new ArrayList<>();
    }

    public RoteiroPersonalizadoEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioEntity getusuario() {
        return usuario;
    }

    public void setusuario(UsuarioEntity usuario) {
        this.usuario = usuario;
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

    public void addPasseio(PasseioEntity passeio) {
        if (passeio != null) {
            this.passeios.add(passeio);
        }
    }

    @Override
    public String toString() {
        return "RoteiroPersonalizadoEntity{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", titulo='" + titulo + '\'' +
                ", numeroDias=" + numeroDias +
                ", passeios=" + passeios +
                '}';
    }
}
