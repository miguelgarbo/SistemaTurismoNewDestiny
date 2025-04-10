package Servicos;

import Entidades.PasseioEntity;
import Entidades.RoteiroPersonalizadoEntity;
import Entidades.UsuarioEntity;
import Repositorio.PasseioRepository;
import Repositorio.RoteiroPersonalizadoRepository;

import javax.inject.Inject;
import java.util.Scanner;

public class RoteiroPersonalizadoService {

    @Inject
    private RoteiroPersonalizadoRepository roteiroPersonalizadoRepository;

    @Inject
    private PasseioService passeioService;

    @Inject
    private PasseioRepository passeioRepository;
    private Scanner sc = new Scanner(System.in);

    public void cadastrarRoteiro(RoteiroPersonalizadoEntity roteiro){

        if(roteiro.getTitulo() == null || roteiro.getTitulo().isEmpty()){

            throw new IllegalArgumentException("Nome não pode ser Vazio");
        }

        if(roteiro.getusuario() == null){

            throw new IllegalArgumentException("O Roteiro deve pertenter a um usuario");
        }

        roteiroPersonalizadoRepository.cadastrar(roteiro);
    }

    public void menuCadastro(UsuarioEntity usuario){

        String continuar = "sim";
        RoteiroPersonalizadoEntity roteiroNovo = new RoteiroPersonalizadoEntity();

        System.out.println("==CADASTRO DE ROTEIRO==");
        System.out.println("Informe O Titulo do Roteiro: ");
        String tituloInformado = sc.nextLine().toLowerCase();

        System.out.println("Numeros De Dias: ");
        Long numeroDias = sc.nextLong();

        sc.nextLine();

        do {
            passeioService.mostrarTodosPasseios(passeioRepository.buscarTodos());
            System.out.println("Informe o ID do Passeio que você ira adiciona: ");
            Long idPasseioInformado = sc.nextLong();
            sc.nextLine(); //quebra linha

            PasseioEntity passeioEscolhido = passeioRepository.findById(idPasseioInformado);

            if(passeioEscolhido != null){
                roteiroNovo.addPasseio(passeioEscolhido);
            }else{
                System.out.println("Passeio nao encontrado");
            }
            System.out.println("Deseja Adicionar Mais Um Passeio? (sim/nao)");
            continuar = sc.nextLine().toLowerCase();

        }while (!continuar.equals("sim"));

        System.out.println("Informe Seu Numero De Telefone (Para Contato): ");
        String telefoneInformado = sc.nextLine();



        roteiroNovo.setTitulo(tituloInformado);
        roteiroNovo.setNumeroDias(numeroDias);
        roteiroNovo.setusuario(usuario);
        cadastrarRoteiro(roteiroNovo);
        System.out.println("Roteiro ("+ roteiroNovo.getTitulo()+ ") Cadastrado Com Sucesso !!");
        System.out.println(roteiroNovo.toString());

    }

}
