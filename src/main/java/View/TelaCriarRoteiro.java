package View;

import Controller.PacoteController;
import Controller.PasseioController;
import Controller.UsuarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class TelaCriarRoteiro extends JFrame {

    private UsuarioController usuarioController;
    private PasseioController passeioController;
    private PacoteController pacoteController;
    private Font interFont;
    private Font interFontBold;
    private CoresProjeto coresProjeto = new CoresProjeto();

    public TelaCriarRoteiro(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController) {
        this.usuarioController = usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
    }

    public void iniciarTela() {
        setTitle("Criando Seu Roteiro");
        setSize(440, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        interFont = FontLoader.loadFont("/fontsNewDestiny/Inter.ttc", 16f);
        interFontBold = FontLoader.loadFont("/fontsNewDestiny/InterVariable.ttf", 16f);

        BackgroundPanel containerMain = new BackgroundPanel("/photos/backgroundMain.png");
        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        // Header
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon imgButtonBack = null;
        URL arrowBackUrl = getClass().getResource("/photos/arrowBack.png");
        if (arrowBackUrl != null) {
            Image img = new ImageIcon(arrowBackUrl).getImage();
            Image imgRedimensionada = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imgButtonBack = new ImageIcon(imgRedimensionada);
        } else {
            System.err.println("Imagem do botão de voltar não encontrada: /photos/arrowBack.png");
        }

        JButton buttonBack = new JButton(imgButtonBack);
        buttonBack.setBorderPainted(false);
        buttonBack.setContentAreaFilled(false);
        buttonBack.setFocusPainted(false);
        buttonBack.setOpaque(false);
        buttonBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TelaVisualizacao telaVisualizacao = new TelaVisualizacao(usuarioController, passeioController, pacoteController);
                telaVisualizacao.iniciarTela();
            }
        });

        ImageIcon imgLogo = null;
        URL logoUrl = getClass().getResource("/photos/logo.png");
        if (logoUrl != null) {
            imgLogo = new ImageIcon(logoUrl);
        } else {
            System.err.println("Imagem do logo não encontrada: /photos/logo.png");
        }
        JLabel logoNew = new JLabel(imgLogo);
        logoNew.setSize(100, 50);

        header.setOpaque(false);
        header.add(buttonBack);
        header.add(Box.createHorizontalStrut(75));
        header.add(logoNew);

        // Conteúdo central
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(60, 40, 20, 40));

        JLabel tituloLabel = new JLabel("Criando Seu Roteiro");
        tituloLabel.setFont(interFontBold.deriveFont(Font.BOLD, 22f));
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campoTitulo = criarCampoTexto("Título do Roteiro");
        JTextField campoData = criarCampoTexto("Data De Início");
        JTextField campoDias = criarCampoTexto("Quantidade de Dias");

        JButton botaoProximo = new JButton("Próximo");
        botaoProximo.setFont(interFont.deriveFont(16f));
        botaoProximo.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoProximo.setBackground(Color.WHITE);
        botaoProximo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        formPanel.add(tituloLabel);
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(campoTitulo);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(campoData);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(campoDias);
        formPanel.add(Box.createVerticalStrut(25));
        formPanel.add(botaoProximo);

        containerMain.add(header, BorderLayout.NORTH);
        containerMain.add(formPanel, BorderLayout.CENTER);

        setContentPane(containerMain);
        setVisible(true);
    }

    private JTextField criarCampoTexto(String placeholder) {
        JTextField campo = new JTextField();
        campo.setMaximumSize(new Dimension(300, 40));
        campo.setFont(interFont);
        campo.setForeground(Color.BLACK);
        campo.setBorder(BorderFactory.createTitledBorder(placeholder));
        return campo;
    }
}
