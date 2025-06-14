package Model.Entidades;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Passeio")
public class PasseioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String  descricao;
    private String  duracao;
    private BigDecimal preco;
    private String localizacao;
    private String horarios;

    @ManyToMany(mappedBy = "passeios")
    private List<PacoteTuristicoEntity> pacotes = new ArrayList<>();

    @ManyToMany(mappedBy = "passeios")
    private List<RoteiroPersonalizadoEntity> roteiros = new ArrayList<>();

    @OneToMany(mappedBy = "passeio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FotoEntity> listaFotos = new ArrayList<>();

    @ManyToMany(mappedBy = "passeios")
    private List<DiaEntity> dias = new ArrayList<>();;

    @ManyToMany
    @JoinTable(
            name = "passeio_categoria",
            joinColumns = @JoinColumn(name = "passeio_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<CategoriaEntity> categorias = new ArrayList<>();



    public PasseioEntity( String titulo, String descricao, String duracao, BigDecimal preco, String localizacao, String horarios, List<PacoteTuristicoEntity> pacotes, List<RoteiroPersonalizadoEntity> roteiros, List<FotoEntity> listaFotos) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.preco = preco;
        this.localizacao = localizacao;
        this.horarios = horarios;
        this.pacotes = pacotes;
        this.roteiros = roteiros;
        this.listaFotos = listaFotos;

    }

    public PasseioEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public List<FotoEntity> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(List<FotoEntity> listaFotos) {
        this.listaFotos = listaFotos;
    }

    public void addFoto(FotoEntity foto){
        this.getListaFotos().add(foto);
    }

    public List<PacoteTuristicoEntity> getPacotes() {
        return pacotes;
    }

    public void setPacotes(List<PacoteTuristicoEntity> pacotes) {
        this.pacotes = pacotes;
    }

    public List<RoteiroPersonalizadoEntity> getRoteiros() {
        return roteiros;
    }

    public void setRoteiros(List<RoteiroPersonalizadoEntity> roteiros) {
        this.roteiros = roteiros;
    }


    public List<DiaEntity> getDias() {
        return dias;
    }

    public void setDias(List<DiaEntity> dias) {
        this.dias = dias;
    }

    public List<CategoriaEntity> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaEntity> categorias) {
        this.categorias = categorias;
    }

    public void addCategoria(CategoriaEntity categoria){
        this.getCategorias().add(categoria);

    }

}
