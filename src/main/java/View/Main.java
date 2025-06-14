package View;
import Controller.*;
import Model.Repositorio.*;
import Model.Servicos.*;

import javax.persistence.EntityManager;
import java.awt.font.TextLayout;

public class Main {

    public static void main(String[] args) {

        //gerenciador de entidades
        EntityManager em = CustomizerFactory.getEntityManager();

        // repositorios das entidades
        RoteiroPersonalizadoRepository roteiroPersonalizadoRepository = new RoteiroPersonalizadoRepository(em);
        PasseioRepository passeioRepository = new PasseioRepository(em);
        AdministradorRepository administradorRepository = new AdministradorRepository(em);
        CartaoRepositorio cartaoRepositorio = new CartaoRepositorio(em);
        UsuarioRepository usuarioRepository = new UsuarioRepository(em);
        PagamentoRepository pagamentoRepository = new PagamentoRepository(em);
        PacoteTuristicoRepository pacoteTuristicoRepository = new PacoteTuristicoRepository(em);
        CartaoService cartaoService = new CartaoService(cartaoRepositorio);
        PedidoRepository pedidoRepository = new PedidoRepository(em);
        CategoriaRepository categoriaRepository = new CategoriaRepository(em);
        DiaRepository diaRepository = new DiaRepository(em);
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
        PagamentoController pagamentoController = new PagamentoController(cartaoController, cartaoService, passeioService, pagamentoService, pacoteTuristicoService);
        CategoriaController categoriaController = new CategoriaController(categoriaService);
        PasseioController passeioController = new PasseioController(passeioService, categoriaService, categoriaController, pagamentoController, diaRepository);
        PacoteController pacoteController = new PacoteController(pacoteTuristicoService, pagamentoController, categoriaController, categoriaService, passeioController, passeioService);
        RoteiroController roteiroController = new RoteiroController(roteiroPersonalizadoService, passeioService, passeioController);

        UsuarioController usuarioController = new UsuarioController(usuarioService, roteiroController, passeioController, pacoteController, cartaoController, pagamentoController);
        AdmnistradorController admController = new AdmnistradorController(administradorService, pacoteController, passeioController, usuarioController, categoriaController);

        //MenuPrincipal menuPrincipal = new MenuPrincipal(usuarioController, admController);

        //TelaLogin telaLogin = new TelaLogin(usuarioController,pacoteController,passeioController);
        //telaLogin.iniciarTela();

   //     TelaCadastro telaCadastro = new TelaCadastro(usuarioController,passeioController,pacoteController);
   //     telaCadastro.iniciarTela();

//        TelaRoteiroSelecionado telaRoteiroSelecionado = new TelaRoteiroSelecionado();
//        telaRoteiroSelecionado.iniciarTela();

          TelaVisualizacao telaVisualizacao = new TelaVisualizacao(usuarioController,passeioController,pacoteController, roteiroController);
          telaVisualizacao.iniciarTela();

//        TelaRoteiros telaRoteiros = new TelaRoteiros();
//        telaRoteiros.inicarTela();

        // TelaPerfilUsuario telaPerfilUsuario = new TelaPerfilUsuario(usuarioController,pacoteController,passeioController);
         //telaPerfilUsuario.iniciarPerfilUsuário();

       //menuPrincipal.mostrarMenuPrincipal();

    }
}