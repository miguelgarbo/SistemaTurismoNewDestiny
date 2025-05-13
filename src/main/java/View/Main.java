package View;
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
        PagamentoService pagamentoService = new PagamentoService(pagamentoRepository, cartaoService, passeioRepository,  pacoteTuristicoRepository,cartaoRepositorio, pedidoRepository);
        PasseioService passeioService = new PasseioService(passeioRepository, categoriaService, pagamentoService);
        RoteiroPersonalizadoService roteiroPersonalizadoService = new RoteiroPersonalizadoService(roteiroPersonalizadoRepository, passeioService, passeioRepository);
        PacoteTuristicoService pacoteTuristicoService = new PacoteTuristicoService(pacoteTuristicoRepository, pagamentoService, passeioRepository, passeioService, categoriaService);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository, pacoteTuristicoRepository,pacoteTuristicoService,passeioService,passeioRepository, roteiroPersonalizadoService, cartaoRepositorio,cartaoService, pagamentoService);
        AdministradorService administradorService = new AdministradorService(administradorRepository, pacoteTuristicoRepository, passeioRepository, usuarioRepository, pacoteTuristicoService, passeioService, usuarioService, categoriaService);

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
                        System.out.println("Voce nao esta logado");
                    }
                    break;

                case 2:
                    usuarioService.menuCadastro();
                    break;

                case 3:

                    usuarioService.menuVisaoUsuario();
                    break;

                case 4:
                        administradorService.menuAdministrador();
                    break;
                default:
                    System.out.println("Opcao Invalida");
                    break;
            }
        }while (opcao !=5);
    }
}