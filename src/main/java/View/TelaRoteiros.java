package View;
import Controller.PacoteController;
import Controller.PasseioController;
import Controller.UsuarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;

public class TelaRoteiros extends JFrame {

    private UsuarioController usuarioController;
    private PacoteController pacoteController;
    private PasseioController passeioController;

    public TelaRoteiros(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController){
        this.usuarioController =usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
    }

    public void inicarTela(){

        // Configurações padrão da tela de login
        setTitle("Roteiros Criados");
        setSize(440, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Carregar a fonte inter
        Font interFont = null;
        Font interFontBold = null;

        try {
            // Carregar a fonte do classpath
            InputStream fontStream = getClass().getResourceAsStream("/fontsNewDestiny/Inter.ttc");
            if (fontStream != null) {
                interFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(16f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(interFont);
            } else {
                System.err.println("Fonte não encontrada!");
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        try {
            // Carregar a fonte do classpath
            InputStream fontStream = getClass().getResourceAsStream("/fontsNewDestiny/InterVariable.ttf");
            if (fontStream != null) {
                interFontBold = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(16f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(interFontBold);
            } else {
                System.err.println("Fonte não encontrada!");
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }


        // Painel com imagem de fundo
        JPanel containerMain = new JPanel() {
            Image img = new ImageIcon(getClass().getResource("/photos/backgroundMain.png")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

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

        buttonBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //TelaUsuario telaUsuario = new TelaUsuario();
                //telaUsuario.iniciarTela();
            }
        });

        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/photos/logo.png"));
        JLabel logoNew = new JLabel(imgLogo);
        logoNew.setSize(100,50);

        header.setOpaque(false);
        header.add(buttonBack);
        header.add(Box.createHorizontalStrut(75));
        header.add(logoNew);

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        JLabel titleRoteiro = new JLabel();

        titleRoteiro.setText("<html><b>Roteiros de ‘Usuario Nome’</b></html>");
        titleRoteiro.setFont(interFontBold.deriveFont(18f));
        titleRoteiro.setForeground(Color.WHITE);

        // bloco roteiro

        JButton buttonRoteiro = new JButton("<html><b>'Roteiro Nome'</b><br><i>Data Inicio: 'xx/xx/xx'</i></html>");
        buttonRoteiro.setBackground(new Color(19, 168, 173, 112));
        buttonRoteiro.setMaximumSize(new Dimension(400, 50));
        buttonRoteiro.setFont(interFont.deriveFont(16f));

        //botao criar roteiro

        JButton buttonCriarRoteiro = new JButton("Criar Roteiro");

        buttonCriarRoteiro.setMaximumSize(new Dimension(120, 40));
        buttonCriarRoteiro.setForeground(new Color(0x208482));
        buttonCriarRoteiro.setBackground(Color.WHITE);

        center.add(Box.createVerticalStrut(30));
        center.add(titleRoteiro);
        center.add(Box.createVerticalStrut(20));
        center.add(buttonRoteiro);
        center.add(Box.createVerticalStrut(20));
        center.add(buttonCriarRoteiro);

        containerMain.add(header,BorderLayout.NORTH);
        containerMain.add(center, BorderLayout.CENTER);

        setContentPane(containerMain);
        setVisible(true);

    }



}
