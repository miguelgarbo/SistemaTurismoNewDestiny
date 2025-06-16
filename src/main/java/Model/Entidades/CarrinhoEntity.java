package Model.Entidades;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CarrinhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrinhoEntity> itens = new ArrayList<>();

    public CarrinhoEntity() {}

    public CarrinhoEntity(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public void adicionarItem(ItemCarrinhoEntity item) {
        item.setCarrinho(this);
        this.itens.add(item);
    }

    public void removerItem(ItemCarrinhoEntity item) {
        this.itens.remove(item);
        item.setCarrinho(null);
    }

    public BigDecimal calcularTotal() {
        return itens.stream()
                .map(ItemCarrinhoEntity::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public List<ItemCarrinhoEntity> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinhoEntity> itens) {
        this.itens = itens;
    }
}
