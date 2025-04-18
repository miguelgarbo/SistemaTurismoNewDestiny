package Servicos;

import Entidades.AdministradorEntity;
import Repositorio.AdministradorRepository;
import Repositorio.PacoteTuristicoRepository;
import Repositorio.PasseioRepository;
import Repositorio.UsuarioRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Scanner;

public class AdministradorService {
    @Inject
    private AdministradorRepository administradorRepository;

    @Inject
    private PacoteTuristicoRepository pacoteTuristicoRepository;

    @Inject
    private PasseioRepository passeioRepository;

    @Inject
    private UsuarioRepository usuarioRepository;

    @Inject
    private PacoteTuristicoService pacoteTuristicoService;

    @Inject
    private PasseioService passeioService;

    @Inject
    private UsuarioService usuarioService;

    private Scanner sc = new Scanner(System.in);

    public AdministradorService(AdministradorRepository administradorRepository,
                                PacoteTuristicoRepository pacoteTuristicoRepository,
                                PasseioRepository passeioRepository,
                                UsuarioRepository usuarioRepository, PacoteTuristicoService pacoteTuristicoService, PasseioService passeioService, UsuarioService usuarioService) {
        this.administradorRepository = administradorRepository;
        this.pacoteTuristicoRepository = pacoteTuristicoRepository;
        this.passeioRepository = passeioRepository;
        this.usuarioRepository = usuarioRepository;
        this.pacoteTuristicoService = pacoteTuristicoService;
        this.passeioService = passeioService;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public void cadastrarAdm(AdministradorEntity adm) {

        if (adm.getNome() == null || adm.getNome().isEmpty()) {

            throw new IllegalArgumentException("Nome não pode ser Vazio");
        }

        if (adm.getEmail() == null || adm.getEmail().isEmpty()) {

            throw new IllegalArgumentException("Email não pode ser Vazio");
        }

        administradorRepository.cadastrar(adm);
    }

    public boolean login() {
        System.out.println("==LOGIN ADMINISTRADOR==");
        System.out.println("Digite seu email: ");
        String email = sc.nextLine();
        System.out.println("Digite sua senha: ");
        String senha = sc.nextLine();

        AdministradorEntity admin = administradorRepository.buscarPorEmail(email);
        if (admin != null && admin.getSenha().equals(senha)) {
            System.out.println("Login realizado com sucesso! Bem-vindo, " + admin.getNome());
            return true;
        } else {
            System.out.println("Email ou senha incorretos!");
            return false;
        }
    }

    public void menuCadastrarAdm(){

            System.out.println("==CADASTRO DE USUARIO==");
            System.out.println("Informe seu Nome: ");
            String nomeInformado = sc.nextLine().toLowerCase();

            System.out.println("Informe Seu Email: ");
            String emailInformado = sc.nextLine();

            if (isEmailRegistrado(emailInformado)) {
                return;
            }

            System.out.println("Informe sua Senha: ");
            String senhaInformada = sc.nextLine();

            AdministradorEntity admNovo = new AdministradorEntity();

            admNovo.setNome(nomeInformado);
            admNovo.setEmail(emailInformado);
            admNovo.setSenha(senhaInformada);

            cadastrarAdm(admNovo);
            System.out.println("Adm Cadastrado Com Sucesso !, Seja Bem Vindo(a) " + admNovo.getNome());

    }

    public boolean isEmailRegistrado(String email) {
        List<AdministradorEntity> listaAdms = administradorRepository.buscarTodos();

        for (AdministradorEntity adm : listaAdms) {
            if (adm.getEmail().equals(email)) {
                System.out.println("Esse Email Já tem Cadastro. Tente Seu Login Com Ele ");
                return true;
            }
        }
        return false;
    }

    public void menuAdministrador() {
        int opcao;
        do {
            System.out.println("\n==MENU ADMINISTRADOR==");
            System.out.println("0 - Cadastrar Administrador");
            System.out.println("1 - Gerenciar Pacotes Turísticos");
            System.out.println("2 - Gerenciar Passeios");
            System.out.println("3 - Gerenciar Usuários");
            System.out.println("4 - Sair");
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
                    System.out.println("Saindo do menu administrador...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
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
                        pacoteTuristicoService.cadastrarPacote();
                        break;
                    case 2:
                        pacoteTuristicoService.imprimirPacotesDisponiveis(pacoteTuristicoRepository.buscarTodos());
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
                passeioService.cadastrarPasseio();
                break;
            case 2:
                passeioService.mostrarTodosPasseios(passeioRepository.buscarTodos());
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
                usuarioService.listarUsuariosCadastrados(usuarioRepository.buscarTodos());
                break;
            case 2:
                System.out.println("Saindo...");
            default:
                System.out.println("Opção inválida!");
        }


    }
}