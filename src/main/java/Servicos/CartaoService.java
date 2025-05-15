package Servicos;

import Model.Entidades.*;
import Repositorio.CartaoRepositorio;

import javax.transaction.Transactional;

public class CartaoService {

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

    public CartaoEntity findById(Long id) {
        return cartaoRepositorio.findById(id);
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

    public EBandeirasCartao[] buscarBandeirasDisponiveis(){
       return EBandeirasCartao.values();
    }

    public EMetodosPagamento[] buscarMetodosPag(){
        return EMetodosPagamento.values();
    }


}
