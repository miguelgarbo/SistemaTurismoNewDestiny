package Entidades;


import javax.persistence.*;

@Entity(name = "Cartao")
public class CartaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numeroCartão;
    private int digitoVerificador;
    private String bandeira;
    private String método;

    @ManyToOne()
    @JoinColumn(name = "cartões")
    private UsuarioEntity usuario;

    public CartaoEntity(Long id, int numeroCartão, int digitoVerificador, String bandeira, String método) {
        this.id = id;
        this.numeroCartão = numeroCartão;
        this.digitoVerificador = digitoVerificador;
        this.bandeira = bandeira;
        this.método = método;
    }

    public CartaoEntity(){}

    public Long getId() {
        return id;
    }

    public int getNumeroCartão() {
        return numeroCartão;
    }

    public int getDigitoVerificador() {
        return digitoVerificador;
    }

    public String getBandeira() {
        return bandeira;
    }

    public String getMétodo() {
        return método;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumeroCartão(int numeroCartão) {
        this.numeroCartão = numeroCartão;
    }

    public void setDigitoVerificador(int digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public void setMétodo(String método) {
        this.método = método;
    }
}
