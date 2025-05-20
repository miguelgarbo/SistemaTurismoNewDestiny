package Controller;

import Model.Entidades.AdministradorEntity;
import Model.Entidades.UsuarioEntity;
import Servicos.UsuarioService;
import View.Main;
import View.MenuPrincipal;

import java.util.Scanner;

public class UsuarioController {

    private final UsuarioService service;
    private final RoteiroController roteiroController;
    private final PasseioController passeioController;
    private final PacoteController pacoteController;
    private final CartaoController cartaoController;
    private final PagamentoController pagamentoController;
    private UsuarioEntity getUserLogged; // usuário logado

    private final Scanner sc = new Scanner(System.in);

    public UsuarioController(UsuarioService service, RoteiroController roteiroController, PasseioController passeioController, PacoteController pacoteController, CartaoController cartaoController, PagamentoController pagamentoController) {
        this.service = service;
        this.roteiroController = roteiroController;
        this.passeioController = passeioController;
        this.pacoteController = pacoteController;
        this.cartaoController = cartaoController;
        this.pagamentoController = pagamentoController;
  }

    public UsuarioEntity getUserLogged() {
        return getUserLogged;
    }

    public void setgetUserLogged(UsuarioEntity getUserLogged) {
        this.getUserLogged = getUserLogged;
    }

    public void menuLogin() {
        System.out.println("== LOGIN DE USUARIO ==");
        System.out.print("Digite seu email: ");
        String email = sc.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine();

        UsuarioEntity usuarioLogado = service.login(email, senha);

        if (usuarioLogado != null) {
            System.out.println("Login realizado com sucesso. Seja bem-vindo, " + usuarioLogado.getNome() + "!");
            this.getUserLogged = usuarioLogado;
            menuUsuario();
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }

    public void menuCadastroUsuario() {
        System.out.println("== CADASTRO DE USUÁRIO ==");

        System.out.print("Informe seu Nome: ");
        String nome = sc.nextLine().toLowerCase();

        System.out.print("Informe seu Email: ");
        String email = sc.nextLine();

        if (service.isEmailRegistrado(email)) {
            System.out.println("Este email já está cadastrado!");
            return;
        }

        System.out.print("Informe seu Número de Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Informe sua Senha: ");
        String senha = sc.nextLine();

        try {
            UsuarioEntity novoUsuario = new UsuarioEntity();
            novoUsuario.setNome(nome);
            novoUsuario.setEmail(email);
            novoUsuario.setNumeroTelefone(telefone);
            novoUsuario.setSenha(senha);

            service.cadastrarUsuario(novoUsuario);

            setgetUserLogged(novoUsuario);
            System.out.println("Usuário cadastrado com sucesso! Seja bem-vindo(a), " + nome);
            menuUsuario();

        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    private void menuCompras(){
        int opcao = -1;

        while (opcao != 3) {
            System.out.println("\n== PACOTES E PASSEIOS ==");
            System.out.println("1 - Visualizar Pacotes Disponíveis");
            System.out.println("2 - Visualizar Passeios Disponíveis");
            System.out.println("3 - Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    if(getUserLogged()!=null){
                        pacoteController.exibirPacotesDisponiveis(getUserLogged());
                    }else{
                        pacoteController.exibirPacotesDisponiveis();
                    }
                    break;
                case 2:
                    if(getUserLogged()!=null){

                        passeioController.exibirTodosPasseios(getUserLogged());
                    }else{
                        passeioController.exibirTodosPasseios();
                    }
                    break;
                case 3:
                    System.out.println("Voltando ao menu anterior...");
                    menuUsuario();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void menuRoteiros() {
        int opcao = -1;

        while (opcao != 3) {
            System.out.println("\n== ROTEIROS PERSONALIZADOS ==");
            System.out.println("1 - Criar Roteiro de Viagem");
            System.out.println("2 - Gerenciar Meus Roteiros");
            System.out.println("3 - Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    roteiroController.menuCadastro(getUserLogged);
                    break;
                case 2:
                    roteiroController.exibirMeusRoteiros(getUserLogged);
                    break;
                case 3:
                    menuUsuario();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void menuContaUsuario() {
        int opcao = -1;

        while (opcao != 5) {
            System.out.println("\n== MINHA CONTA ==");
            System.out.println("1 - Editar Meus Dados");
            System.out.println("2 - Cadastrar Cartão");
            System.out.println("3 - Visualizar Meus Cartões");
            System.out.println("4 - Visualizar Pagamentos / Pedidos");
            System.out.println("5 - Sair Da Conta");
            System.out.println("6 - Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    editarDadosUsuario();
                    break;
                case 2:
                   cartaoController.menuCadastroCartao(getUserLogged);
                    break;
                case 3:
                    cartaoController.listarCartoesUsuario(getUserLogged);
                    break;
                case 4:
                    menuPagamentos();
                    break;

                case 5:
                    System.out.println("Tchau "+ getUserLogged.getNome());
                    System.out.println("Saindo da Conta");
                    setgetUserLogged(null);
                    Main main = new Main();
                    break;
                case 6:
                    System.out.println("Voltando ao menu anterior...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    private void menuPagamentos() {
        int opcao = -1;

        while (opcao != 3) {
            System.out.println("\n== PAGAMENTOS E PEDIDOS ==");
            System.out.println("1 - Visualizar Meu Historico de Pagamentos");
            System.out.println("2 - Visualizar Pedidos Pendentes");
            System.out.println("3 - Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    pagamentoController.exibirHistoricoPagamentos(getUserLogged);
                    break;
                case 2:
                    pagamentoController.exibirPedidosPendentes(getUserLogged);
                    break;
                case 3:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }



    public void menuUsuario() {

        int opcao = -1;

        while (opcao != 4) {
            System.out.println("\n== MENU USUÁRIO ==");
            System.out.println("1 - Pacotes e Passeios");
            System.out.println("2 - Roteiros Personalizados");
            System.out.println("3 - Minha Conta");
            System.out.println("4 - Sair");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    menuCompras();
                    break;
                case 2:
                    menuRoteiros();
                    break;
                case 3:
                    menuContaUsuario();
                    break;
                case 4:
                    System.out.println("Tchau..."+ getUserLogged().getNome());
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }


    public void editarDadosUsuario() {
        if (getUserLogged == null) {
            System.out.println("Você precisa estar logado para alterar suas informações.");
            return;
        }

        System.out.println("== ALTERAR DADOS ==");
        System.out.println("1 - Alterar Nome");
        System.out.println("2 - Alterar Senha");
        System.out.println("3 - Alterar Telefone");

        int opcao = sc.nextInt();
        sc.nextLine();

        System.out.print("Digite o novo valor: ");
        String novoValor = sc.nextLine();

        try {
            service.alterarInformacoesUsuario(getUserLogged, opcao, novoValor);
            System.out.println("Dados alterados com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao alterar dados: " + e.getMessage());
        }
    }

    public void listarUsuariosCadastrados() {
        
        for (UsuarioEntity usuario : service.buscarTodos()) {
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Número Telefone: " + usuario.getNumeroTelefone());
            roteiroController.exibirMeusRoteiros(usuario);
        }
    }
}
