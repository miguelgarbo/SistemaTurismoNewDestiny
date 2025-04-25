package Servicos;

import Entidades.DiaEntity;
import Entidades.PasseioEntity;
import Entidades.RoteiroPersonalizadoEntity;
import Entidades.UsuarioEntity;
import Repositorio.PasseioRepository;
import Repositorio.RoteiroPersonalizadoRepository;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class RoteiroPersonalizadoService {

    private final RoteiroPersonalizadoRepository roteiroPersonalizadoRepository;

    private  final PasseioService passeioService;

    private final PasseioRepository passeioRepository;

    private final Scanner sc = new Scanner(System.in);

    public RoteiroPersonalizadoService(RoteiroPersonalizadoRepository roteiroPersonalizadoRepository, PasseioService passeioService, PasseioRepository passeioRepository) {
        this.roteiroPersonalizadoRepository = roteiroPersonalizadoRepository;
        this.passeioService = passeioService;
        this.passeioRepository = passeioRepository;
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

        String continuar;
        RoteiroPersonalizadoEntity roteiroNovo = new RoteiroPersonalizadoEntity();

        System.out.println("==CADASTRO DE ROTEIRO==");
        System.out.println("Informe O Titulo do Roteiro: ");
        String tituloInformado = sc.nextLine();

        System.out.println("Informe a Data de Início (formato: dia/mes/ano): ");
        String dataInicioTexto = sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicioInformada = LocalDate.parse(dataInicioTexto, formatter);

        System.out.println("Numeros De Dias que voce vai passar: ");
        int numeroDias = sc.nextInt();

        if (numeroDias < 1) {
            throw new IllegalArgumentException("O roteiro precisa ter pelo menos 1 dia.");
        }
        sc.nextLine();

        //criando os dias pra relacionar os passeios neles

        for(int i = 0;i<=numeroDias;i++){
            DiaEntity dia = new DiaEntity();

            dia.setNumeroDoDia(i);
            dia.setdataReal(dataInicioInformada.plusDays(i-1));//definindo data dos dias
            roteiroNovo.adicionarDia(dia);
        }

        //agr nessa estrutura de repetição os passeios serao adicionados ao roteiro e logo em seguida aos dias se o usuario quiser
        do {
            passeioService.mostrarTodosPasseios(passeioRepository.buscarTodos());
            System.out.println("Informe o ID do Passeio que você ira adiciona: ");
            Long idPasseioInformado = sc.nextLong();
            sc.nextLine(); //quebra linha

            PasseioEntity passeioEscolhido = passeioRepository.findById(idPasseioInformado);

            if(passeioEscolhido != null){

                roteiroNovo.addPasseio(passeioEscolhido);
                System.out.println("1- Adicionar o passeio em algum dia de seu Roteiro\n 2- Apenas adicionar o passeio ao roteiro");
                int op = sc.nextInt();
                sc.nextLine();


                switch (op){

                    case 1:

                        System.out.println("Informe o numero do dia em que o passeio pertence: ");
                        int numeroDia = sc.nextInt();
                        sc.nextLine();

                        if(numeroDia >= 1 && numeroDia <= numeroDias){

                            DiaEntity diaSelecionado = roteiroNovo.getDias().get(numeroDia-1);
                            diaSelecionado.addPasseio(passeioEscolhido);
                        }

                        break;

                    case 2:
                        // O passeio já foi adicionado à lista geral do roteiro, não é necessário fazer nada aqui.
                        break;

                    default:
                        System.out.println("Opcao Invalida");
                }

            }else{
                System.out.println("Passeio nao encontrado");
            }
            System.out.println("Deseja Adicionar Mais Um Passeio? (sim/nao)");
            continuar = sc.nextLine().toLowerCase();

        }while (continuar.equals("sim"));

        roteiroNovo.setTitulo(tituloInformado);
        roteiroNovo.setusuario(usuario);
        roteiroNovo.setDataInicio(dataInicioInformada);
        cadastrarRoteiro(roteiroNovo);
        System.out.println("Roteiro ("+ roteiroNovo.getTitulo()+ ") Cadastrado Com Sucesso !!");

    }

    public void mostrarMeusRoteiros(UsuarioEntity usuario){

        if(usuario.getRoteirosCriados() != null || !usuario.getRoteirosCriados().isEmpty()) {


            System.out.println("==MEUS ROTEIROS==");
            for (RoteiroPersonalizadoEntity roteiro : usuario.getRoteirosCriados()) {
                System.out.println("ID ROTEIRO: " + roteiro.getId());
                System.out.println("Roteiro Titulo: " + roteiro.getTitulo());
                System.out.println("Data de Inicio: " + roteiro.getDataInicio());
                System.out.println("Numero de Dias: " + roteiro.getDias().size());

                if (roteiro.getPasseios() == null || roteiro.getPasseios().isEmpty()) {
                    System.out.println("Sem Passeios Adicionados");
                } else {
                    System.out.println("Passeios Adicionados Ao Roteiro: ");
                    for (PasseioEntity passeio : roteiro.getPasseios()) {
                        System.out.println("ID PASSEIO: " + passeio.getId());
                        System.out.println("Titulo Passeio: " + passeio.getTitulo());
                        System.out.println("Localização: " + passeio.getLocalizacao());
                    }
                }

                if (roteiro.getDias() != null && !roteiro.getDias().isEmpty()) {

                    System.out.println("Passeios Relacionados aos Dias do Roteiro: ");

                    for (DiaEntity dia : roteiro.getDias()) {

                        if(dia.getPasseios() != null && !dia.getPasseios().isEmpty()){

                            System.out.println("Dia " + dia.getdataReal().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ": ");
                            for (PasseioEntity passeioPorDia : dia.getPasseios()) {
                                System.out.println("- " + passeioPorDia.getTitulo());
                            }
                        }
                    }
                } else {

                    System.out.println("Ainda Nao Foi relacionado os dias com os passeios");
                }

                System.out.println("//////////////////////////////////////////////////////////");
            }
        }
    }

    public void opcoesRoteiro(UsuarioEntity usuario){

        mostrarMeusRoteiros(usuario);

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

        System.out.println("Informe o ID do Roteiro pra Gerencia-lo:");
        Long idRoteiro = sc.nextLong();
        sc.nextLine();

        RoteiroPersonalizadoEntity roteiro = roteiroPersonalizadoRepository.findById(idRoteiro);

        if (roteiro == null) {
            System.out.println("Roteiro não encontrado.");
            return;
        }

        System.out.println("ID ROTEIRO SELECIONADO: " + roteiro.getId());
        System.out.println("TÍTULO: " + roteiro.getTitulo());
        System.out.println("NÚMERO DE DIAS: " + roteiro.getDias().size());

        System.out.println("PASSEIOS INCLUSOS:");
        for (PasseioEntity passeio : roteiro.getPasseios()) {
            System.out.println("ID PASSEIO: " + passeio.getId());
            System.out.println("Título Passeio: " + passeio.getTitulo());
            System.out.println("Localização: " + passeio.getLocalizacao());
        }

        for (int i = 0; i < roteiro.getDias().size(); i++) {
            DiaEntity dia = roteiro.getDias().get(i);
            System.out.println("DIA " + (i + 1) + " - Data: " + dia.getdataReal().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }

        System.out.println("Selecione o número do dia para adicionar o passeio:");
        int diaSelecionado = sc.nextInt();
        sc.nextLine();

        if (diaSelecionado > 0 && diaSelecionado <= roteiro.getDias().size()) {
            DiaEntity dia = roteiro.getDias().get(diaSelecionado - 1);

            System.out.println("Dia escolhido para adicionar passeios: " + diaSelecionado);

            System.out.println("Informe o ID do Passeio que deseja adicionar:");
            Long idPasseio = sc.nextLong();
            sc.nextLine();

            PasseioEntity passeioSelecionado = passeioRepository.findById(idPasseio);

            if (passeioSelecionado != null) {
                boolean passeioNoRoteiro = roteiro.getPasseios().contains(passeioSelecionado);

                if (passeioNoRoteiro) {
                    dia.addPasseio(passeioSelecionado);
                    System.out.println("Passeio '" + passeioSelecionado.getTitulo() + "' adicionado ao dia " + diaSelecionado);
                } else {
                    System.out.println("Esse passeio ainda não foi adicionado ao roteiro. Adicione ele ao roteiro primeiro.");
                }
            } else {
                System.out.println("Passeio inválido.");
            }

        } else {
            System.out.println("Número de dia inválido.");
        }
    }

}
