package Model.Entidades;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ItemCarrinhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // "PACOTE" ou "PASSEIO"
    private Long itemId; // ID do PacoteTuristico ou Passeio

    private String titulo;
    private BigDecimal preco;

    @ManyToOne
    private CarrinhoEntity carrinho;

    public ItemCarrinhoEntity() {}

    public ItemCarrinhoEntity(String tipo, Long itemId, String titulo, BigDecimal preco) {
        this.tipo = tipo;
        this.itemId = itemId;
        this.titulo = titulo;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public CarrinhoEntity getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoEntity carrinho) {
        this.carrinho = carrinho;
    }
}
