package View;

import Controller.PacoteController;
import Controller.PasseioController;
import Controller.RoteiroController;
import Controller.UsuarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class EditarPerfilUsuario extends JFrame {
    private Font interFont = null;
    private Font interFontBold = null;
    private UsuarioController usuarioController;
    private PacoteController pacoteController;
    private PasseioController passeioController;
    private RoteiroController roteiroController;


    public EditarPerfilUsuario(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController, RoteiroController roteiroController) {
        this.usuarioController = usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
        this.roteiroController = roteiroController;
    }

    public void editarPerfil() {
        interFont = FontLoader.loadFont("/fontsNewDestiny/Inter.ttc", 14f);
        interFontBold = FontLoader.loadFont("/fontsNewDestiny/InterVariable.ttf", 18f);

        BackgroundPanel containerMain = new BackgroundPanel("/photos/backgroundMain.png");
        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        // Header
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon imgButtonBack = new ImageIcon(getClass().getResource("/photos/arrowBack.png"));
        Image img = imgButtonBack.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton buttonBack = new JButton(new ImageIcon(img));
        buttonBack.setBorderPainted(false);
        buttonBack.setContentAreaFilled(false);
        buttonBack.setFocusPainted(false);
        buttonBack.setOpaque(false);
        buttonBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/photos/logo.png"));
        JLabel logoNew = new JLabel(imgLogo);

        header.setOpaque(false);
        header.add(buttonBack);
        header.add(Box.createHorizontalStrut(75));
        header.add(logoNew);
        containerMain.add(header, BorderLayout.NORTH);

        // Painel central
        JPanel painelCentral = new JPanel();
        painelCentral.setOpaque(false);
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));

        JTextField[] nomeRef = new JTextField[1];
        JTextField[] emailRef = new JTextField[1];
        JPasswordField[] senha1Ref = new JPasswordField[1];
        JPasswordField[] senha2Ref = new JPasswordField[1];
        JTextField[] telefoneRef = new JTextField[1];

        painelCentral.add(Box.createVerticalStrut(20));
        painelCentral.add(criarCampo("Nome", "Digite seu nome", nomeRef));
        painelCentral.add(Box.createVerticalStrut(20));
        painelCentral.add(criarCampo("Email", "Digite seu email", emailRef));
        painelCentral.add(Box.createVerticalStrut(20));
        painelCentral.add(criarCampoSenha("Senha", "Digite nova Senha", senha1Ref));
        painelCentral.add(Box.createVerticalStrut(20));
        painelCentral.add(criarCampoSenha("Senha", "Confirme a Senha", senha2Ref));
        painelCentral.add(Box.createVerticalStrut(20));
        painelCentral.add(criarCampo("Telefone", "Digite seu telefone", telefoneRef));
        painelCentral.add(Box.createVerticalStrut(30));

        JButton botaoSalvar = new JButton("Salvar Alterações");
        botaoSalvar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoSalvar.setMaximumSize(new Dimension(250, 40));
        botaoSalvar.setFont(interFontBold.deriveFont(16f));
        botaoSalvar.setBackground(Color.WHITE);
        botaoSalvar.setForeground(new Color(0x1c9430));
        botaoSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botaoSalvar.addActionListener(e -> {
            String nome = nomeRef[0].getText();
            String email = emailRef[0].getText();
            String senha1 = new String(senha1Ref[0].getPassword());
            String senha2 = new String(senha2Ref[0].getPassword());
            String telefone = telefoneRef[0].getText();

            // usuarioController.atualizarPerfil(nome, email, senha, telefone);

            JOptionPane.showMessageDialog(this, "Perfil atualizado com sucesso!");
            this.dispose();
            new TelaPerfilUsuario(usuarioController,pacoteController,passeioController,roteiroController).iniciarPerfilUsuário();
        });

        painelCentral.add(botaoSalvar);
        painelCentral.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); //permite selecionar a posição diretamento com pixels
        containerMain.add(painelCentral, BorderLayout.CENTER);
        setContentPane(containerMain);

        buttonBack.addActionListener(e -> {
            this.dispose();
            new TelaPerfilUsuario(usuarioController,pacoteController,passeioController,roteiroController).iniciarPerfilUsuário();
        });

        setTitle("Perfil do Usuário");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(440, 920);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel criarCampo(String labelText, String placeholder, JTextField[] campoRef) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(interFontBold.deriveFont(16f));

        JTextField campo = new JTextField(20);
        campo.setMaximumSize(new Dimension(300, 30));
        campo.setFont(interFont);
        campo.setText(placeholder);
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campo.setBackground(Color.WHITE);

        campo.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(placeholder)) campo.setText("");
            }

            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) campo.setText(placeholder);
            }
        });

        campoRef[0] = campo;

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);
        painel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(label);
        painel.add(Box.createVerticalStrut(5));
        painel.add(campo);

        return painel;
    }

    private JPanel criarCampoSenha(String labelText, String placeholder, JPasswordField[] campoRef) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(interFontBold.deriveFont(16f));

        JPasswordField campo = new JPasswordField(20);
        campo.setMaximumSize(new Dimension(300, 30));
        campo.setFont(interFont);
        campo.setText(placeholder);
        campo.setEchoChar((char) 0);
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campo.setBackground(Color.WHITE);

        campo.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(campo.getPassword()).equals(placeholder)) {
                    campo.setText("");
                    campo.setEchoChar('•');
                }
            }

            public void focusLost(FocusEvent e) {
                if (String.valueOf(campo.getPassword()).isEmpty()) {
                    campo.setText(placeholder);
                    campo.setEchoChar((char) 0);
                }
            }
        });

        campoRef[0] = campo;

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);
        painel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(label);
        painel.add(Box.createVerticalStrut(5));
        painel.add(campo);

        return painel;
    }

    private JPanel criarCampoConfirmaçãoSenha(String labelText, String placeholder, JPasswordField[] campoRef) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(interFontBold.deriveFont(16f));

        JPasswordField campo = new JPasswordField(20);
        campo.setMaximumSize(new Dimension(300, 30));
        campo.setFont(interFont);
        campo.setText(placeholder);
        campo.setEchoChar((char) 0);
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campo.setBackground(Color.WHITE);

        campo.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(campo.getPassword()).equals(placeholder)) {
                    campo.setText("");
                    campo.setEchoChar('•');
                }
            }

            public void focusLost(FocusEvent e) {
                if (String.valueOf(campo.getPassword()).isEmpty()) {
                    campo.setText(placeholder);
                    campo.setEchoChar((char) 0);
                }
            }
        });

        campoRef[0] = campo;

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);
        painel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(label);
        painel.add(Box.createVerticalStrut(5));
        painel.add(campo);

        return painel;
    }
}
