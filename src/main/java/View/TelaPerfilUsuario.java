package View;

import Controller.UsuarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaPerfilUsuario extends JFrame {

    private Font interFont = null;
    private Font interFontBold = null;
    private UsuarioController usuarioController;

    public TelaPerfilUsuario(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
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
        JLabel saudacao = new JLabel("Olá, Pablo!"); // <- você pode tornar isso dinâmico
        saudacao.setFont(interFontBold.deriveFont(22f));
        saudacao.setForeground(Color.WHITE);
        saudacao.setAlignmentX(Component.CENTER_ALIGNMENT);

        containerConteudo.add(Box.createVerticalStrut(20));
        containerConteudo.add(saudacao);
        containerConteudo.add(Box.createVerticalStrut(30));


        String[] opcoes = {
                "Editar Perfil",
                "Meus Pacotes",
                "Montar Roteiro Turístico",
                "Comprar Pacotes Prontos",
                "Métodos de Pagamento",
                "Ajuda / Suporte",
                "Sair da Conta"
        };

        for (String opcao : opcoes) {  // lista dos Botões
            final String opcaoFinal = opcao;
            JButton botao = new JButton(opcao);
            botao.setAlignmentX(Component.CENTER_ALIGNMENT);
            botao.setMaximumSize(new Dimension(300, 40));
            botao.setFont(interFontBold.deriveFont(16f));
            botao.setBackground(Color.WHITE);
            botao.setForeground(new Color(0x1c9430));
            botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            containerConteudo.add(botao);
            containerConteudo.add(Box.createVerticalStrut(15));

            botao.addActionListener(e -> {
                TelaPerfilUsuario.this.setVisible(false);
                switch (opcaoFinal) {
                    case "Editar Perfil":
                        EditarPerfilUsuário editarPerfilUsuário = new EditarPerfilUsuário(usuarioController);
                        editarPerfilUsuário.editarPerfil();
                        break;
                    case "Meus Pacotes":
                        System.out.println("Meus Pacotes");
                        break;
                    case "Montar Roteiro Turístico":
                        System.out.println("Roteiros");
                        break;
                    case "Comprar Pacotes Prontos":
                        System.out.println("Pacotes Prontos");
                        break;
                    case "Métodos de Pagamento":
                        System.out.println("Métodos pagamento");
                        break;
                    case "Ajuda / Suporte":
                        System.out.println("Help!");
                        break;
                    case "Sair da Conta":
                        new TelaLogin(usuarioController).iniciarTela();
                        break;

                }
            });
            containerMain.add(containerConteudo, BorderLayout.CENTER);

            buttonBack.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println(e);// mostra no terminal que o botão foi acionado
                    TelaPerfilUsuario.this.dispose(); //fecha a tela do perfil do usuário
                }
            });


            // Configurações da janela para parecer um celular
            setTitle("Perfil do Usuário");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(440, 920);
            setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }
}
