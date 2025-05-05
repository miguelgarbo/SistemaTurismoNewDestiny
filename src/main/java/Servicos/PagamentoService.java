package Servicos;

import Entidades.*;
import Repositorio.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Scanner;

public class PagamentoService {
    @Inject
    private final PagamentoRepository pagamentoRepository;

    @Inject
    private final CartaoService cartaoService;

    @Inject
    private final PacoteTuristicoRepository pacoteTuristicoRepository;

    @Inject
    private final CartaoRepositorio cartaoRepositorio;
    
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, CartaoService cartaoService, PacoteTuristicoRepository pacoteTuristicoRepository, CartaoRepositorio cartaoRepositorio, PedidoRepository pedidoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.cartaoService = cartaoService;
        this.pacoteTuristicoRepository = pacoteTuristicoRepository;
        this.cartaoRepositorio = cartaoRepositorio;
        this.pedidoRepository = pedidoRepository;

    }

    private final Scanner sc = new Scanner(System.in);


   public void efetuarPagamento(UsuarioEntity usuario) {
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
    pedidoRepository.cadastrar(pedidoNovo);
    

    if (usuario.getCartoes() == null && usuario.getCartoes().isEmpty()) {

        System.out.println("Vc Ainda Nao Possui Cartao Cadastrado\n 1 - Cadastrar Cartao\n 2 - Voltar ao Menu");
    } else {

        System.out.println("==SEUS CARTOES CADASTRADOS==");

        cartaoService.listarCartoes(usuario);
        System.out.println("\n");
        System.out.println("Digite o ID do cartao que deseja usar!");
        Long idCartao = sc.nextLong();

        sc.nextLine();

        CartaoEntity cartao = cartaoRepositorio.findById(idCartao);
        System.out.println("CARTAO SELECIONADO: ");
        System.out.println("Deseja confirmar o pagamento? sim/nao");
        String confirmacao = sc.nextLine();

        if (confirmacao.equals("sim")) {
            PagamentoEntity pagamento = new PagamentoEntity();

            pagamento.setMetodoPagamento(cartao.getmetodo());
            pagamento.setStatusPagamento(StatusPagamento.APROVADO);
            pagamento.setpedido(pedidoNovo);
            pagamento.setData(LocalDate.now());
            pagamentoRepository.cadastrar(pagamento);

            System.out.println("Pagamento Realizado com Secesso!");
        } else {
            System.out.println("Saindo...");

        }
    }
}


}

