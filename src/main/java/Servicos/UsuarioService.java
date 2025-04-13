package Servicos;

import Entidades.RoteiroPersonalizadoEntity;
import Entidades.UsuarioEntity;
import Repositorio.PacoteTuristicoRepository;
import Repositorio.PasseioRepository;
import Repositorio.RoteiroPersonalizadoRepository;
import Repositorio.UsuarioRepository;
import jdk.swing.interop.SwingInterOpUtils;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Scanner;

public class UsuarioService {

    @Inject
    private UsuarioRepository usuarioRepository;

    @Inject
    private PacoteTuristicoRepository pacoteTuristicoRepository;

    @Inject
    private PacoteTuristicoService pacoteTuristicoService;

    @Inject
    private PasseioService passeioService;

    @Inject
    private PasseioRepository passeioRepository;

    @Inject
    private RoteiroPersonalizadoRepository roteiroPersonalizadoRepository;

    @Inject
    private RoteiroPersonalizadoService roteiroPersonalizadoService;

    private Long idLogged;

    private Scanner sc = new Scanner(System.in);

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PacoteTuristicoRepository pacoteTuristicoRepository,
                          PacoteTuristicoService pacoteTuristicoService,
                          PasseioService passeioService,
                          PasseioRepository passeioRepository,
                          RoteiroPersonalizadoRepository roteiroPersonalizadoRepository,
                          RoteiroPersonalizadoService roteiroPersonalizadoService) {

        this.usuarioRepository = usuarioRepository;
        this.pacoteTuristicoRepository = pacoteTuristicoRepository;
        this.pacoteTuristicoService = pacoteTuristicoService;
        this.passeioService = passeioService;
        this.passeioRepository = passeioRepository;
        this.roteiroPersonalizadoRepository = roteiroPersonalizadoRepository;
        this.roteiroPersonalizadoService = roteiroPersonalizadoService;
    }

    @Transactional
    public void cadastrarUsuario(UsuarioEntity usuario) {

        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {

            throw new IllegalArgumentException("Nome não pode ser Vazio");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {

            throw new IllegalArgumentException("Email não pode ser Vazio");
        }

        usuarioRepository.cadastrar(usuario);
    }

    @Transactional
    public void removerUsuario(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id);
        if (usuario != null) {
            usuarioRepository.remover(usuario);
        }
    }

    @Transactional
    public void atualizarUsuario(UsuarioEntity usuario) {
        if (usuario.getId() == null) {
            throw new IllegalArgumentException("ID do Usuário não pode ser nulo");
        }
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome Não pode Ser Vazio");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email Não pode Ser Vazio");
        }
    }

    public void listarUsuariosCadastrados(List<UsuarioEntity> usuarios) {

        System.out.println("==USUARIOS CADASTRADOS==");

        for(UsuarioEntity usuario : usuarios){

            System.out.println("ID: "+ usuario.getId());
            System.out.println("Nome: "+ usuario.getNome());
            System.out.println("Email: "+ usuario.getEmail());
            System.out.println("Numero Telefone: "+ usuario.getNumeroTelefone());
            roteiroPersonalizadoService.mostrarMeusRoteiros(usuario);
        }
    }

    public Long getIdLogged() {
        return idLogged;
    }

    public void setIdLogged(Long idLogged) {
        this.idLogged = idLogged;
    }

    public boolean menuLogin() {

        System.out.println("==LOGIN==");
        System.out.println("Digite seu email: ");
        String emailInformado = sc.nextLine();
        System.out.println("Digite sua senha:");
        String senhaInformada = sc.nextLine();

        List<UsuarioEntity> listaUsuarios = usuarioRepository.buscarTodos();
        boolean loginCorreto = false;

        for (UsuarioEntity usuario : listaUsuarios) {
            if (usuario.getEmail().equals(emailInformado) && usuario.getSenha().equals(senhaInformada)) {
                System.out.println("Login Realizado Com Sucesso, Seja Bem Vindo " + usuario.getNome());
                loginCorreto = true;
                idLogged = usuario.getId();
                return true;
            }
        }
        if (!loginCorreto) {
            System.out.println("Email ou senha incorretos.");
        }
        return false;
    }

    public void menuCadastro() {

        System.out.println("==CADASTRO DE USUARIO==");
        System.out.println("Informe seu Nome: ");
        String nomeInformado = sc.nextLine().toLowerCase();

        System.out.println("Informe Seu Email: ");
        String emailInformado = sc.nextLine();

        if (isEmailRegistrado(emailInformado)) {
            return;
        }

        System.out.println("Informe Seu Numero De Telefone (Para Contato): ");
        String telefoneInformado = sc.nextLine();

        System.out.println("Informe sua Senha: ");
        String senhaInformada = sc.nextLine();

        if (senhaInformada.length() <= 7) {

            throw new IllegalArgumentException("A senha deve conter 8 ou mais caracteres");
        }

        UsuarioEntity usuarioNovo = new UsuarioEntity();

        usuarioNovo.setNome(nomeInformado);
        usuarioNovo.setEmail(emailInformado);
        usuarioNovo.setNumeroTelefone(telefoneInformado);
        usuarioNovo.setSenha(senhaInformada);

        cadastrarUsuario(usuarioNovo);
        System.out.println("Usuario Cadastrado Com Sucesso !, Seja Bem VIndo(a) " + usuarioNovo.getNome());
    }


    public boolean isEmailRegistrado(String email) {
        List<UsuarioEntity> listaUsuarios = usuarioRepository.buscarTodos();

        for (UsuarioEntity usuario : listaUsuarios) {
            if (usuario.getEmail().equals(email)) {
                System.out.println("Esse Email Já tem Cadastro. Tente Seu Login Com Ele ");
                return true;
            }
        }
        return false;
    }


    public void alterarInformacoes(UsuarioEntity usuarioLogado) {
        System.out.println("SUAS INFORMAÇÕES: ");
        System.out.println("1 - NOME: " + usuarioLogado.getNome());
        System.out.println("2 - SENHA: " + usuarioLogado.getSenha());
        System.out.println("3 - NUMERO TELEFONE: " + usuarioLogado.getNumeroTelefone());

        System.out.println("Informe o Campo vc gostaria de Modificar: ");
        int campoSelecionado = sc.nextInt();

        sc.nextLine();

        switch (campoSelecionado) {

            case 1:
                System.out.println("==ALTERAÇÃO NOME== ");
                System.out.println("NOME ATUAL: " + usuarioLogado.getNome());
                System.out.println("Informe o Nome Novo");
                String nomeNovo = sc.nextLine();

                usuarioLogado.setNome(nomeNovo);
                atualizarUsuario(usuarioLogado);

                System.out.println("Nome Trocado Com Sucesso " + nomeNovo);

                break;

            case 2:

                System.out.println("==ALTERAÇÃO SENHA== ");
                System.out.println("Informe a senha atual: ");
                String senhaAtualInformada = sc.nextLine();

                if (senhaAtualInformada.equals(usuarioLogado.getSenha())) {
                    System.out.println("Senha Correta !");

                    System.out.println("Informe a Nova Senha: ");
                    String senhaNova = sc.nextLine();

                    usuarioLogado.setSenha(senhaNova);
                    atualizarUsuario(usuarioLogado);

                    System.out.println("Senha Trocada Com Sucesso");
                }

                break;

            case 3:
                System.out.println("==ALTERAÇÃO NUMERO TELEFONE== ");
                System.out.println("Informe o Numero De Telefone Novo");
                String numeroNovo = sc.nextLine();

                usuarioLogado.setNumeroTelefone(numeroNovo);
                atualizarUsuario(usuarioLogado);

                System.out.println("Numero Telefone Trocado Com Sucesso ");

                break;
            default:
                System.out.println("informação invalida");
                break;
        }
    }


    public void menuVisaoUsuario() {
        System.out.println("==MENU USUARIO==");
        System.out.println("1 - Visualizar Pacotes Disponiveis: ");
        System.out.println("2 - Visualizar Passeios Diponiveis");
        System.out.println("3 - Criar Roteiro de Viagem");
        System.out.println("4 - Visualizar Meus Roteiros Criados");
        System.out.println("5 - Editar Meus Dados");
        System.out.println("6 - Voltar ao Menu");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                pacoteTuristicoService.imprimirPacotesDisponiveis(pacoteTuristicoRepository.buscarTodos());
                break;

            case 2:

                passeioService.mostrarTodosPasseios(passeioRepository.buscarTodos());
                break;

            case 3:
                if (idLogged == null) {
                    System.out.println("Você precisa estar logado para criar um roteiro");
                } else {
                    UsuarioEntity usuarioLogged = usuarioRepository.findById(idLogged);
                    roteiroPersonalizadoService.menuCadastro(usuarioLogged);
                }

                break;

            case 4:
                if (idLogged == null) {
                    System.out.println("Você precisa estar logado para ver seus roteiros.");
                    break;
                }

                UsuarioEntity usuarioLogged = usuarioRepository.findById(idLogged);

                if (usuarioLogged == null) {
                    System.out.println("Usuário não encontrado. Faça login novamente.");
                    break;
                }

                if (usuarioLogged.getRoteirosCriados().isEmpty()) {
                    System.out.println("Você ainda não possui roteiros criados.");
                } else {
                    roteiroPersonalizadoService.mostrarMeusRoteiros(usuarioLogged);
                }
                break;

            case 5:
                if (idLogged == null) {
                    System.out.println("Você precisa estar logado para alterar suas informações.");
                    break;
                }else{
                    UsuarioEntity usuarioLogado = usuarioRepository.findById(idLogged);
                    alterarInformacoes(usuarioLogado);
                }




                break;

            default:

                System.out.println("case invalido");
                break;

        }
    }
}