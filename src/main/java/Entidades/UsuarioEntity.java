package Entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Usuario")

public class UsuarioEntity {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String senha;
    private String email;
    private String numeroTelefone;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PedidoEntity> pedidos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RoteiroPersonalizadoEntity> roteirosCriados = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartaoEntity> cartões = new ArrayList<>();


    public UsuarioEntity(Long id, String nome, String senha, String email, String numeroTelefone, List<PedidoEntity> pedidos) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.numeroTelefone = numeroTelefone;
        this.pedidos = pedidos;
    }

    public UsuarioEntity(){}

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", numeroTelefone='" + numeroTelefone + '\'' +
                ", pedidos=" + pedidos +
                ", roteirosCriados=" + roteirosCriados +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public List<PedidoEntity> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoEntity> pedidos) {
        this.pedidos = pedidos;
    }

    public List<RoteiroPersonalizadoEntity> getRoteirosCriados() {
        return roteirosCriados;
    }

    public void setRoteirosCriados(List<RoteiroPersonalizadoEntity> roteirosCriados) {
        this.roteirosCriados = roteirosCriados;
    }

    public List<CartaoEntity> getCartões() {
        return cartões;
    }

    public void addCartao(CartaoEntity cartao){
        this.getCartões().add(cartao);
    }

    public void setCartões(List<CartaoEntity> cartões) {
        this.cartões = cartões;
    }
}
