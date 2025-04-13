package SistemaTurismo;
import Repositorio.*;
import Servicos.*;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//
//        PasseioEntity passeio = passeioRepository.findById(1);
//        System.out.println(passeio.toString());
//
//        UsuarioEntity usuario = usuarioRepository.findById(6L); //usuario: miguel
//        UsuarioEntity usuarioCadastradoAoBanco = new UsuarioEntity();
//
//        usuarioCadastradoAoBanco.setnome("Joana");
//        usuarioCadastradoAoBanco.setemail("joana@gmail.com");
//        usuarioCadastradoAoBanco.setsenha("Joana123#");
//        usuarioCadastradoAoBanco.setnumeroTelefone("45 98284841439");
//
//        usuarioRepository.cadastrar(usuarioCadastradoAoBanco);
//
//        System.out.println(usuario.getnome());
//        System.out.println(usuario.getnumeroTelefone());
//
//       List<UsuarioEntity> usuarios = usuarioRepository.buscarTodos();
//        for(UsuarioEntity u : usuarios){
//
//            System.out.println(u.getnome());
//        }
//
//        System.out.println("Achei  eu:  " + usuarioRepository.buscarPorNomeInicial("Miguel"));
//
//        UsuarioEntity usuario3 = usuarioRepository.findById(3);
//
//        usuario3.setnome("Luciana");
//        usuario3.setsenha("Luci123@");
//        usuarioRepository.atualizar(usuario3);

//        UsuarioEntity usuario5 = usuarioRepository.findById(5);
//
//        usuarioRepository.remover(usuario5);

        //TESTES COM ROTEIRO_PASSEIOS

//       List<Roteiro_PasseiosEntity> listaPasseiosPorIdRoteiro = roteiroPasseiosRepository.buscarPorIdRoteiro(1);
//
//        for(int i =0;i<listaPasseiosPorIdRoteiro.size();i++){
//
//            System.out.println(listaPasseiosPorIdRoteiro.get(i).toString());
//        }

        EntityManager em = CustomizerFactory.getEntityManager();

        UsuarioRepository usuarioRepository = new UsuarioRepository(em);
        PacoteTuristicoRepository pacoteTuristicoRepository = new PacoteTuristicoRepository(em);
        PasseioRepository passeioRepository = new PasseioRepository(em);
        RoteiroPersonalizadoRepository  roteiroPersonalizadoRepository= new RoteiroPersonalizadoRepository(em);
        PasseioService passeioService = new PasseioService(passeioRepository);
        AdministradorRepository administradorRepository = new AdministradorRepository(em);

        RoteiroPersonalizadoService roteiroPersonalizadoService = new RoteiroPersonalizadoService(passeioRepository, passeioService, roteiroPersonalizadoRepository);
        PacoteTuristicoService pacoteTuristicoService = new PacoteTuristicoService(pacoteTuristicoRepository, passeioRepository, passeioService);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository, pacoteTuristicoRepository,pacoteTuristicoService,passeioService,passeioRepository, roteiroPersonalizadoRepository, roteiroPersonalizadoService);
        AdministradorService administradorService = new AdministradorService(administradorRepository, pacoteTuristicoRepository, passeioRepository, usuarioRepository, pacoteTuristicoService, passeioService, usuarioService);

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("==SISTEMA DE TURISMO (NEW DESTINY)==");
            System.out.println("===MENU PRINCIPAL===");
            System.out.println("1 - Login Como Usuário");
            System.out.println("2 - Cadastrar Como Usuário");
            System.out.println("3 - Entrar Como Usuario ");
            System.out.println("4 - Entrar Como Administrador");
            System.out.println("5 - Sair");
            System.out.println("Informe A opção: ");
            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    if(usuarioService.menuLogin()){
                        usuarioService.menuVisaoUsuario();
                    }else {

                    }
                    break;

                case 2:
                    usuarioService.menuCadastro();
                    break;

                case 3:

                    usuarioService.menuVisaoUsuario();
                    break;

                case 4:

                    if(administradorService.login()) {
                        administradorService.menuAdministrador();
                    }

                    break;
                default:
                    System.out.println("Opcao Invalida");
                    break;
            }
        }while (opcao !=5);
    }
}