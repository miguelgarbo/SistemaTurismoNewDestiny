package Servicos;

import Entidades.UsuarioEntity;
import Repositorio.PacoteTuristicoRepository;
import Repositorio.UsuarioRepository;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Scanner;

public class UsuarioService {

    @Inject
    private UsuarioRepository usuarioRepository;
    private PacoteTuristicoRepository pacoteTuristicoRepository;
    private PacoteTuristicoService pacoteTuristicoService;
    private Scanner sc = new Scanner(System.in);

    public UsuarioService(UsuarioRepository usuarioRepository, PacoteTuristicoRepository pacoteTuristicoRepository, PacoteTuristicoService pacoteTuristicoService) {
        this.usuarioRepository = usuarioRepository;
        this.pacoteTuristicoRepository = pacoteTuristicoRepository;
        this.pacoteTuristicoService = pacoteTuristicoService;
        this.sc = new Scanner(System.in);
    }

    @Transactional
    public void cadastrarUsuario(UsuarioEntity usuario){

        if(usuario.getnome() == null || usuario.getnome().isEmpty()){

            throw new IllegalArgumentException("Nome não pode ser Vazio");
        }

        if(usuario.getemail() == null || usuario.getemail().isEmpty()){

            throw new IllegalArgumentException("Email não pode ser Vazio");
        }

        usuarioRepository.cadastrar(usuario);
    }

    @Transactional
    public void removerUsuario(Long id){
        UsuarioEntity usuario = usuarioRepository.findById(id);
        if(usuario != null){
            usuarioRepository.remover(usuario);
        }
    }

    @Transactional
    public void atualizarUsuario(UsuarioEntity usuario){

        if(usuario.getid() == null){
            throw new IllegalArgumentException("ID do Usuário não pode ser nulo");
        }

        if(usuario.getnome() == null || usuario.getnome().isEmpty()){
            throw new IllegalArgumentException("Nome Não pode Ser Vazio");
        }

        if(usuario.getemail() == null || usuario.getemail().isEmpty()){
            throw new IllegalArgumentException("Email Não pode Ser Vazio");
        }
    }

    @Transactional
    public List<UsuarioEntity> listarUsuario(){
        return usuarioRepository.buscarTodos();
    }


    public boolean menuLogin(){

        System.out.println("==LOGIN==");
        System.out.println("Digite seu email: ");
        String emailInformado = sc.nextLine();
        System.out.println("Digite sua senha:");
        String senhaInformada = sc.nextLine();

        List<UsuarioEntity> listaUsuarios =  usuarioRepository.buscarTodos();
        boolean loginCorreto = false;

        for(UsuarioEntity usuario: listaUsuarios){
            if(usuario.getemail().equals(emailInformado) || usuario.getsenha().equals(senhaInformada)){
                System.out.println("Login Realizado Com Sucesso, Seja Bem Vindo "+ usuario.getnome());
                loginCorreto = true;
                return true;
            }
        }
        if(!loginCorreto){
            System.out.println("Email ou senha incorretos.");
        }
        return false;
    }

    public void menuCadastro(){

        System.out.println("==CADASTRO DE USUARIO==");
        System.out.println("Informe seu Nome: ");
        String nomeInformado = sc.nextLine().toLowerCase();

        System.out.println("Informe Seu Email: ");
        String emailInformado = sc.nextLine();

        if(isEmailRegistrado(emailInformado)){
        return;
        }

        System.out.println("Informe Seu Numero De Telefone (Para Contato): ");
        String telefoneInformado = sc.nextLine();

        System.out.println("Informe sua Senha: ");
        String senhaInformada = sc.nextLine();

        UsuarioEntity usuarioNovo = new UsuarioEntity();

        usuarioNovo.setnome(nomeInformado);
        usuarioNovo.setemail(emailInformado);
        usuarioNovo.setnumeroTelefone(telefoneInformado);
        usuarioNovo.setsenha(senhaInformada);

        cadastrarUsuario(usuarioNovo);
        System.out.println("Usuario Cadastrado Com Sucesso !, Seja Bem VIndo(a) "+ usuarioNovo.getnome());
    }


    public boolean isEmailRegistrado(String email){

        List<UsuarioEntity> listaUsuarios = usuarioRepository.buscarTodos();

        for(UsuarioEntity usuario: listaUsuarios){
            if(usuario.getemail().equals(email)){
                System.out.println("Esse Email Já tem Cadastro. Tente Seu Login Com Ele ");
                return true;
            }
        }
        return false;

    }

    public void menuVisaoUsuario(){

        System.out.println("1 - Visualizar Pacotes Disponiveis: ");
        System.out.println("2 - Visualizar Passeios Diponiveis");
        System.out.println("3 - Criar Roteiro de Viagem");
        System.out.println("4 - Voltar ao Menu");
        int opcao = sc.nextInt();

        switch (opcao){

            case 1:

                pacoteTuristicoService.mostrarPacotesFormatados();


                break;

            case 2:

                break;

            case 3:

                break;

            case 4:

                break;

        }

    }





}
