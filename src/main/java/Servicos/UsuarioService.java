package Servicos;

import Entidades.UsuarioEntity;
import Repositorio.UsuarioRepository;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Scanner;

public class UsuarioService {

    @Inject
    private UsuarioRepository usuarioRepository;
    private Scanner sc = new Scanner(System.in);

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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


    public void menuLogin(){

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
            }
        }

        if(!loginCorreto){
            System.out.println("Email ou senha incorretos.");
        }

    }





}
