package View;

import Controller.PacoteController;
import Controller.PasseioController;
import Controller.RoteiroController;
import Controller.UsuarioController;
import Model.Repositorio.CartaoRepositorio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaPerfilUsuario extends JFrame {

    private Font interFont = null;
    private Font interFontBold = null;
    private UsuarioController usuarioController;
    private PasseioController passeioController;
    private PacoteController pacoteController;
    private RoteiroController roteiroController;
    private CartaoRepositorio cartaoRepositorio;

    public TelaPerfilUsuario(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController, RoteiroController roteiroController) {
        this.usuarioController = usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
        this.roteiroController = roteiroController;
    }

    public void iniciarPerfilUsuário() {
        interFont = FontLoader.loadFont("/fontsNewDestiny/Inter.ttc", 14f);
        interFontBold = FontLoader.loadFont("/fontsNewDestiny/InterVariable.ttf", 18f);

        // Container principal com fundo personalizado
        BackgroundPanel containerMain = new BackgroundPanel("/photos/backgroundMain.png");
        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        CoresProjeto coresProjeto = new CoresProjeto();

        // inicio da Header ----------------------------------------------------
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon imgButtonBack = new ImageIcon(getClass().getResource("/photos/arrowBack.png"));

        Image img = imgButtonBack.getImage();
        Image imgRedimensionada = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon imgRedimensionadaIcon = new ImageIcon(imgRedimensionada);

        JButton buttonBack = new JButton(imgRedimensionadaIcon);
        buttonBack.setBorderPainted(false);
        buttonBack.setContentAreaFilled(false);
        buttonBack.setFocusPainted(false);
        buttonBack.setOpaque(false);
        buttonBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/photos/logo.png"));
        JLabel logoNew = new JLabel(imgLogo);
        logoNew.setSize(100, 50);

        header.setOpaque(false);
        header.add(buttonBack);
        header.add(Box.createHorizontalStrut(75));
        header.add(logoNew);

        containerMain.add(header, BorderLayout.NORTH);
        setContentPane(containerMain);
        //fim da header-------------------------------------------------------------------------

        JPanel containerConteudo = new JPanel();
        containerConteudo.setOpaque(false);
        containerConteudo.setLayout(new BoxLayout(containerConteudo, BoxLayout.Y_AXIS));
        containerConteudo.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));


        //Conteúdo Central----------------------------------------------------------------------


        String nomeUsuário = usuarioController.getUserLogged().getNome();
        String primeiroNome = nomeUsuário.split(" ")[0];
        String nomeFormatado = primeiroNome.substring(0, 1).toUpperCase() + primeiroNome.substring(1).toLowerCase();
        JLabel saudacao = new JLabel("Olá, " + nomeFormatado);

        saudacao.setFont(interFontBold.deriveFont(22f));
        saudacao.setForeground(Color.WHITE);
        saudacao.setAlignmentX(Component.CENTER_ALIGNMENT);

        containerConteudo.add(Box.createVerticalStrut(20));
        containerConteudo.add(saudacao);
        containerConteudo.add(Box.createVerticalStrut(30));

        String[] opcoes = {
                "Editar Perfil",
                "Meu Carrinho",
                "Meus Roteiros",
                "Métodos de Pagamento",
                "Ajuda / Suporte",
                "Sair da Conta"
        };

        JButton ultimoBotao = null;

        for (String opcao : opcoes) {  // lista dos Botões
            final String opcaoFinal = opcao;
            JButton botao = new JButton(opcao);
            botao.setAlignmentX(Component.CENTER_ALIGNMENT);
            botao.setMaximumSize(new Dimension(300, 40));
            botao.setFont(interFontBold.deriveFont(16f));
            botao.setBackground(Color.WHITE);
            botao.setForeground(new Color(0x208482));
            botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            containerConteudo.add(botao);
            containerConteudo.add(Box.createVerticalStrut(15));
            ultimoBotao = botao;

            botao.addActionListener(e -> {
                TelaPerfilUsuario.this.setVisible(false);
                switch (opcaoFinal) {
                    case "Editar Perfil":
                        dispose();

                        EditarPerfilUsuario editarPerfilUsuario = new EditarPerfilUsuario(usuarioController, pacoteController, passeioController, roteiroController);
                        editarPerfilUsuario.editarPerfil();
                        break;
                    case "Meu Carrinho":
                        TelaCarrinhoUsuario telaCarrinhoUsuario = new TelaCarrinhoUsuario(usuarioController,pacoteController,passeioController, roteiroController, usuarioController.carrinhoService());
                        telaCarrinhoUsuario.iniciarTela();
                        break;
                    case "Meus Roteiros":
                        TelaRoteiros telaRoteiros = new TelaRoteiros(usuarioController, pacoteController, passeioController, roteiroController);
                        telaRoteiros.inicarTela(usuarioController.getUserLogged());
                        break;
                    case "Métodos de Pagamento":
                        CadastrarMetodoPagamento cadastrarMetodoPagamento = new CadastrarMetodoPagamento(usuarioController,pacoteController,passeioController, roteiroController);
                        cadastrarMetodoPagamento.gerenciadorCartão(usuarioController.getUserLogged());
                        System.out.println("Métodos pagamento");
                        break;
                    case "Ajuda / Suporte":
                        System.out.println("Help!");
                        break;
                    case "Sair da Conta":
                        usuarioController.setgetUserLogged(null);
                        new TelaVisualizacao(usuarioController,passeioController,pacoteController, roteiroController).iniciarTela();
                        break;

                }

            });
            containerMain.add(containerConteudo, BorderLayout.CENTER);

            // Configurações da janela para parecer um celular
            setTitle("Perfil do Usuário");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(440, 920);
            setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);
        }
        buttonBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e);// mostra no terminal que o botão foi acionado
                TelaPerfilUsuario.this.dispose(); //fecha a tela do perfil do usuário
                TelaVisualizacao telaVisualizacao = new TelaVisualizacao(usuarioController,passeioController,pacoteController, roteiroController);
                telaVisualizacao.iniciarTela();
                dispose();
            }
        });

        if (ultimoBotao != null) {
            ultimoBotao.setForeground(coresProjeto.corVermelha);
        }
    }
}
