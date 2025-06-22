package Model.Entidades;


import javax.persistence.*;

@Entity(name = "Cartao")
public class CartaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCartao;
    private String numeroCartao;
    private String digitoVerificador;
    @Enumerated(EnumType.STRING)
    private EBandeirasCartao bandeira;
    private String metodo;

    @ManyToOne()
    @JoinColumn(name = "usuario")
    private UsuarioEntity usuario;

    public CartaoEntity(String nomeCartao, String numeroCartao, String digitoVerificador, EBandeirasCartao bandeira, String metodo, UsuarioEntity usuario) {
        this.nomeCartao = nomeCartao;
        this.numeroCartao = numeroCartao;
        this.digitoVerificador = digitoVerificador;
        this.bandeira = bandeira;
        this.metodo = metodo;
        this.usuario = usuario;
    }

    public CartaoEntity(){}

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getDigitoVerificador() {
        return digitoVerificador;
    }

    public EBandeirasCartao getBandeira() {
        return bandeira;
    }

    public String getmetodo() {
        return metodo;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public void setDigitoVerificador(String digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    public void setBandeira(EBandeirasCartao bandeira) {
        this.bandeira = bandeira;
    }

    public void setmetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }


}
