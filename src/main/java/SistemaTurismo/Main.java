package SistemaTurismo;
import Entidades.PagamentoEntity;
import Repositorio.*;
import Servicos.*;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        EntityManager em = CustomizerFactory.getEntityManager();

        UsuarioRepository usuarioRepository = new UsuarioRepository(em);
        PacoteTuristicoRepository pacoteTuristicoRepository = new PacoteTuristicoRepository(em);
        PasseioRepository passeioRepository = new PasseioRepository(em);
        RoteiroPersonalizadoRepository  roteiroPersonalizadoRepository= new RoteiroPersonalizadoRepository(em);
        PasseioService passeioService = new PasseioService(passeioRepository);
        AdministradorRepository administradorRepository = new AdministradorRepository(em);
        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        CartaoRepositorio cartaoRepositorio = new CartaoRepositorio(em);
        PedidoRepository pedidoRepository = new PedidoRepository(em);

        CartaoSevice cartaoSevice = new CartaoSevice();

        PagamentoRepository pagamentoRepository = new PagamentoRepository(em);
        PagamentoService pagamentoService = new PagamentoService(pagamentoRepository, pagamentoEntity, cartaoSevice, pacoteTuristicoRepository,cartaoRepositorio);

        RoteiroPersonalizadoService roteiroPersonalizadoService = new RoteiroPersonalizadoService(passeioRepository, passeioService, roteiroPersonalizadoRepository);


        PacoteTuristicoService pacoteTuristicoService = new PacoteTuristicoService(pacoteTuristicoRepository, pagamentoService, passeioRepository, passeioService);
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