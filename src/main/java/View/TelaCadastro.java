package View;

import Controller.UsuarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.IOException;

public class TelaCadastro extends JFrame {
    
    private UsuarioController usuarioController;
    
    public TelaCadastro(UsuarioController usuarioController){
        this.usuarioController = usuarioController;
    }

    public void iniciarTela() {
        setTitle("Tela de Cadastro");
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

        JLabel cadastroText = new JLabel("Cadastro");

        cadastroText.setFont(interFontBold.deriveFont(32f));
        cadastroText.setForeground(Color.WHITE);


        JLabel cadastroSubText = new JLabel("Junte-se a nós e aproveite !");
        cadastroSubText.setFont(interFontBold);
        cadastroSubText.setFont(interFontBold.deriveFont(16f));

        cadastroSubText.setForeground(Color.WHITE);

         //alinhando

        cadastroText.setAlignmentX(Component.LEFT_ALIGNMENT);
        cadastroSubText.setAlignmentX(Component.LEFT_ALIGNMENT);

        containerTexts.add(cadastroText);
        containerTexts.add(Box.createVerticalStrut(10));
        containerTexts.add(cadastroSubText);

        /////////////////////////////////////////////////////////////

        JLabel nameText = new JLabel("Nome: ");
        nameText.setFont(interFont.deriveFont(18f));
        nameText.setForeground(Color.WHITE);

        JTextField fieldName = new JTextField(15);
        fieldName.setBackground(coresProjeto.corOpacaField);
        fieldName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        nameText.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailText = new JLabel("Email:");
        emailText.setForeground(Color.WHITE);
        emailText.setFont(interFont.deriveFont(18f));


        JTextField fieldEmail = new JTextField(15);

        fieldEmail.setBackground(coresProjeto.corOpacaField);
        fieldEmail.setOpaque(true);

        fieldEmail.setForeground(Color.BLACK);
        fieldEmail.setFont(interFontBold); // Aplicar a fonte
        fieldEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        emailText.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldEmail.setAlignmentX(Component.CENTER_ALIGNMENT);



        JLabel numeroText = new JLabel("Numero De Telefone: ");
        numeroText.setFont(interFont.deriveFont(18f));
        numeroText.setForeground(Color.WHITE);

        JTextField fieldNumero = new JTextField( 15);
        fieldNumero.setBackground(coresProjeto.corOpacaField);
        fieldNumero.setOpaque(true);

        fieldNumero.setForeground(Color.BLACK);
        fieldNumero.setFont(interFontBold);
        fieldNumero.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        numeroText.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldNumero.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel senhaText = new JLabel("Senha");
        senhaText.setFont(interFont.deriveFont(18f));
        senhaText.setForeground(Color.WHITE);

        JTextField fieldSenha = new JTextField( 15);
        fieldSenha.setBackground(coresProjeto.corOpacaField);
        fieldSenha.setOpaque(true);

        fieldSenha.setForeground(Color.BLACK);
        fieldSenha.setFont(interFontBold);
        fieldSenha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        senhaText.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton buttonRegistrar = new JButton("Registrar");


        JLabel mensagemStatus = new JLabel();
        mensagemStatus.setForeground(Color.WHITE); // cor padrão
        mensagemStatus.setFont(interFont.deriveFont(14f));
        mensagemStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensagemStatus.setVisible(false); // começa invisível

        buttonRegistrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String nomeInformado = fieldName.getText();
                String emailInformado = fieldEmail.getText();
                String senhaInformada = fieldSenha.getText();
                String numeroInformado = fieldNumero.getText();
                boolean cadastroOk = usuarioController.swingCadastro(nomeInformado, emailInformado,senhaInformada, numeroInformado);


                if(cadastroOk){

                    mensagemStatus.setText("Cadastro Realizado Com Sucesso");
                    mensagemStatus.setForeground(Color.GREEN);
                    mensagemStatus.setVisible(true);
                    containerMain.revalidate();
                    containerMain.repaint();
                } else {
                    mensagemStatus.setText("Dados Inválidos");
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

        buttonRegistrar.setForeground(coresProjeto.corPrincipalAzul);
        buttonRegistrar.setBackground(Color.WHITE);

        buttonRegistrar.setMaximumSize(new Dimension(250, 40));

        // Linha de adição na nossa div container main
        containerMain.add(containerTexts);
        containerMain.add(Box.createVerticalStrut(70));

        containerMain.add(nameText);
        containerMain.add(fieldName);
        containerMain.add(Box.createVerticalStrut(40));

        containerMain.add(emailText);
        containerMain.add(Box.createVerticalStrut(5));
        containerMain.add(fieldEmail);
        containerMain.add(Box.createVerticalStrut(40));


        containerMain.add(numeroText);
        containerMain.add(Box.createVerticalStrut(5));
        containerMain.add(fieldNumero);

        containerMain.add(Box.createVerticalStrut(40));
        containerMain.add(senhaText);
        containerMain.add(Box.createVerticalStrut(5));
        containerMain.add(fieldSenha);

        containerMain.add(Box.createVerticalStrut(25));
        containerMain.add(buttonRegistrar);

        containerMain.add(Box.createVerticalStrut(15));
        containerMain.add(mensagemStatus);


        setContentPane(containerMain);
        setVisible(true);
    }
}
