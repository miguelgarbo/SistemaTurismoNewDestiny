package Servicos;

import Entidades.CartaoEntity;
import Entidades.UsuarioEntity;

import java.util.Scanner;

public class CartaoSevice {

    private Scanner sc = new Scanner(System.in);

    public void cadastroCartão(UsuarioEntity usuario){
        System.out.println("DIgite o numero do cartão:");
        int numeroCartao = sc.nextInt();
        System.out.println("Digite o Dígito de verificação");
        int numeroVerificador = sc.nextInt();
        System.out.println("Digite método de pagamento: ");
        String métodoPagamento = sc.nextLine();
        System.out.println("Digite a Bandeira do Cartão:");
        String bandeira = sc.nextLine();

        CartaoEntity cartaoNovo  = new CartaoEntity();
        cartaoNovo.setNumeroCartão(numeroCartao);
        cartaoNovo.setDigitoVerificador(numeroVerificador);
        cartaoNovo.setBandeira(bandeira);
        cartaoNovo.setMétodo(métodoPagamento);

        usuario.addCartao(cartaoNovo);
        System.out.println("Cartão cadastrado com secesso!");
    }
    public void listarCartoes(UsuarioEntity usuario){
        for(int i=0; i<usuario.getCartões().size();i++){
            System.out.println("Id cartão: " +usuario.getCartões().get(i).getId());
            System.out.println("Numero do cartão: "+usuario.getCartões().get(i).getNumeroCartão());
        }
    }

}
