package View;
import Controller.PacoteController;
import Controller.PasseioController;
import Controller.UsuarioController;
import Model.Entidades.CartaoEntity;
import Model.Entidades.UsuarioEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CadastrarMetodoPagamento extends JFrame {

    private Font interFont = null;
    private Font interFontBold = null;
    private UsuarioController usuarioController;
    private PacoteController pacoteController;
    private PasseioController passeioController;
    private UsuarioEntity usuarioEntity;

    public CadastrarMetodoPagamento(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController) {
        this.usuarioController = usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
    }

    public void gerenciadorCartão(UsuarioEntity usuario){
        if (usuario.getCartoes() == null || usuario.getCartoes().isEmpty()) {
            iniciarTelaCadastroCartao();
        } else {
            iniciarTelaListarCartoesUsuario(usuario);
        }
    }

    public void iniciarTelaCadastroCartao() {
        interFont = FontLoader.loadFont("/fontsNewDestiny/Inter.ttc", 14f);
        interFontBold = FontLoader.loadFont("/fontsNewDestiny/InterVariable.ttf", 18f);

        BackgroundPanel containerMain = new BackgroundPanel("/photos/backgroundMain.png");
        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

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

        buttonBack.addActionListener(e -> {
            TelaPerfilUsuario telaPerfilUsuario = new TelaPerfilUsuario(usuarioController, pacoteController, passeioController);
            telaPerfilUsuario.iniciarPerfilUsuário();
        });


        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/photos/logo.png"));
        JLabel logoNew = new JLabel(imgLogo);

        header.setOpaque(false);
        header.add(buttonBack);
        header.add(Box.createHorizontalStrut(75));
        header.add(logoNew);
        containerMain.add(header, BorderLayout.NORTH);

        // Conteúdo central
        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        JLabel title = new JLabel("Cadastrar Cartão de Crédito");
        title.setFont(interFontBold.deriveFont(20f));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nomeCartao = new JTextField();
        nomeCartao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        nomeCartao.setFont(interFont);
        nomeCartao.setBorder(BorderFactory.createTitledBorder("Nome no Cartão"));

        JTextField numeroCartao = new JTextField();
        numeroCartao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        numeroCartao.setFont(interFont);
        numeroCartao.setBorder(BorderFactory.createTitledBorder("Número do Cartão"));

        JTextField validade = new JTextField();
        validade.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        validade.setFont(interFont);
        validade.setBorder(BorderFactory.createTitledBorder("Validade (MM/AA)"));

        JTextField cvv = new JTextField();
        cvv.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        cvv.setFont(interFont);
        cvv.setBorder(BorderFactory.createTitledBorder("CVV"));

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setMaximumSize(new Dimension(200, 40));
        botaoCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoCadastrar.setBackground(Color.WHITE);
        botaoCadastrar.setForeground(new Color(0x208482));
        botaoCadastrar.setFont(interFontBold.deriveFont(16f));
        botaoCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botaoCadastrar.addActionListener(e -> {
            String nome = nomeCartao.getText();
            String numero = numeroCartao.getText();
            String data = validade.getText();
            String cvvTexto = cvv.getText();

            // Aqui você pode validar os dados e salvar no banco
            JOptionPane.showMessageDialog(null, "Cartão cadastrado com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            TelaPerfilUsuario telaPerfilUsuario = new TelaPerfilUsuario(usuarioController, pacoteController, passeioController);
            telaPerfilUsuario.iniciarPerfilUsuário();
        });

        center.add(title);
        center.add(Box.createVerticalStrut(20));
        center.add(nomeCartao);
        center.add(Box.createVerticalStrut(15));
        center.add(numeroCartao);
        center.add(Box.createVerticalStrut(15));
        center.add(validade);
        center.add(Box.createVerticalStrut(15));
        center.add(cvv);
        center.add(Box.createVerticalStrut(30));
        center.add(botaoCadastrar);

        containerMain.add(center, BorderLayout.CENTER);

        setTitle("New Destiny - Cadastro de Cartão");
        setContentPane(containerMain);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void iniciarTelaListarCartoesUsuario(UsuarioEntity usuario) {
        interFont = FontLoader.loadFont("/fontsNewDestiny/Inter.ttc", 14f);
        interFontBold = FontLoader.loadFont("/fontsNewDestiny/InterVariable.ttf", 18f);

        BackgroundPanel containerMain = new BackgroundPanel("/photos/backgroundMain.png");
        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

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
        buttonBack.addActionListener(e -> dispose());

        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/photos/logo.png"));
        JLabel logoNew = new JLabel(imgLogo);

        header.setOpaque(false);
        header.add(buttonBack);
        header.add(Box.createHorizontalStrut(75));
        header.add(logoNew);
        containerMain.add(header, BorderLayout.NORTH);

        // Conteúdo Central
        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titulo = new JLabel("Cartões Cadastrados");
        titulo.setFont(interFontBold.deriveFont(20f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(titulo);
        center.add(Box.createVerticalStrut(20));

        var cartoes = usuario.getCartoes();

        if (usuario.getCartoes() == null || usuario.getCartoes().isEmpty()) {
            JLabel label = new JLabel("Nenhum cartão cadastrado.");
            label.setFont(interFont.deriveFont(16f));
            label.setForeground(Color.DARK_GRAY);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            center.add(label);

            JButton botaoCadastrar = new JButton("Cadastrar Cartão");
            botaoCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);
            botaoCadastrar.setMaximumSize(new Dimension(200, 40));
            botaoCadastrar.setBackground(Color.WHITE);
            botaoCadastrar.setForeground(new Color(0x208482));
            botaoCadastrar.setFont(interFontBold.deriveFont(15f));
            botaoCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            botaoCadastrar.addActionListener(e -> {
                dispose(); // fecha essa tela antes
                iniciarTelaCadastroCartao(); // abre a tela de cadastro
            });
            center.add(botaoCadastrar);
        } else {
            for (CartaoEntity cartao : cartoes) {
                String ultimos4 = cartao.getNumeroCartao().length() >= 4
                        ? cartao.getNumeroCartao().substring(cartao.getNumeroCartao().length() - 4)
                        : cartao.getNumeroCartao();

                String texto = String.format(
                        "<html><b>Cartão:</b> **** **** **** %s<br><b>Nome:</b> %s<br><b>Bandeira:</b> %s<br><b>Método:</b> %s</html>",
                        ultimos4,
                        cartao.getNomeCartao(),
                        cartao.getBandeira(),
                        cartao.getMetodo()
                );

                JLabel labelCartao = new JLabel(texto);
                labelCartao.setFont(interFont.deriveFont(16f));
                labelCartao.setForeground(new Color(0x208482));
                labelCartao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                labelCartao.setOpaque(true);
                labelCartao.setBackground(Color.WHITE);
                labelCartao.setAlignmentX(Component.CENTER_ALIGNMENT);

                center.add(labelCartao);
                center.add(Box.createVerticalStrut(15));
            }
            JButton botaoCadastrar = new JButton("Cadastrar Cartão");
            botaoCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);
            botaoCadastrar.setMaximumSize(new Dimension(200, 40));
            botaoCadastrar.setBackground(Color.WHITE);
            botaoCadastrar.setForeground(new Color(0x208482));
            botaoCadastrar.setFont(interFontBold.deriveFont(15f));
            botaoCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            botaoCadastrar.addActionListener(e -> {
                dispose(); // fecha essa tela antes
                iniciarTelaCadastroCartao(); // abre a tela de cadastro
            });
            center.add(botaoCadastrar);
        }

        containerMain.add(center, BorderLayout.CENTER);

        setTitle("New Destiny - Cartões Cadastrados");
        setContentPane(containerMain);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
