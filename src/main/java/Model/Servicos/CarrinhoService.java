package Model.Servicos;

import Model.Entidades.*;
import Model.Repositorio.CarrinhoRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

public class CarrinhoService {

    private CarrinhoRepository carrinhoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository) {
        this.carrinhoRepository = carrinhoRepository;
    }

    @Transactional
    public void adicionarItemAoCarrinho(UsuarioEntity usuario, String tipo, Long itemId, String titulo, BigDecimal preco) {
        CarrinhoEntity carrinho = carrinhoRepository.buscarPorUsuarioId(usuario.getId());

        if (carrinho == null) {
            carrinho = new CarrinhoEntity(usuario);
            carrinhoRepository.cadastrar(carrinho);
        }

        ItemCarrinhoEntity novoItem = new ItemCarrinhoEntity(tipo, itemId, titulo, preco);
        carrinho.adicionarItem(novoItem);

        carrinhoRepository.atualizar(carrinho);
    }

    @Transactional
    public void removerItemDoCarrinho(UsuarioEntity usuario, Long itemId) {
        CarrinhoEntity carrinho = carrinhoRepository.buscarPorUsuarioId(usuario.getId());
        if (carrinho == null) return;

        Optional<ItemCarrinhoEntity> itemOptional = carrinho.getItens().stream()
                .filter(item -> item.getItemId().equals(itemId))
                .findFirst();

        itemOptional.ifPresent(item -> {
            carrinho.removerItem(item);
            carrinhoRepository.atualizar(carrinho);
        });
    }

    public BigDecimal calcularTotal(UsuarioEntity usuario) {
        CarrinhoEntity carrinho = carrinhoRepository.buscarPorUsuarioId(usuario.getId());
        if (carrinho == null) return BigDecimal.ZERO;

        return carrinho.calcularTotal();
    }

    @Transactional
    public void limparCarrinho(UsuarioEntity usuario) {
        CarrinhoEntity carrinho = carrinhoRepository.buscarPorUsuarioId(usuario.getId());
        if (carrinho != null) {
            carrinho.getItens().clear();
            carrinhoRepository.atualizar(carrinho);
        }
    }

    public CarrinhoEntity getCarrinho(UsuarioEntity usuario) {
        return carrinhoRepository.buscarPorUsuarioId(usuario.getId());
    }
}
