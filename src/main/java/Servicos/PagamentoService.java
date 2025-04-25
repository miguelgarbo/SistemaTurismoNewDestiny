package Servicos;

import Entidades.*;
import Repositorio.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PagamentoService {
    @Inject
    private PagamentoRepository pagamentoRepository;

    @Inject
    private PagamentoEntity pagamentoEntity;

    @Inject
    private CartaoSevice cartaoSevice;

    @Inject
    private PacoteTuristicoRepository pacoteTuristicoRepository;

    @Inject
    private CartaoRepositorio cartaoRepositorio;



    public PagamentoService(PagamentoRepository pagamentoRepository, PagamentoEntity pagamentoEntity, CartaoSevice cartaoSevice, PacoteTuristicoRepository pacoteTuristicoRepository, CartaoRepositorio cartaoRepositorio) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoEntity = pagamentoEntity;
        this.cartaoSevice = cartaoSevice;
        this.pacoteTuristicoRepository = pacoteTuristicoRepository;
        this.cartaoRepositorio = cartaoRepositorio;

    }

    private final Scanner sc = new Scanner(System.in);

public void efetuarPagamento(UsuarioEntity usuario){
    System.out.println("Digite o ID do pacote!");
    Long idInformado = sc.nextLong();
    PacoteTuristicoEntity pacote = pacoteTuristicoRepository.findById(idInformado);
    System.out.println("Valor: " + pacote.getPrecoTotal());

    PedidoEntity pedidoNovo = new PedidoEntity();
    pedidoNovo.setusuario(usuario);
    LocalDate dataAtual = LocalDate.now();
    pedidoNovo.setDataCompra(dataAtual);
    pedidoNovo.setpacoteTuristico(pacote);
    pedidoNovo.setValorTotal(pacote.getPrecoTotal());
    pedidoNovo.setStatusPagamento(StatusPagamento.PENDENTE);

    cartaoSevice.listarCartoes(usuario);
    System.out.println("Digite o ID do Cartão que deseja usar!");
    Long idCartao = sc.nextLong();
    CartaoEntity cartão = cartaoRepositorio.findById(idCartao);
    System.out.println("Deseja confirmar o pagamento? sim/nao");
    String confirmação = sc.nextLine();

    if(confirmação.equals("sim")){
        PagamentoEntity pagamento = new PagamentoEntity();
        pagamento.setMetodoPagamento(cartão.getMétodo());
        pagamento.setStatusPagamento(StatusPagamento.APROVADO);
        pagamento.setpedido(pedidoNovo);
        pagamento.setData(LocalDate.now());
        System.out.println("Pagamento Realizado com Secesso!");
        }else {
        System.out.println("Saindo...");
    }
    }


}

