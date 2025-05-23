package Controller;

import Model.Entidades.*;
import Model.Servicos.CartaoService;
import Model.Servicos.PacoteTuristicoService;
import Model.Servicos.PagamentoService;
import Model.Servicos.PasseioService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class PagamentoController {

    private final CartaoController cartaoController;

    private final CartaoService cartaoService;

    private final PasseioService passeioService;

    private final PagamentoService pagamentoService;


    private final PacoteTuristicoService pacoteTuristicoService;

    public PagamentoController(CartaoController cartaoController, CartaoService cartaoService, PasseioService passeioService, PagamentoService pagamentoService, PacoteTuristicoService pacoteTuristicoService) {
        this.cartaoController = cartaoController;
        this.cartaoService = cartaoService;
        this.passeioService = passeioService;
        this.pagamentoService = pagamentoService;
        this.pacoteTuristicoService = pacoteTuristicoService;
    }

    private final Scanner sc = new Scanner(System.in);

        public void menuPagarPasseio(UsuarioEntity usuario) {
            System.out.println("Informe o ID do Passeio:");
            Long idInformado = sc.nextLong();
            sc.nextLine();

            PasseioEntity passeioEscolhido = passeioService.buscarPorId(idInformado);
            if (passeioEscolhido == null) {
                System.out.println("Passeio não encontrado!");
                return;
            }

            System.out.println("Informe a quantidade de ingressos:");
            Long quantidadeIngresso = sc.nextLong();
            sc.nextLine();

            System.out.println("Confirmar Pedido? (sim/nao):");
            String sOuN = sc.nextLine().toLowerCase();

            if (!sOuN.equals("sim")) {
                System.out.println("Compra cancelada.");
                return;
            }

            BigDecimal valorTotal = passeioEscolhido.getPreco().multiply(BigDecimal.valueOf(quantidadeIngresso));

            PedidoEntity pedidoNovo = new PedidoEntity();
            pedidoNovo.setUsuario(usuario);
            pedidoNovo.setDataCompra(LocalDate.now());
            pedidoNovo.setPasseio(passeioEscolhido);
            pedidoNovo.setQuantidadeIngressos(quantidadeIngresso);
            pedidoNovo.setValorTotal(valorTotal);
            pedidoNovo.setStatusPagamento(StatusPagamento.PENDENTE);

            usuario.addPedido(pedidoNovo);
            pagamentoService.cadastrarPedido(pedidoNovo);

            System.out.println("Digite : 1 - Pagar \n 2 - Não Pagarei agora");
            int oneTwo = sc.nextInt();

            if (oneTwo == 1) {
                if (usuario.getCartoes() == null || usuario.getCartoes().isEmpty()) {

                    System.out.println("Vc Ainda Nao Possui Cartao Cadastrado\n 1 - Cadastrar Cartao //// 2 - Voltar ao Menu");
                    int umDois = sc.nextInt();

                    if(umDois ==1) {

                        cartaoController.menuCadastroCartao(usuario);
                    }else{
                        return;
                    }

                } else {
                    cartaoController.listarCartoesUsuario(usuario);
                    System.out.println("\n");
                    System.out.println("Digite o ID do cartao que deseja usar!");
                    Long idCartao = sc.nextLong();

                    sc.nextLine();

                    CartaoEntity cartao = cartaoService.findById(idCartao);
                    System.out.println("CARTAO SELECIONADO: ");
                    System.out.println("Deseja confirmar o pagamento? sim/nao");
                    String confirmacao = sc.nextLine();

                    if (confirmacao.equals("sim")) {
                        PagamentoEntity pagamento = new PagamentoEntity();
                        pedidoNovo.setStatusPagamento(StatusPagamento.APROVADO);

                        pagamento.setMetodoPagamento(cartao.getmetodo());
                        pagamento.setStatusPagamento(StatusPagamento.APROVADO);
                        pagamento.setPedido(pedidoNovo);
                        pagamento.setData(LocalDate.now());
                        pagamento.setUsuario(usuario);
                        pagamentoService.cadastrarPagamento(pagamento);

                        System.out.println("Pagamento Realizado com Secesso!");
                    } else {
                        System.out.println("Saindo...");
                    }
                }
                System.out.println("Compra realizada com sucesso!");
            }


        }

        public void menuPagarPacote(UsuarioEntity usuario) {
            System.out.println("Digite o ID do pacote:");
            Long idInformado = sc.nextLong();
            PacoteTuristicoEntity pacote = pacoteTuristicoService.buscarPorId(idInformado);
            System.out.println("Valor: " + pacote.getPrecoTotal());

            PedidoEntity pedidoNovo = new PedidoEntity();

            pedidoNovo.setUsuario(usuario);
            LocalDate dataAtual = LocalDate.now();
            pedidoNovo.setDataCompra(dataAtual);
            pedidoNovo.setPacoteTuristico(pacote);
            pedidoNovo.setValorTotal(pacote.getPrecoTotal());
            pedidoNovo.setStatusPagamento(StatusPagamento.PENDENTE);
            usuario.addPedido(pedidoNovo);
            pagamentoService.cadastrarPedido(pedidoNovo);

            if (usuario.getCartoes() == null || usuario.getCartoes().isEmpty()) {

                System.out.println("Vc Ainda Nao Possui Cartao Cadastrado\n 1 - Cadastrar Cartao //// 2 - Voltar ao Menu");
                int umDois = sc.nextInt();

                if(umDois ==1) {

                    cartaoController.menuCadastroCartao(usuario);

                }else{
                    System.out.println("Voltando");

                }
            } else {

                System.out.println("==SEUS CARTOES CADASTRADOS==");

                cartaoService.listarCartoes(usuario);
                System.out.println("\n");
                System.out.println("Digite o ID do cartao que deseja usar!");
                Long idCartao = sc.nextLong();

                sc.nextLine();

                CartaoEntity cartao = cartaoService.findById(idCartao);
                System.out.println("CARTAO SELECIONADO: ");
                System.out.println("Deseja confirmar o pagamento? sim/nao");
                String confirmacao = sc.nextLine();

                if (confirmacao.equals("sim")) {
                    PagamentoEntity pagamento = new PagamentoEntity();
                    pedidoNovo.setStatusPagamento(StatusPagamento.APROVADO);

                    pagamento.setMetodoPagamento(cartao.getmetodo());
                    pagamento.setStatusPagamento(StatusPagamento.APROVADO);
                    pagamento.setPedido(pedidoNovo);
                    pagamento.setData(LocalDate.now());
                    pagamento.setUsuario(pedidoNovo.getUsuario());
                    usuario.addPagamento(pagamento);
                    pagamentoService.cadastrarPagamento(pagamento);

                    System.out.println("Pagamento Realizado com Secesso!");
                } else {
                    System.out.println("Saindo...");

                }
            }
        }

    public void exibirHistoricoPagamentos(UsuarioEntity usuario) {
        System.out.println("Número de pagamentos do usuário: " + usuario.getPagamentos().size());
        if (usuario.getPagamentos() != null && !usuario.getPagamentos().isEmpty()) {
            for (PagamentoEntity pagamento : usuario.getPagamentos()) {
                System.out.println("ID do Pagamento: " + pagamento.getId() + ", Status: " + pagamento.getStatusPagamento());
                if (pagamento.getStatusPagamento() == StatusPagamento.APROVADO) {
                    System.out.println("Pedido: " + pagamento.getPedido().getId());
                    System.out.println("Valor Total Pago: " + pagamento.getPedido().getValorTotal());
                    System.out.println("Data Compra: " + pagamento.getPedido().getDataCompra());
                    System.out.println("Conteúdo: ");
                    exibirDetalhesPedido(pagamento.getPedido());
                    System.out.println("////////////////////////////////////\n");
                }
            }
        } else {
            System.out.println("Você ainda não realizou nenhum pagamento.");
        }
    }

    public void exibirDetalhesPedido(PedidoEntity pedido){

            if(pedido != null) {

                System.out.println("STATUS PEDIDO: " + pedido.getStatusPagamento());
                System.out.println("VALOR TOTAL: "+ pedido.getValorTotal());
                System.out.println("CONTEÚDO: ");
                if (pedido.getPacoteTuristico() != null) {

                    System.out.println("Pacote Turistico: " + pedido.getPacoteTuristico().getTitulo());
                    for (PasseioEntity passeio : pedido.getPacoteTuristico().getPasseios()) {

                        System.out.println("- " + passeio.getTitulo());
                    }
                }

                if (pedido.getPasseio() != null) {

                    System.out.println("Passeio: ");
                    System.out.println("- " + pedido.getPasseio().getTitulo());
                    System.out.println("Quantidade Ingressos: " + pedido.getQuantidadeIngressos());
                }
            }else{
                System.out.println("Pedido não encontrado.");
                return;
            }

        }

        public void exibirPedidosPendentes(UsuarioEntity usuario) {
            boolean encontrou = false;

            for (PedidoEntity pedido : usuario.getPedidos()) {
                if (pedido.getStatusPagamento() == StatusPagamento.PENDENTE) {
                    System.out.println("ID:" + pedido.getId());
                    System.out.println("Data Pedido:" + pedido.getDataCompra());

                    System.out.println("Contéudo: ");
                    exibirDetalhesPedido(pedido);
                    System.out.println("////////////////////////////////////\n");
                    encontrou = true;
                }
            }

            if (!encontrou) {
                System.out.println("Nenhum pedido pendente encontrado.");
            }

            System.out.println("1 - Pagar Pedido Pendente /// 2 - Voltar");
            int op  = sc.nextInt();

            if(op ==1){

                System.out.println("Informe o ID do Pedido: ");
                Long idPedido = sc.nextLong();

                PedidoEntity pedidoSelecionado = pagamentoService.findPedById(idPedido);

                cartaoService.listarCartoes(usuario);

                System.out.println("Informe o ID Cartão : ");
                Long idCartaoSelecionado = sc.nextLong();
                sc.nextLine();

                CartaoEntity cartaoSelecionado = cartaoService.findById(idCartaoSelecionado);
                System.out.println("Cartão Selecionado: "+ cartaoSelecionado.getNomeCartao());

                //
                System.out.println("Deseja confirmar o pagamento? sim/nao");
                String confirmacao = sc.nextLine();

                if (confirmacao.equals("sim")) {
                    PagamentoEntity pagamento = new PagamentoEntity();

                    pagamento.setMetodoPagamento(cartaoSelecionado.getmetodo());
                    pagamento.setStatusPagamento(StatusPagamento.APROVADO);
                    pagamento.setPedido(pedidoSelecionado);
                    pagamento.setData(LocalDate.now());
                    pagamento.setUsuario(usuario);
                    pagamentoService.cadastrarPagamento(pagamento);

                    usuario.addPagamento(pagamento);
                    System.out.println("Pagamento Realizado com Sucess!");
                } else {
                    System.out.println("Saindo...");
                }
            }
        }
    }

