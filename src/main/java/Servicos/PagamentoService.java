package Servicos;

import Model.Repositorio.PagamentoRepository;
import Model.Repositorio.PedidoRepository;
import Model.Entidades.*;

public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, PedidoRepository pedidoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
    }


    public void cadastrarPedido(PedidoEntity pedido) {
        pedidoRepository.cadastrar(pedido);
    }


    public void cadastrarPagamento(PagamentoEntity pagamento) {
        if (pagamento.getUsuario() == null) {
            throw new IllegalArgumentException("O pagamento precisa estar associado a um usuário.");
        }

        pagamentoRepository.cadastrar(pagamento);
    }


    public void atualizarPedido(PedidoEntity pedido){
        pedidoRepository.atualizar(pedido);
    }

    public void atualizarPagamento(PagamentoEntity pagamento){
        pagamentoRepository.atualizar(pagamento);
    }

    public PagamentoEntity findPagById(Long id) {
        return pagamentoRepository.findById(id);
    }

    public PedidoEntity findPedById(Long id) {
        return pedidoRepository.findById(id);
    }

}






