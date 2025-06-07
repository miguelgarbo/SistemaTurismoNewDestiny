package View;

import Controller.PacoteController;
import Controller.PasseioController;
import Controller.UsuarioController;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.image.BufferedImage; // Importar para BufferedImage

public class TelaCriarRoteiro3Etapa extends JFrame {

    private UsuarioController usuarioController;
    private PasseioController passeioController;
    private PacoteController pacoteController;
    private Font interFont;
    private Font interFontBold;
    private CoresProjeto coresProjeto = new CoresProjeto(); // Correção: CoresProjeto

    public TelaCriarRoteiro3Etapa(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController) {
        this.usuarioController = usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
    }

    public void iniciarTela() {
        setTitle("Criando Seu Roteiro 3");
        setSize(440, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        interFont = FontLoader.loadFont("/fontsNewDestiny/Inter.ttc", 16f);
        interFontBold = FontLoader.loadFont("/fontsNewDestiny/InterVariable.ttf", 16f);

        BackgroundPanel containerMain = new BackgroundPanel("/photos/backgroundMain.png");
        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        // Header (existente)
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
                dispose();
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

        // --- Painel para o Conteúdo Central (Título Fixo + Subtítulo + Botão "Próximo" + ScrollPane de Cards) ---
        JPanel centralContentWrapper = new JPanel();
        centralContentWrapper.setOpaque(false);
        centralContentWrapper.setLayout(new BoxLayout(centralContentWrapper, BoxLayout.Y_AXIS));

        JLabel tituloLabel = new JLabel("Criando Seu Roteiro");
        tituloLabel.setFont(interFontBold.deriveFont(Font.BOLD, 22f));
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel para o subtítulo e o botão "Próximo"
        JPanel subtitleAndButtonPanel = new JPanel(new BorderLayout());
        subtitleAndButtonPanel.setOpaque(false);
        // Padding lateral para este painel, alinhado com o padding dos cards
        subtitleAndButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        JLabel subtituloLabel = new JLabel("<html>Selecione Os Passeios<br>Para Dia: xx/xx/xxxx</html>");
        subtituloLabel.setFont(interFont.deriveFont(16f));
        subtituloLabel.setForeground(Color.WHITE);
        // Remover setAlignmentX pois o BorderLayout vai gerenciar o posicionamento
        // subtituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Nao precisa de setHorizontalAlignment aqui se ele estiver no CENTER do BorderLayout
        // subtituloLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton botaoProximo = new JButton("Próximo");
        botaoProximo.setFont(interFont.deriveFont(16f));
        botaoProximo.setForeground(coresProjeto.corPrincipalAzul);
        // Remover setAlignmentX pois o BorderLayout vai gerenciar o posicionamento
        // botaoProximo.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoProximo.setBackground(Color.WHITE);
        botaoProximo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botaoProximo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // Adicione a lógica para a próxima tela aqui, se houver
            }
        });

        subtitleAndButtonPanel.add(subtituloLabel, BorderLayout.WEST); // Subtítulo à esquerda
        subtitleAndButtonPanel.add(botaoProximo, BorderLayout.EAST);   // Botão à direita

        centralContentWrapper.add(Box.createVerticalStrut(30));
        centralContentWrapper.add(tituloLabel);
        centralContentWrapper.add(Box.createVerticalStrut(10)); // Pequeno espaço entre título e subtítulo/botão
        centralContentWrapper.add(subtitleAndButtonPanel); // Adiciona o novo painel
        centralContentWrapper.add(Box.createVerticalStrut(30)); // Espaço depois do subtítulo/botão e antes dos cards

        // Painel que conterá os cards de passeio para rolagem
        JPanel cardsPanel = new JPanel();
        cardsPanel.setOpaque(false);
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40)); // Padding horizontal para os cards

        for (int i = 0; i < 5; i++) { // Exemplo: 5 cards
            JPanel card = criarPasseioCard();
            card.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza cada card horizontalmente
            cardsPanel.add(card);
            if (i < 4) { // Adiciona espaço entre os cards, exceto após o último
                cardsPanel.add(Box.createVerticalStrut(20)); // Espaço entre os cards
            }
        }

        // JScrollPane para os cards
        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remover borda padrão do JScrollPane

        // Estilo da barra de rolagem
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(100, 100, 100, 255);
                this.trackColor = new Color(50, 50, 50, 255);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }

            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(5, super.getPreferredSize(c).height);
            }
        });

        centralContentWrapper.add(scrollPane);

        containerMain.add(header, BorderLayout.NORTH);
        containerMain.add(centralContentWrapper, BorderLayout.CENTER);

        setContentPane(containerMain);
        setVisible(true);
    }

    public JPanel criarPasseioCard() {
        JPanel cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(360, 200));
        cardPanel.setMaximumSize(new Dimension(360, 200));

        cardPanel.setLayout(new OverlayLayout(cardPanel));

        String caminhoImagem = "caminho/para/sua/imagem.jpg"; // Este caminho deve ser válido para a imagem aparecer
        ImageIcon icon = new ImageIcon(caminhoImagem);

        JLabel imageLabel = new JLabel(icon);
        imageLabel.setAlignmentX(0.5f);
        imageLabel.setAlignmentY(0.5f);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 0, 0, 150));
        titlePanel.setPreferredSize(new Dimension(360, 50));
        titlePanel.setMaximumSize(new Dimension(360, 50));
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));

        JLabel titlePasseio = new JLabel("Passeio");
        titlePasseio.setForeground(Color.WHITE);
        titlePasseio.setFont(interFont.deriveFont(16f));
        titlePanel.add(titlePasseio);

        titlePanel.setAlignmentX(0.5f);
        titlePanel.setAlignmentY(1.0f);

        cardPanel.add(titlePanel);
        cardPanel.add(imageLabel);
        return cardPanel;
    }
}