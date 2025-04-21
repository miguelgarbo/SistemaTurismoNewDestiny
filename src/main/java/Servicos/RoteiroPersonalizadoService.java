package Servicos;

import Entidades.PasseioEntity;
import Entidades.RoteiroPersonalizadoEntity;
import Entidades.UsuarioEntity;
import Repositorio.PasseioRepository;
import Repositorio.RoteiroPersonalizadoRepository;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

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

    public RoteiroPersonalizadoService(PasseioRepository passeioRepository, PasseioService passeioService, RoteiroPersonalizadoRepository roteiroPersonalizadoRepository) {
        this.passeioRepository = passeioRepository;
        this.passeioService = passeioService;
        this.roteiroPersonalizadoRepository = roteiroPersonalizadoRepository;
    }

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

        }while (continuar.equals("sim"));

        roteiroNovo.setTitulo(tituloInformado);
        roteiroNovo.setNumeroDias(numeroDias);
        roteiroNovo.setusuario(usuario);
        cadastrarRoteiro(roteiroNovo);
        System.out.println("Roteiro ("+ roteiroNovo.getTitulo()+ ") Cadastrado Com Sucesso !!");

    }

    public void mostrarMeusRoteiros(UsuarioEntity usuario){

        System.out.println("==MEUS ROTEIROS==");
        for(RoteiroPersonalizadoEntity roteiro : usuario.getRoteirosCriados()){
            System.out.println("ID ROTEIRO: "+ roteiro.getId());
            System.out.println("Roteiro Titulo: "+ roteiro.getTitulo());
            System.out.println("Numero de Dias: "+ roteiro.getNumeroDias());
            if(usuario.getRoteirosCriados() == null || usuario.getRoteirosCriados().isEmpty()){

                System.out.println("Sem Passeios Adicionados");
            }else{

                System.out.println("Passeios Adicionados: ");
                for(PasseioEntity passeio : roteiro.getPasseios()){
                    System.out.println("ID PASSEIO: "+ passeio.getId());
                    System.out.println("Titulo Passeio: "+ passeio.getTitulo());
                    System.out.println("Localização: "+ passeio.getLocalizacao());
                }

            }

            System.out.println("//////////////////////////////////////////////////////////");
        }

        System.out.println("1 - Relacionar os Passeios aos dias, 2 - Voltar");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao){

            case 1:

                gerenciarRoteiro();

                break;

            case 2:
                System.out.println("voltando");
                break;

        }


    }


    public void gerenciarRoteiro(){

        System.out.println("Informe o ID do Roteiro pra Gerencia-lo");
        Long idRoteiro = sc.nextLong();

        sc.nextLine();

        RoteiroPersonalizadoEntity roteiroPersonalizadoNovo = roteiroPersonalizadoRepository.findById(idRoteiro);

        System.out.println("ID ROTEIRO SELECIONADO: "+ roteiroPersonalizadoNovo.getId());
        System.out.println("TITULO: "+ roteiroPersonalizadoNovo.getTitulo());
        System.out.println("NUMERO DIAS: "+ roteiroPersonalizadoNovo.getNumeroDias());
        System.out.println("PASSEIOS INCLUSOS: ");

        for (PasseioEntity passeio : roteiroPersonalizadoNovo.getPasseios()){

            System.out.println("ID PASSEIO: "+ passeio.getId());
            System.out.println("Titulo Passeio: "+ passeio.getTitulo());
            System.out.println("Localização: "+ passeio.getLocalizacao());
        }

        for(int i = 0;i<roteiroPersonalizadoNovo.getNumeroDias();i++){

            System.out.println("DIA: "+ (i+1));
        }
        System.out.println("Selecione o número do dia para adicionar os passeio");
        int diaSelecionado = sc.nextInt();

        sc.nextLine();

        if(diaSelecionado > 0 && diaSelecionado<= roteiroPersonalizadoNovo.getNumeroDias()){
            System.out.println("Dia Escolhido Para Adicionar Passeios: "+ diaSelecionado);

            for (PasseioEntity passeio : roteiroPersonalizadoNovo.getPasseios()){

                System.out.println("ID PASSEIO: "+ passeio.getId());
                System.out.println("Titulo Passeio: "+ passeio.getTitulo());
                System.out.println("Localização: "+ passeio.getLocalizacao());
            }
            System.out.println("Informe o ID do Passeio: ");
            Long idPasseio = sc.nextLong();
            sc.nextLine();

            PasseioEntity passeioSelecionado = passeioRepository.findById(idPasseio);

            if(passeioSelecionado != null){

                boolean passeioNoRoteiro = roteiroPersonalizadoNovo.getPasseios().contains(passeioSelecionado);

                if(passeioNoRoteiro){

                    roteiroPersonalizadoNovo.adicionarPasseioAoDia(diaSelecionado, passeioSelecionado);

                    System.out.println("Passeio: "+ passeioSelecionado.getTitulo()+ " adicionado ao dia "+ diaSelecionado);
                }else{
                    System.out.println("Esse Passeio não foi adicionado a esse roteiro. Adicione o Passeio Primeiro.");
                }

            }else{

                System.out.println("Passeio Invalido");
            }


        }

    }

}
