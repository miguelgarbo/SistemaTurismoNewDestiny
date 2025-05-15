package Controller;

import Model.Entidades.CartaoEntity;
import Model.Entidades.EBandeirasCartao;
import Model.Entidades.EMetodosPagamento;
import Model.Entidades.UsuarioEntity;
import Servicos.CartaoService;

import java.util.Scanner;

public class CartaoController {

    private final Scanner sc = new Scanner(System.in);
    private final CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }


    public void menuCadastroCartao(UsuarioEntity usuario) {
        CartaoEntity cartaoNovo = new CartaoEntity();

        System.out.println("==CADASTRO DE CARTAO==");
        System.out.println("Informe um nome ao Cartao: ");
        String nomeCartao = sc.nextLine();

        System.out.println("DIgite o numero do cartão:");
        String numeroCartao = sc.nextLine();
        System.out.println("Digite o Dígito de verificação");
        String numeroVerificador = sc.nextLine();

        System.out.println("Digite método de pagamento pelo número: ");

        for (int i = 0; i < cartaoService.buscarMetodosPag().length; i++) {
            System.out.println(i + "-" + cartaoService.buscarMetodosPag()[i]);
        }
        int metodoPagamento = sc.nextInt();

        if (metodoPagamento == 0) {

            cartaoNovo.setmetodo(EMetodosPagamento.débito.name());
        } else if (metodoPagamento == 1) {

            cartaoNovo.setmetodo(EMetodosPagamento.crédito.name());
        } else {

            System.out.println("Metodo Invalido");
        }

        System.out.println("Digite a Bandeira do Cartão De Acordo Com essas Abaixo:");

        for (int i = 0; i < cartaoService.buscarBandeirasDisponiveis().length; i++) {

            System.out.println(i + "-" + cartaoService.buscarBandeirasDisponiveis()[i]);
        }

        int bandeiraOpcao = sc.nextInt();
        sc.nextLine();

        EBandeirasCartao bandeiraSelecionada = EBandeirasCartao.values()[bandeiraOpcao];

        cartaoNovo.setNomeCartao(nomeCartao);
        cartaoNovo.setNumeroCartao(numeroCartao);
        cartaoNovo.setDigitoVerificador(numeroVerificador);
        cartaoNovo.setBandeira(bandeiraSelecionada);
        cartaoNovo.setUsuario(usuario);

        usuario.addCartao(cartaoNovo);
        cartaoService.cadastrar(cartaoNovo);
        System.out.println("Cartão cadastrado com secesso!");
    }

    public void listarCartoesUsuario(UsuarioEntity usuario) {
        if (usuario.getCartoes() == null || usuario.getCartoes().isEmpty()) {
            System.out.println("Nenhum Cartão Cadastrado");
        } else {
            System.out.println("==SEUS CARTOES CADASTRADOS==");
            for (int i = 0; i < usuario.getCartoes().size(); i++) {
                System.out.println("Id cartão: " + usuario.getCartoes().get(i).getId());
                System.out.println("Nome do cartão: " + usuario.getCartoes().get(i).getNomeCartao());
            }
        }
    }
}