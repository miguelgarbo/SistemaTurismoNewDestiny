package Controller;

import Model.Entidades.AdministradorEntity;

import Servicos.AdministradorService;

import java.util.Scanner;

public class AdmnistradorController {

        private final AdministradorService administradorService;

        private final PacoteController pacoteController;

        private final PasseioController passeioController;

        private final UsuarioController usuarioController;

        private final CategoriaController categoriaController;

        private AdministradorEntity admLogged;

        private final Scanner sc = new Scanner(System.in);

    public AdmnistradorController(AdministradorService administradorService, PacoteController pacoteController, PasseioController passeioController, UsuarioController usuarioController, CategoriaController categoriaController) {
        this.administradorService = administradorService;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
        this.usuarioController = usuarioController;
        this.categoriaController = categoriaController;
    }

    public void menuLoginADM() {
        System.out.println("==LOGIN ADMINISTRADOR==");
        System.out.println("Digite seu email: ");
        String email = sc.nextLine();
        System.out.println("Digite sua senha: ");
        String senha = sc.nextLine();

        AdministradorEntity admLogado = administradorService.login(email, senha);

        if (admLogado != null) {
            System.out.println("Login realizado com sucesso. Seja bem-vindo, " + admLogado.getNome() + "!");
            setadmLogged(admLogado);
            menuAdministrador();
        } else {
            System.out.println("Email ou senha incorretos.");
        }
    }

        public void menuCadastrarAdm(){

            System.out.println("==CADASTRO DE ADMINISTRADOR==");
            System.out.println("Informe seu Nome: ");
            String nomeInformado = sc.nextLine().toLowerCase();

            System.out.println("Informe Seu Email: ");
            String emailInformado = sc.nextLine();

            if (administradorService.isEmailRegistrado(emailInformado)) {
                return;
            }

            System.out.println("Informe sua Senha: ");
            String senhaInformada = sc.nextLine();

            AdministradorEntity admNovo = new AdministradorEntity();

            admNovo.setNome(nomeInformado);
            admNovo.setEmail(emailInformado);
            admNovo.setSenha(senhaInformada);

            administradorService.cadastrarAdm(admNovo);
            System.out.println("Adm Cadastrado Com Sucesso !, Seja Bem Vindo(a) " + admNovo.getNome());

        }

        public boolean isEmailRegistrado(String email) {

            for (AdministradorEntity adm : administradorService.buscarTodos()) {
                if (adm.getEmail().equals(email)) {
                    System.out.println("Esse Email Já tem Cadastro. Tente Seu Login Com Ele ");
                    return true;
                }
            }
            return false;
        }

        public void menuAdministrador() {

            if (getadmLogged() != null) {
                int opcao;
                do {
                    System.out.println("\n==MENU ADMINISTRADOR==");
                    System.out.println("0 - Cadastrar Administrador");
                    System.out.println("1 - Gerenciar Pacotes Turísticos");
                    System.out.println("2 - Gerenciar Passeios");
                    System.out.println("3 - Gerenciar Usuários");
                    System.out.println("4 - Gerenciar Categorias");
                    System.out.println("5 - Sair da Conta");
                    System.out.println("6 - Sair");
                    System.out.println("Escolha uma opção: ");

                    opcao = sc.nextInt();
                    sc.nextLine();

                    switch (opcao) {
                        case 0:
                            menuCadastrarAdm();

                            break;

                        case 1:
                            menuGerenciarPacotes();
                            break;
                        case 2:
                            menuGerenciarPasseios();
                            break;
                        case 3:
                            menuGerenciarUsuarios();
                            break;

                        case 4:
                            menuGerenciarCategorias();
                            break;

                        case 5:
                            System.out.println("Saindo Do ADM: "+ admLogged.getNome());
                            setadmLogged(null);
                            break;
                        case 6:
                            System.out.println("Saindo do menu administrador...");
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;

                    }
                } while (opcao != 5);
            }else {

                menuLoginADM();
            }
        }

        public void menuGerenciarPacotes() {
            int opcao;


            System.out.println("\n==== GERENCIAR PACOTES TURÍSTICOS ====");
            System.out.println("1 - Cadastrar Pacote Turístico");
            System.out.println("2 - Listar Pacotes Turísticos Cadastrados");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar o buffer

            switch (opcao) {
                case 1:
                    pacoteController.menuCadastrarPacote();
                    break;
                case 2:
                    pacoteController.exibirPacotesDisponiveis();
                    break;
                case 3:
                    System.out.println("Saindo...");
                default:
                    System.out.println("Opção inválida!");
            }

        }

        public void menuGerenciarPasseios() {
            System.out.println("\n==GERENCIAR PASSEIOS==");
            int opcao;
            System.out.println("1 - Cadastrar Passeio");
            System.out.println("2 - Listar Passeios Cadastrados");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    passeioController.cadastrarPasseio();
                    break;
                case 2:
                    passeioController.exibirTodosPasseios();
                    break;
                case 3:
                    System.out.println("Saindo...");
                default:
                    System.out.println("Opção inválida!");
            }

        }

        public void menuGerenciarUsuarios(){

            System.out.println("\n==GERENCIAR USUARIOS==");
            int opcao;

            System.out.println("1 - Listar Usuarios Cadastrados");
            System.out.println("2 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    usuarioController.listarUsuariosCadastrados();
                    break;
                case 2:
                    System.out.println("Saindo...");
                default:
                    System.out.println("Opção inválida!");
            }


        }

        public void menuGerenciarCategorias(){

            System.out.println("\n==GERENCIAR CATEGORIAS==");
            int opcao;

            System.out.println("1 - Cadastrar Categorias");
            System.out.println("2 - Listar Categorias");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    categoriaController.menuCadastrarCategoria();
                    break;
                case 2:
                    categoriaController.exibirTodasCategorias();
                case 3:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        }

        public AdministradorEntity getadmLogged() {
            return admLogged;
        }

        public void setadmLogged(AdministradorEntity admLogged) {
            this.admLogged = admLogged;
        }
    }

