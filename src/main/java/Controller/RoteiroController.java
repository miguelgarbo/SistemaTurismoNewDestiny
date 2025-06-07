package Controller;

import Model.Entidades.DiaEntity;
import Model.Entidades.PasseioEntity;
import Model.Entidades.RoteiroPersonalizadoEntity;
import Model.Entidades.UsuarioEntity;
import Model.Servicos.PasseioService;
import Model.Servicos.RoteiroPersonalizadoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class RoteiroController {
    
        private final RoteiroPersonalizadoService roteiroPersonalizadoService;
        private final PasseioService passeioService;
        private final PasseioController passeioController;

        private final Scanner sc = new Scanner(System.in);

    public RoteiroController(RoteiroPersonalizadoService roteiroPersonalizadoService, PasseioService passeioService, PasseioController passeioController) {
        this.roteiroPersonalizadoService = roteiroPersonalizadoService;
        this.passeioService = passeioService;
        this.passeioController = passeioController;
    }

    public void menuCadastro(UsuarioEntity usuario) {

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
            sc.nextLine();

            //criando os dias pra relacionar os passeios neles

            for (int i = 0; i < numeroDias; i++) {
                DiaEntity dia = new DiaEntity();

                dia.setNumeroDoDia(i+1);
                dia.setdataReal(dataInicioInformada.plusDays(i));
                roteiroNovo.adicionarDia(dia);
            }


        //agr nessa estrutura de repetição os passeios serao adicionados ao roteiro e logo em seguida aos dias se o usuario quiser
            do {
                passeioController.exibirTodosPasseios();
                System.out.println("Informe o ID do Passeio que você ira adiciona: ");
                Long idPasseioInformado = sc.nextLong();
                sc.nextLine();

                PasseioEntity passeioEscolhido = passeioService.buscarPorId(idPasseioInformado);

                if (passeioEscolhido != null) {

                    roteiroNovo.addPasseio(passeioEscolhido);
                    System.out.println("1- Adicionar o passeio em algum dia do Roteiro\n 2- Apenas adicionar o passeio ao roteiro");
                    int op = sc.nextInt();
                    sc.nextLine();

                    switch (op) {

                        case 1:

                            System.out.println("Informe o numero do dia em que o passeio pertencerá: ");
                            for(DiaEntity dia : roteiroNovo.getDias()){

                                System.out.println("Dia: "+ dia.getNumeroDoDia());
                                System.out.println(dia.getdataReal().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ": ");
                                System.out.println("------------------------------------");
                            }

                            int numeroDia = sc.nextInt();
                            sc.nextLine();

                            if (numeroDia >= 1 && numeroDia <= numeroDias) {
                                DiaEntity diaSelecionado = roteiroNovo.getDias().get(numeroDia - 1);

                                if (roteiroPersonalizadoService.validarPasseioNoDia(diaSelecionado, passeioEscolhido)) {
                                    System.out.println("Este passeio já está adicionado nesse dia!");
                                } else {
                                    diaSelecionado.addPasseio(passeioEscolhido);
                                    System.out.println("Passeio adicionado ao dia " + numeroDia);
                                }

                            } else {
                                System.out.println("Número de dia inválido.");
                            }

                            break;

                        case 2:
                            // O passeio já foi adicionado à lista geral do roteiro, não é necessário fazer nada aqui.
                            break;

                        default:
                            System.out.println("Opcao Invalida");
                    }

                } else {
                    System.out.println("Passeio nao encontrado");
                }
                System.out.println("Deseja Adicionar Mais Um Passeio? (sim/nao)");
                continuar = sc.nextLine().toLowerCase();

            } while (continuar.equals("sim"));

            roteiroNovo.setTitulo(tituloInformado);
            roteiroNovo.setusuario(usuario);
            roteiroNovo.setDataInicio(dataInicioInformada);
            roteiroPersonalizadoService.cadastrarRoteiro(roteiroNovo);
            usuario.getRoteirosCriados().add(roteiroNovo);

        System.out.println("Roteiro (" + roteiroNovo.getTitulo() + ") Cadastrado Com Sucesso !!");
        }


        public void exibirRoteiros(UsuarioEntity usuario) {

            if (usuario.getRoteirosCriados() != null && !usuario.getRoteirosCriados().isEmpty()) {
                System.out.println("==MEUS ROTEIROS==");

                for (RoteiroPersonalizadoEntity roteiro : usuario.getRoteirosCriados()) {
                    System.out.println("ID ROTEIRO: " + roteiro.getId());
                    System.out.println("Roteiro Titulo: " + roteiro.getTitulo());
                    System.out.println("Data de Inicio: " + roteiro.getDataInicio());
                    System.out.println("Numero de Dias: " + roteiro.getDias().size());

                    if (roteiro.getPasseios() == null || roteiro.getPasseios().isEmpty()) {
                        System.out.println("Sem Passeios Adicionados");
                    } else {
                        System.out.println("PASSEIOS ADICIONADOS AO ROTEIRO:: ");
                        for (PasseioEntity passeio : roteiro.getPasseios()) {
                            System.out.println("ID PASSEIO: " + passeio.getId());
                            System.out.println("Titulo Passeio: " + passeio.getTitulo());
                            System.out.println("Localização: " + passeio.getLocalizacao());
                        }
                    }
                    if (roteiro.getDias() != null && !roteiro.getDias().isEmpty()) {
                        System.out.println("Dias Do Roteiro: ");
                        for (DiaEntity dia : roteiro.getDias()) {
                            System.out.println("Dia " + dia.getdataReal().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ": ");

                            if (dia.getPasseios() != null && !dia.getPasseios().isEmpty()) {
                                for (PasseioEntity passeioPorDia : dia.getPasseios()) {
                                    System.out.println("- " + passeioPorDia.getTitulo());
                                }
                            } else {
                                System.out.println("- Nenhum passeio para este dia.");
                            }
                        }
                    } else {
                        System.out.println("Ainda não foram relacionados dias a este roteiro.");
                    }

                    System.out.println("//////////////////////////////////////////////////////////");
                }
            } else {
                System.out.println(usuario.getNome() + " você ainda não possui roteiros criados");
            }

        }

        public void exibirMeusRoteiros(UsuarioEntity usuario) {

            exibirRoteiros(usuario);

            System.out.println("1 - Gerenciar Algum Roteiro // 2 - Voltar");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:
                    menuGerenciarRoteiro();
                    break;

                case 2:
                    return;
                default:
                    System.out.println("case invalido");
                    break;

            }
        }


        public void adicionarPasseiosAosDias(RoteiroPersonalizadoEntity roteiro) {

            for (int i = 0; i < roteiro.getDias().size(); i++) {
                DiaEntity dia = roteiro.getDias().get(i);
                System.out.println("DIA " + (i + 1) + " - Data: " + dia.getdataReal().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                if (dia.getPasseios() != null && !dia.getPasseios().isEmpty()) {
                    for (PasseioEntity passeio : dia.getPasseios()) {
                        System.out.println("- " + passeio.getTitulo());
                    }
                } else {
                    System.out.println("- Nenhum passeio para este dia.");
                }
            }

            System.out.println("Selecione o número do dia para adicionar o passeio:");
            int diaSelecionado = sc.nextInt();
            sc.nextLine();

            if (diaSelecionado > 0 && diaSelecionado <= roteiro.getDias().size()) {
                DiaEntity dia = roteiro.getDias().get(diaSelecionado - 1);

                System.out.println("Dia escolhido para adicionar passeios: " + dia.getNumeroDoDia());

                passeioController.exibirTodosPasseios();

                System.out.println("Informe o ID do Passeio que deseja adicionar:");
                Long idPasseio = sc.nextLong();
                sc.nextLine();

                PasseioEntity passeioSelecionado = passeioService.buscarPorId(idPasseio);

                if (passeioSelecionado != null) {

                    if (!roteiroPersonalizadoService.validarPasseioNoDia(dia, passeioSelecionado)) {

                          roteiroPersonalizadoService.adicionarPasseioAoDia(dia, passeioSelecionado);
                         roteiroPersonalizadoService.atualizar(roteiro);

                        System.out.println("Passeio '" + passeioSelecionado.getTitulo() + "' adicionado ao dia " + diaSelecionado);
                    } else {
                        System.out.println("O passeio '" + passeioSelecionado.getTitulo() + "' já foi adicionado ao dia " + diaSelecionado + "!");
                    }

                } else {
                    System.out.println("Passeio inválido.");
                }
            } else {
                System.out.println("Número de dia inválido.");
            }
        }


        public void menuGerenciarRoteiro() {

            System.out.println("Informe o ID do roteiro: ");
            Long idRoteiroSelecionado = sc.nextLong();

            RoteiroPersonalizadoEntity roteiroEscolhido = roteiroPersonalizadoService.buscarPorId(idRoteiroSelecionado);

            System.out.println("ROTEIRO SELECIONADO: " + roteiroEscolhido.getTitulo());
            System.out.println("Informe o Numero Do Campo que vc gostaria de Modificar: \n");
            System.out.println("1 - TITULO : " + roteiroEscolhido.getTitulo());
            System.out.println("2 - DATA INICIO : " + roteiroEscolhido.getDataInicio());
            System.out.println("3 - GERENCIAR PASSEIOS DO ROTEIRO");
            System.out.println("4 - GERENCIAR OS DIAS\n");

            if (roteiroEscolhido.getPasseios() == null && roteiroEscolhido.getPasseios().isEmpty()) {
                System.out.println("Sem Passeios Adicionados");
            } else {
                System.out.println("Passeios Adicionados Ao Roteiro: ");
                for (PasseioEntity passeio : roteiroEscolhido.getPasseios()) {
                    System.out.println("ID PASSEIO: " + passeio.getId());
                    System.out.println("Titulo Passeio: " + passeio.getTitulo());
                    System.out.println("Localização: \n" + passeio.getLocalizacao());
                }
            }
            if (roteiroEscolhido.getDias() != null && !roteiroEscolhido.getDias().isEmpty()) {
                System.out.println("Dias do Roteiro: ");
                for (DiaEntity dia : roteiroEscolhido.getDias()) {
                    System.out.println("Dia " + dia.getdataReal().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ": ");

                    if (dia.getPasseios() != null && !dia.getPasseios().isEmpty()) {
                        for (PasseioEntity passeioPorDia : dia.getPasseios()) {
                            System.out.println("- " + passeioPorDia.getTitulo());
                        }
                    } else {
                        System.out.println("- Nenhum passeio para este dia.");
                    }
                }
            } else {
                System.out.println("Roteiro sem dias cadastrados.");
            }


            int campoSelecionado = sc.nextInt();
            sc.nextLine();

            switch (campoSelecionado) {

                case 1:
                    System.out.println("==ALTERAÇÃO NOME== ");
                    System.out.println("TITULO ATUAL: " + roteiroEscolhido.getTitulo());
                    System.out.println("Informe o Titulo Novo");
                    String nomeNovo = sc.nextLine();

                    roteiroEscolhido.setTitulo(nomeNovo);
                    roteiroPersonalizadoService.atualizar(roteiroEscolhido);
                    System.out.println("Titulo Trocado Com Sucesso " + nomeNovo);

                    break;

                case 2:

                    System.out.println("==ALTERAÇÃO DATA INICIO== ");
                    System.out.println("DATA INICIO ATUAL: " + roteiroEscolhido.getTitulo());
                    System.out.println("Informe a Data Nova: (formato: dia/mes/ano):");
                    String dataInicioTextoNova = sc.nextLine();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataInicioInformada = LocalDate.parse(dataInicioTextoNova, formatter);

                    roteiroEscolhido.setDataInicio(dataInicioInformada);
                    roteiroPersonalizadoService.atualizar(roteiroEscolhido);

                    System.out.println("Data De Inicio Trocada Com Sucesso");
                    break;

                case 3:
                    System.out.println("==PASSEIOS DO ROTEIRO== ");

                    for (PasseioEntity passeio : roteiroEscolhido.getPasseios()) {

                        System.out.println("ID PASSEIO: " + passeio.getId());
                        System.out.println("Titulo Passeio: " + passeio.getTitulo());
                        System.out.println("Localização: " + passeio.getLocalizacao());
                    }

                    System.out.println("1 - Add Passeio no Roteiro // 2 - Excluir Passeio do Roteiro");
                    int opcaoSN = sc.nextInt();
                    sc.nextLine();

                    switch (opcaoSN) {

                        case 1:

                            passeioController.exibirTodosPasseios();
                            System.out.println("Informe o ID do Passeio Para Add: ");
                            Long idPasseioAdd = sc.nextLong();

                            if (idPasseioAdd != null) {
                                PasseioEntity passeioAdded = passeioService.buscarPorId(idPasseioAdd);

                                roteiroEscolhido.addPasseio(passeioAdded);
                                roteiroPersonalizadoService.atualizar(roteiroEscolhido);
                                System.out.println("Passeio Adicionado Com Sucesso");

                            } else {

                                System.out.println("Id do Passeio  Invalido");
                            }

                            break;

                        case 2:

                            System.out.println("Informe o ID do Passeio Para Excluir ele Do Roteiro: ");
                            Long idPasseio = sc.nextLong();

                            if (idPasseio != null && idPasseio>=1) {

                                passeioService.removerPasseioRoteiroPorId(idPasseio, roteiroEscolhido);
                             roteiroPersonalizadoService.atualizar(roteiroEscolhido);

                                System.out.println("Passeio Excluido Com Sucesso");
                            } else {
                                System.out.println("Id do Passeio Nao Identificado");
                            }
                            break;
                        default:
                            System.out.println("Opcao Invalida");
                            break;
                    }
                    break;

                case 4:

                    System.out.println("==DIAS==");
                    System.out.println("Numero De Dias Atual: "+ roteiroEscolhido.getDias().size());
                    System.out.println("1 - Alterar Numero de Dias ");
                    System.out.println("2 - Add Passeios Aos Dias");
                    System.out.println("3 - Voltar ");
                    int op = sc.nextInt();
                    sc.nextLine();

                    switch (op){
                        case 1:

                            System.out.println("Numeros De Dias Novo: ");
                            int numeroDias = sc.nextInt();

                            if (numeroDias < 1) {
                                throw new IllegalArgumentException("O roteiro precisa ter pelo menos 1 dia.");
                            }
                            sc.nextLine();

                            roteiroEscolhido.getDias().clear();

                            for (int i = 0; i < numeroDias; i++) {
                                DiaEntity dia = new DiaEntity();
                                dia.setNumeroDoDia(i + 1); // começa do 1
                                dia.setdataReal(roteiroEscolhido.getDataInicio().plusDays(i));
                                roteiroEscolhido.adicionarDia(dia);
                            }

                         roteiroPersonalizadoService.atualizar(roteiroEscolhido);

                            System.out.println("Numero De Dias Modificado");
    
                            break;

                        case 2:
                            adicionarPasseiosAosDias(roteiroEscolhido);
                            break;

                        case 3:

                            break;

                        default:

                            System.out.println("opcao invalida");
                            break;

                }




                    break;
                default:
                    System.out.println("informação invalida");
                    break;
            }
        }
    
    }
