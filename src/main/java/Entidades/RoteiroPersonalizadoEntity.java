package Entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity(name = "RoteiroPersonalizado")
public class RoteiroPersonalizadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private UsuarioEntity usuario;
    private String titulo;
    private Long numeroDias;

    @ManyToMany
    @JoinTable(name = "roteiro_passeios",
        joinColumns = @JoinColumn(name = "idroteiro"),
            inverseJoinColumns = @JoinColumn(name = "idpasseio")
    )
    private List<PasseioEntity> passeios = new ArrayList<>();


// para ter a relaçcao de passeios nos dias especificos que o usuario quiser
@Transient
private Map<Integer, List<PasseioEntity>> passeiosPorDia;

    public RoteiroPersonalizadoEntity(UsuarioEntity usuario, String titulo, Long numeroDias, List<PasseioEntity> passeios, Map<Integer, List<PasseioEntity>> passeiosPorDia) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.numeroDias = numeroDias;
        this.passeios  = (passeios != null) ? passeios : new ArrayList<>();
        this.passeiosPorDia = (passeiosPorDia != null) ? passeiosPorDia : new HashMap<>();
    }


    public RoteiroPersonalizadoEntity(){}

    public void adicionarPasseioAoDia(int diaSelecionado, PasseioEntity passeio){
        if (passeiosPorDia == null) {
            passeiosPorDia = new HashMap<>();
        }

        if(!passeiosPorDia.containsKey(diaSelecionado)){
            passeiosPorDia.put(diaSelecionado, new ArrayList<>());
        }else {

            passeiosPorDia.get(diaSelecionado).add(passeio);

        }
    }

    public List<PasseioEntity> getPasseiosPorDia(int dia){

        return passeiosPorDia.getOrDefault(dia, new ArrayList<>());
    }


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

    public void setPasseios(List<PasseioEntity> passeios) {
        this.passeios = passeios;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public List<PasseioEntity> getPasseios() {
        return passeios;
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
