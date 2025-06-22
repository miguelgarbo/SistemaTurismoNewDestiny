package View;

import Controller.PacoteController;
import Controller.PasseioController;
import Controller.RoteiroController;
import Controller.UsuarioController;
import Model.Entidades.DiaEntity;
import Model.Entidades.PasseioEntity;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class TelaAddPasseioDia extends JFrame {

    private UsuarioController usuarioController;
    private PasseioController passeioController;
    private PacoteController pacoteController;
    private RoteiroController roteiroController;
    private DiaEntity dia;

    private Font interFont;
    private Font interFontBold;
    private CoresProjeto coresProjeto = new CoresProjeto(); // Correção: CoresProjeto

    public TelaAddPasseioDia(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController, RoteiroController roteiroController, DiaEntity dia) {
        this.usuarioController = usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
        this.roteiroController = roteiroController;
        this.dia = dia;
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
                TelaVisualizacao telaVisualizacao = new TelaVisualizacao(usuarioController, passeioController, pacoteController, roteiroController);
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

        JPanel centralContentWrapper = new JPanel();
        centralContentWrapper.setOpaque(false);
        centralContentWrapper.setLayout(new BoxLayout(centralContentWrapper, BoxLayout.Y_AXIS));

        JLabel tituloLabel = new JLabel("Adicionando Passeios Ao Dia");
        tituloLabel.setFont(interFontBold.deriveFont(Font.BOLD, 22f));
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel subtitleAndButtonPanel = new JPanel(new BorderLayout());
        subtitleAndButtonPanel.setOpaque(false);
        subtitleAndButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        JLabel subtituloLabel = new JLabel("<html>Selecione Os Passeios<br>Para Dia "+dia.getNumeroDoDia() +": "+dia.getdataReal()+"</html>");
        subtituloLabel.setFont(interFont.deriveFont(16f));
        subtituloLabel.setForeground(Color.WHITE);


        JButton botaoProximo = new JButton("Próximo");
        botaoProximo.setFont(interFont.deriveFont(16f));
        botaoProximo.setForeground(coresProjeto.corPrincipalAzul);
        botaoProximo.setBackground(Color.WHITE);
        botaoProximo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botaoProximo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                TelaRoteiroSelecionado telaAnterior = new TelaRoteiroSelecionado(usuarioController, pacoteController, passeioController, roteiroController);
                telaAnterior.iniciarTela(dia.getRoteiro());
                dispose();
            }
        });

        subtitleAndButtonPanel.add(subtituloLabel, BorderLayout.WEST);
        subtitleAndButtonPanel.add(botaoProximo, BorderLayout.EAST);

        centralContentWrapper.add(Box.createVerticalStrut(30));
        centralContentWrapper.add(tituloLabel);
        centralContentWrapper.add(Box.createVerticalStrut(10)); // Pequeno espaço entre título e subtítulo/botão
        centralContentWrapper.add(subtitleAndButtonPanel); // Adiciona o novo painel
        centralContentWrapper.add(Box.createVerticalStrut(30)); // Espaço depois do subtítulo/botão e antes dos cards

        JPanel cardsPanel = new JPanel();
        cardsPanel.setOpaque(false);
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40)); // Padding horizontal para os cards

        for(PasseioEntity passeio : passeioController.listaPasseiosCadastrados()){

            JPanel card = criarPasseioCard(passeio);
            card.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardsPanel.add(card);
            cardsPanel.add(Box.createVerticalStrut(5));
        }


        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remover borda padrão do JScrollPane

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

    public JPanel criarPasseioCard(PasseioEntity passeio) {
        JPanel cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(300, 200));
        cardPanel.setLayout(new OverlayLayout(cardPanel));

        try {
            // Carregar e redimensionar imagem
            String urlImagem = passeio.getListaFotos().get(0).getUrl();
            URL url = new URL(urlImagem);
            ImageIcon imgIcon = new ImageIcon(url);
            Image imagem = imgIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(imagem));
            imageLabel.setPreferredSize(new Dimension(300, 200));
            imageLabel.setAlignmentX(0.0f);
            imageLabel.setAlignmentY(0.0f);

            // Painel de título (fundo preto transparente)
            JPanel titlePanel = new JPanel();
            titlePanel.setOpaque(true);
            titlePanel.setBackground(new Color(0, 0, 0, 150));
            titlePanel.setPreferredSize(new Dimension(300, 50));
            titlePanel.setMaximumSize(new Dimension(300, 50));
            titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));

            JLabel titleLabel = new JLabel(passeio.getTitulo());
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(interFont.deriveFont(16f));
            titlePanel.add(titleLabel);

            // Container Y para empurrar o título pra baixo
            JPanel container = new JPanel();
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
            container.setOpaque(false);
            container.setPreferredSize(new Dimension(300, 200));
            container.setMaximumSize(new Dimension(300, 200));
            container.setAlignmentX(0.0f);
            container.setAlignmentY(0.0f);

            container.add(Box.createVerticalGlue()); // empurra pra baixo
            container.add(titlePanel);

            // Adiciona primeiro a imagem, depois o container com o título
            cardPanel.add(container);    // título por cima
            cardPanel.add(imageLabel);   // imagem no fundo

        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + e.getMessage());
            cardPanel.add(new JLabel("Imagem indisponível"));
        }

        cardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                dia.addPasseio(passeio);
                passeioController.diaRepository().atualizar(dia);
                JOptionPane.showMessageDialog(null, passeio.getTitulo()+" adicionado ao Dia "+ dia.getNumeroDoDia()+": "+dia.getdataReal());
            }
        });

        cardPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY, 1, true), // true = arredondado
                        BorderFactory.createEmptyBorder(5, 5, 5, 5) // espaço interno
                )
        );


        return cardPanel;
    }
}