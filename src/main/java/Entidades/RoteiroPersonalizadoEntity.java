package Entidades;


import javax.persistence.*;
import java.time.LocalDate;
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

    @OneToMany(mappedBy = "roteiro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DiaEntity> dias = new ArrayList<>();

    private LocalDate dataInicio;

    @ManyToMany
    @JoinTable(name = "roteiro_passeios",
        joinColumns = @JoinColumn(name = "idroteiro"),
            inverseJoinColumns = @JoinColumn(name = "idpasseio")
    )
    private List<PasseioEntity> passeios = new ArrayList<>();


    public RoteiroPersonalizadoEntity(){}


    public RoteiroPersonalizadoEntity(UsuarioEntity usuario, String titulo, List<DiaEntity> dias, LocalDate dataInicio, List<PasseioEntity> passeios) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.dias = dias;
        this.dataInicio = dataInicio;
        this.passeios = passeios;
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

    public void addPasseio(PasseioEntity passeio) {
        if (passeio != null) {
            this.passeios.add(passeio);
        }
    }





    public void setPasseios(List<PasseioEntity> passeios) {
        this.passeios = passeios;
    }

    public List<PasseioEntity> getPasseios() {
        return passeios;
    }


    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }


    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    // Métodos para gerenciar os dias do roteiro
    public void adicionarDia(DiaEntity dia) {
        if (dias == null) dias = new ArrayList<>();
        this.dias.add(dia);
        dia.setRoteiro(this); // Associando o roteiro ao dia
    }

    public List<DiaEntity> getDias() {
        return dias;
    }

    public void setDias(List<DiaEntity> dias) {
        this.dias = dias;
    }
}
