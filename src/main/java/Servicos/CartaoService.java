package Servicos;

import Entidades.*;
import Repositorio.CartaoRepositorio;

import javax.transaction.Transactional;
import java.util.Scanner;

public class CartaoService {

    private final Scanner sc = new Scanner(System.in);
    private  final CartaoRepositorio cartaoRepositorio;

    public CartaoService(CartaoRepositorio cartaoRepositorio) {
        this.cartaoRepositorio = cartaoRepositorio;
    }

    @Transactional
    public void cadastrar(CartaoEntity cartao) {
        if(cartao.getNomeCartao() == null || cartao.getNomeCartao().isEmpty()){
            cartao.setNomeCartao("Cartão Comum");
        }

        if (cartao.getUsuario() == null) {
            throw new IllegalArgumentException("Não é possível criar cartão sem estar logado.");
        }

        if (cartao.getNumeroCartao() == null || cartao.getNumeroCartao().isEmpty()) {
            throw new IllegalArgumentException(" não pode estar vazio");
        }

        if (cartao.getDigitoVerificador() == null || cartao.getDigitoVerificador().isEmpty()) {
            throw new IllegalArgumentException(" não pode estar vazio");
        }
        cartaoRepositorio.cadastrar(cartao);
    }

    public void cadastroCartao(UsuarioEntity usuario){
        CartaoEntity cartaoNovo  = new CartaoEntity();

        EBandeirasCartao[] cartaoBandeiras = EBandeirasCartao.values();
        EMetodosPagamento[] metodosPagamentos = EMetodosPagamento.values();

        System.out.println("==CADASTRO DE CARTAO==");
        System.out.println("Informe um nome ao Cartao: ");
        String nomeCartao = sc.nextLine();

        System.out.println("DIgite o numero do cartão:");
        String numeroCartao = sc.nextLine();
        System.out.println("Digite o Dígito de verificação");
        String numeroVerificador = sc.nextLine();

        System.out.println("Digite método de pagamento pelo número: ");

        for(int i= 0 ;i<metodosPagamentos.length;i++){
            System.out.println(i+ "-" + metodosPagamentos[i]);
        }
        int metodoPagamento = sc.nextInt();

        if(metodoPagamento == 0 ){

            cartaoNovo.setmetodo(EMetodosPagamento.débito.name());
        } else if(metodoPagamento == 1){

            cartaoNovo.setmetodo(EMetodosPagamento.crédito.name());
        }else{


            System.out.println("Metodo Invalido");
        }


        System.out.println("Digite a Bandeira do Cartão De Acordo Com essas Abaixo:");

        for(int i =0;i< cartaoBandeiras.length;i++){

            System.out.println(i+ "-"+ cartaoBandeiras[i]);
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
        cadastrar(cartaoNovo);
        System.out.println("Cartão cadastrado com secesso!");
    }
    public void listarCartoes(UsuarioEntity usuario){
        if (usuario.getCartoes() == null || usuario.getCartoes().isEmpty()) {
            System.out.println("Nenhum Cartão Cadastrado");
            return;
        }

        for(int i=0; i<usuario.getCartoes().size();i++){
            System.out.println("Id cartão: " +usuario.getCartoes().get(i).getId());
            System.out.println("Nome do cartão: "+usuario.getCartoes().get(i).getNomeCartao());
        }
    }

}
