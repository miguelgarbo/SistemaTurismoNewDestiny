package View;

import Controller.PacoteController;
import Controller.PasseioController;
import Controller.UsuarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class TelaLogin extends JFrame {
    private UsuarioController usuarioController;
    private Font interFont = null;
    private Font interFontBold = null;
    private PasseioController passeioController;
    private PacoteController pacoteController;


    public TelaLogin(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController) {
        this.usuarioController = usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
    }

    public void iniciarTela() {
        setTitle("Tela de Login");
        setSize(440, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        interFont = FontLoader.loadFont("/fontsNewDestiny/Inter.ttc", 16f);
        interFontBold = FontLoader.loadFont("/fontsNewDestiny/InterVariable.ttf", 16f);

        BackgroundPanel containerMain = new BackgroundPanel("/photos/backgroundMain.png");
        containerMain.setLayout(new BoxLayout(containerMain, BoxLayout.Y_AXIS));
        containerMain.setBorder(BorderFactory.createEmptyBorder(30, 90, 30, 90));

        CoresProjeto coresProjeto = new CoresProjeto();

        JPanel containerTexts = new JPanel();
        containerTexts.setOpaque(false);
        containerTexts.setLayout(new BoxLayout(containerTexts, BoxLayout.Y_AXIS));

        JLabel loginText = new JLabel("<html><b>Login</b></html>");

        loginText.setFont(interFontBold.deriveFont(32f));
        loginText.setForeground(Color.WHITE);

        JLabel loginSubText = new JLabel("Acesse sua conta e aproveite !");
        loginSubText.setFont(interFontBold.deriveFont(16f));
        loginSubText.setForeground(Color.WHITE);

        loginText.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginSubText.setAlignmentX(Component.LEFT_ALIGNMENT);

        containerTexts.add(loginText);
        containerTexts.add(Box.createVerticalStrut(10));
        containerTexts.add(loginSubText);

        JLabel emailText = new JLabel("<html><b>Email</b></html>");
        emailText.setForeground(Color.WHITE);
        emailText.setFont(interFont.deriveFont(18f));

        JTextField fieldEmail = new JTextField(15);

        fieldEmail.setBackground(coresProjeto.corOpacaField);

        fieldEmail.setForeground(Color.BLACK);
        fieldEmail.setFont(interFontBold);
        fieldEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel senhaText = new JLabel("<html><b>Senha</b></html>");
        senhaText.setFont(interFont.deriveFont(18f));
        senhaText.setForeground(Color.WHITE);

        JPasswordField fieldSenha = new JPasswordField( 15);
        fieldSenha.setBackground(coresProjeto.corOpacaField);

        fieldSenha.setForeground(Color.BLACK);
        fieldSenha.setFont(interFontBold);
        fieldSenha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        senhaText.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton buttonLogin = new JButton("Entrar");
        buttonLogin.setForeground(new Color(0x1c9430));
        buttonLogin.setBackground(Color.WHITE);
        buttonLogin.setMaximumSize(new Dimension(250, 40));

        JButton buttonRegister = new JButton("Cadastrar-se");
        buttonRegister.setForeground(coresProjeto.corAzulSecundaria);
        buttonRegister.setBackground(Color.WHITE);
        buttonRegister.setMaximumSize(new Dimension(250, 40));

        JLabel mensagemStatus = new JLabel();
        mensagemStatus.setForeground(Color.WHITE);
        mensagemStatus.setFont(interFont.deriveFont(14f));
        mensagemStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensagemStatus.setVisible(false);

        buttonLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e);

                String emailInformado = fieldEmail.getText();
                var senhaInformada = new String(fieldSenha.getPassword());

                boolean loginOk = usuarioController.loginSwing(emailInformado,senhaInformada);
                System.out.println(loginOk);

                if(Boolean.TRUE.equals(loginOk)){
                    mensagemStatus.setText("Login Realizado Com Sucesso");
                    mensagemStatus.setForeground(Color.GREEN);
                    mensagemStatus.setVisible(true);
                    containerMain.revalidate();
                    containerMain.repaint();
                    containerMain.setVisible(false);
                    dispose();
                    System.out.println("Chamando tela de perfil...");
                    TelaVisualizacao telaVisualizacao = new TelaVisualizacao(usuarioController, passeioController, pacoteController);
                    telaVisualizacao.iniciarTela();
                } else {
                    mensagemStatus.setText("Email ou Senha Inválidos");
                    mensagemStatus.setForeground(Color.RED);
                    mensagemStatus.setVisible(true);
                    containerMain.revalidate();
                    containerMain.repaint();
                }

                new javax.swing.Timer(3000, event -> {
                    mensagemStatus.setVisible(false);
                    containerMain.revalidate();
                    containerMain.repaint();
                    ((javax.swing.Timer) event.getSource()).stop();
                }).start();

            }
        });

        buttonRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TelaLogin.this.setVisible(false);
                TelaCadastro telaCadastro = new TelaCadastro(usuarioController, passeioController, pacoteController);
                telaCadastro.iniciarTela();
            }
        });

        containerMain.add(containerTexts);
        containerMain.add(Box.createVerticalStrut(100));

        containerMain.add(emailText);
        containerMain.add(Box.createVerticalStrut(5));
        containerMain.add(fieldEmail);

        containerMain.add(Box.createVerticalStrut(40));
        containerMain.add(senhaText);
        containerMain.add(Box.createVerticalStrut(5));
        containerMain.add(fieldSenha);

        containerMain.add(Box.createVerticalStrut(25));
        containerMain.add(buttonLogin);
        containerMain.add(Box.createVerticalStrut(5));
        containerMain.add(buttonRegister);
        containerMain.add(Box.createVerticalStrut(15));
        containerMain.add(mensagemStatus);

        setContentPane(containerMain);
        setVisible(true);
    }
}
