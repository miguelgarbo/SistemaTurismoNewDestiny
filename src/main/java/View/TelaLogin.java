package View;

import Controller.UsuarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.IOException;

public class TelaLogin extends JFrame {
    private UsuarioController usuarioController;

    public TelaLogin(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public void iniciarTela() {
        // Configurações padrão da tela de login
        setTitle("Tela de Login");
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

        CoresProjeto coresProjeto = new CoresProjeto();

        containerMain.setLayout(new BoxLayout(containerMain, BoxLayout.Y_AXIS));
        containerMain.setBorder(BorderFactory.createEmptyBorder(30, 90, 30, 90));

        JPanel containerTexts = new JPanel();
        containerTexts.setOpaque(false);
        containerTexts.setLayout(new BoxLayout(containerTexts, BoxLayout.Y_AXIS));

        JLabel loginText = new JLabel("<html><b>Login</b></html>");

        loginText.setFont(interFontBold.deriveFont(32f));
        loginText.setForeground(Color.WHITE);


        JLabel loginSubText = new JLabel("Acesse sua conta e aproveite !");
        loginSubText.setFont(interFontBold);
        loginSubText.setFont(interFontBold.deriveFont(16f));

        loginSubText.setForeground(Color.WHITE);

        //alinhamentos
        loginText.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginSubText.setAlignmentX(Component.LEFT_ALIGNMENT);

        containerTexts.add(loginText);
        containerTexts.add(Box.createVerticalStrut(10)); // espaço entre Login e o subtítulo
        containerTexts.add(loginSubText);

        /////////////////////////////////////////////////////////////

        JLabel emailText = new JLabel("<html><b>Email</b></html>");
        emailText.setForeground(Color.WHITE);
        emailText.setFont(interFont.deriveFont(18f));


        JTextField fieldEmail = new JTextField(15);

        fieldEmail.setBackground(coresProjeto.corOpacaField);

        fieldEmail.setForeground(Color.BLACK);
        fieldEmail.setFont(interFontBold); // Aplicar a fonte
        fieldEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        emailText.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldEmail.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        mensagemStatus.setForeground(Color.WHITE); // cor padrão
        mensagemStatus.setFont(interFont.deriveFont(14f));
        mensagemStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensagemStatus.setVisible(false); // começa invisível

        buttonLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e);

                String emailInformado = fieldEmail.getText();
                var senhaInformada = new String(fieldSenha.getPassword());

                boolean loginOk = usuarioController.loginSwing(emailInformado,senhaInformada);

                if(loginOk){

                    mensagemStatus.setText("Login Realizado Com Sucesso");
                    mensagemStatus.setForeground(Color.GREEN);
                    mensagemStatus.setVisible(true);
                    containerMain.revalidate();
                    containerMain.repaint();
                } else {
                    mensagemStatus.setText("Email ou Senha Inválidos");
                    mensagemStatus.setForeground(Color.RED);
                    mensagemStatus.setVisible(true);
                    containerMain.revalidate();
                    containerMain.repaint();
                }

                // Timer para esconder após 3 segundos
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
                TelaCadastro telaCadastro = new TelaCadastro(usuarioController);
                telaCadastro.iniciarTela();
            }
        });


        // Linha de adição na nossa div container main
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
