package View;
import Controller.*;
import Repositorio.*;
import Servicos.*;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //gerenciador de entidades
        EntityManager em = CustomizerFactory.getEntityManager();

        // repositorios das entidades
        RoteiroPersonalizadoRepository  roteiroPersonalizadoRepository= new RoteiroPersonalizadoRepository(em);
        PasseioRepository passeioRepository = new PasseioRepository(em);
        AdministradorRepository administradorRepository = new AdministradorRepository(em);
        CartaoRepositorio cartaoRepositorio = new CartaoRepositorio(em);
        UsuarioRepository usuarioRepository = new UsuarioRepository(em);
        PagamentoRepository pagamentoRepository = new PagamentoRepository(em);
        PacoteTuristicoRepository pacoteTuristicoRepository = new PacoteTuristicoRepository(em);
        CartaoService cartaoService = new CartaoService(cartaoRepositorio);
        PedidoRepository pedidoRepository = new PedidoRepository(em);
        CategoriaRepository categoriaRepository = new CategoriaRepository(em);
        //servicos das entidades


        CategoriaService categoriaService = new CategoriaService(categoriaRepository);
        PagamentoService pagamentoService = new PagamentoService(pagamentoRepository, pedidoRepository);
        PasseioService passeioService = new PasseioService(passeioRepository);
        RoteiroPersonalizadoService roteiroPersonalizadoService = new RoteiroPersonalizadoService(roteiroPersonalizadoRepository);
        PacoteTuristicoService pacoteTuristicoService = new PacoteTuristicoService(pacoteTuristicoRepository, pagamentoService, passeioRepository, passeioService, categoriaService);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository, roteiroPersonalizadoService);
        AdministradorService administradorService = new AdministradorService(administradorRepository);


        /// MVC COISAS NOVAS ABAIXO
        CartaoController cartaoController = new CartaoController(cartaoService);
        PagamentoController pagamentoController = new PagamentoController(cartaoController,cartaoService, passeioService, pagamentoService, pacoteTuristicoService, usuarioService);
        CategoriaController categoriaController = new CategoriaController(categoriaService);
        PasseioController passeioController = new PasseioController(passeioService, categoriaService, categoriaController, pagamentoController);
        PacoteController pacoteController = new PacoteController(pacoteTuristicoService, pagamentoController, categoriaController, categoriaService, passeioController, passeioService);

        RoteiroController roteiroController = new RoteiroController(roteiroPersonalizadoService,passeioService,passeioController);
        UsuarioController usuarioController = new UsuarioController(usuarioService,roteiroController, passeioController, pacoteController, cartaoController, pagamentoController);
        AdmnistradorController admController = new AdmnistradorController(administradorService, pacoteController, passeioController, usuarioController, categoriaController);

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("==SISTEMA DE TURISMO (NEW DESTINY)==");
            System.out.println("===MENU PRINCIPAL===");
            System.out.println("1 - Login Como Usuário");
            System.out.println("2 - Cadastrar Como Usuário");
            System.out.println("3 - Entrar Como Usuario");
            System.out.println("4 - Entrar Como Administrador");
            System.out.println("5 - Sair");
            System.out.println("Informe A opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    if(usuarioController.getUserLogged()!=null){
                        System.out.println("Voce ja esta logado");
                        usuarioController.menuUsuario();

                    }else {
                        usuarioController.menuLogin();
                    }
                    break;

                case 2:
                    usuarioController.menuCadastroUsuario();
                    break;

                case 3:

                    usuarioController.menuUsuario();
                    break;

                case 4:
                        admController.menuAdministrador();
                    break;
                default:
                    System.out.println("Opcao Invalida");
                    break;
            }
        }while (opcao !=5);
    }
}