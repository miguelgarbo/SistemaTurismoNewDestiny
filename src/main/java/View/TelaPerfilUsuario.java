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

        // Header
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

        buttonBack.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e);
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
