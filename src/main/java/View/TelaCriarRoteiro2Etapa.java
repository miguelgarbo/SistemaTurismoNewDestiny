package View;

import Controller.PacoteController;
import Controller.PasseioController;
import Controller.RoteiroController;
import Controller.UsuarioController;
import Model.Entidades.DiaEntity;
import Model.Entidades.PasseioEntity;
import Model.Entidades.RoteiroPersonalizadoEntity;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class TelaCriarRoteiro2Etapa extends JFrame {

    private UsuarioController usuarioController;
    private PasseioController passeioController;
    private PacoteController pacoteController;
    private RoteiroController roteiroController;

    private Font interFont;
    private Font interFontBold;
    private CoresProjeto coresProjeto = new CoresProjeto();

    public TelaCriarRoteiro2Etapa(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController, RoteiroController roteiroController) {
        this.usuarioController = usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
        this.roteiroController = roteiroController;
    }

    public void iniciarTela(RoteiroPersonalizadoEntity roteiroNovo) {
        setTitle("Criando Seu Roteiro 2");
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

        // Conteúdo
        JPanel contentPanelForScroll = new JPanel();
        contentPanelForScroll.setOpaque(false);
        contentPanelForScroll.setLayout(new BoxLayout(contentPanelForScroll, BoxLayout.Y_AXIS));

        JLabel tituloLabel = new JLabel("Criando Seu Roteiro");
        tituloLabel.setFont(interFontBold.deriveFont(Font.BOLD, 22f));
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanelForScroll.add(Box.createVerticalStrut(30));
        contentPanelForScroll.add(tituloLabel);
        contentPanelForScroll.add(Box.createVerticalStrut(30));

        for(DiaEntity dia : roteiroNovo.getDias()){
            contentPanelForScroll.add(createDayBlock(dia));
            contentPanelForScroll.add(Box.createVerticalStrut(10));

        }

        JButton botaoProximo = new JButton("Pronto");
        botaoProximo.setFont(interFont.deriveFont(16f));
        botaoProximo.setForeground(coresProjeto.corPrincipalAzul);
        botaoProximo.setBackground(Color.WHITE);
        botaoProximo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoProximo.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoProximo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "Roteiro Criado com Sucesso");
                TelaVisualizacao telaVisualizacao = new TelaVisualizacao(usuarioController, passeioController, pacoteController, roteiroController);
                telaVisualizacao.iniciarTela();
            }
        });

        contentPanelForScroll.add(Box.createVerticalStrut(30));
        contentPanelForScroll.add(botaoProximo);
        contentPanelForScroll.add(Box.createVerticalStrut(30));

        JScrollPane scrollPane = new JScrollPane(contentPanelForScroll);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Estilização da barra de rolagem fina
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

        containerMain.add(header, BorderLayout.NORTH);
        containerMain.add(scrollPane, BorderLayout.CENTER);

        setContentPane(containerMain);
        setVisible(true);
    }

    private JPanel createDayBlock(DiaEntity dia) {
        JPanel blockDia = new JPanel();
        blockDia.setLayout(new BorderLayout());
        blockDia.setBackground(new Color(0x13A8AD >> 16 & 0xFF, 0x13A8AD >> 8 & 0xFF, 0x13A8AD & 0xFF, 102));

        JPanel topoPanel = new JPanel(new BorderLayout());
        topoPanel.setBackground(new Color(0x13A8AD >> 16 & 0xFF, 0x13A8AD >> 8 & 0xFF, 0x13A8AD & 0xFF, 102));
        topoPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel diaLabel = new JLabel("<html><b>Dia " + dia.getNumeroDoDia() + "</b></html>");
        diaLabel.setForeground(Color.WHITE);
        diaLabel.setFont(interFont.deriveFont(16f));

        JLabel dataLabel = new JLabel("<html><b>"+dia.getdataReal()+"</b></html>");
        dataLabel.setForeground(Color.WHITE);
        dataLabel.setFont(interFont.deriveFont(16f));

        topoPanel.add(diaLabel, BorderLayout.WEST);
        topoPanel.add(dataLabel, BorderLayout.EAST);


        JButton addButton = new JButton("Add Passeio");

        addButton.setPreferredSize(new Dimension(120, 30));
        addButton.setForeground(new Color(0x208482));
        addButton.setBackground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Layout principal
        JPanel centroPanel = new JPanel();
        centroPanel.setBackground(new Color(0x13A8AD));
        centroPanel.setOpaque(false);
        centroPanel.setLayout(new BoxLayout(centroPanel, BoxLayout.Y_AXIS));
        centroPanel.setMinimumSize(new Dimension(350, 75));  // largura fixa e altura mínima
        centroPanel.setPreferredSize(new Dimension(350, 75));


        for(PasseioEntity passeio : dia.getPasseios()){

            JPanel passeioPanel = criarPasseioDentroDia(passeio);
            centroPanel.add(passeioPanel);
            centroPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Alterado de 0 para 5 pixels
        }

        centroPanel.add(addButton);
        centroPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Top/Bottom de 5 pixels

        blockDia.add(topoPanel, BorderLayout.NORTH);
        blockDia.add(centroPanel, BorderLayout.CENTER);

        JPanel blocoDiaPanelCentralizado = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        blocoDiaPanelCentralizado.setOpaque(false);
        blocoDiaPanelCentralizado.add(blockDia);

        return blocoDiaPanelCentralizado;
    }

    public JPanel criarPasseioDentroDia(PasseioEntity passeio) {
        JPanel passeioPanel = new JPanel();
        passeioPanel.setOpaque(false);
        passeioPanel.setLayout(new BorderLayout());
        passeioPanel.setPreferredSize(new Dimension(350, 70)); // um pouco maior pra imagem caber bem
        passeioPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Painel para texto (titulo + horário)
        JPanel textoPanel = new JPanel();
        textoPanel.setOpaque(false);
        textoPanel.setLayout(new BoxLayout(textoPanel, BoxLayout.Y_AXIS));

        JLabel nomePasseio = new JLabel("<html><b>" + passeio.getTitulo() + "</b></html>");
        nomePasseio.setForeground(Color.WHITE);
        nomePasseio.setFont(interFont.deriveFont(18f));

        JLabel horario = new JLabel("<html><b>" + passeio.getHorarios() + "</b></html>");
        horario.setForeground(Color.WHITE);
        horario.setFont(interFont.deriveFont(16f));

        textoPanel.add(nomePasseio);
        textoPanel.add(horario);

        // Carregar imagem como na criarPasseioCard
        JLabel imagemLabel = new JLabel();
        try {
            String urlImagem = passeio.getListaFotos().get(0).getUrl();
            URL url = new URL(urlImagem);
            ImageIcon imgIcon = new ImageIcon(url);
            Image imagem = imgIcon.getImage().getScaledInstance(80, 70, Image.SCALE_SMOOTH);
            imagemLabel.setIcon(new ImageIcon(imagem));
            imagemLabel.setPreferredSize(new Dimension(80, 70));
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem do passeio: " + e.getMessage());
            imagemLabel.setText("Imagem indisponível");
            imagemLabel.setForeground(Color.WHITE);
            imagemLabel.setPreferredSize(new Dimension(80, 70));
            imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imagemLabel.setVerticalAlignment(SwingConstants.CENTER);
        }

        passeioPanel.add(imagemLabel, BorderLayout.WEST);
        passeioPanel.add(textoPanel, BorderLayout.CENTER);

        return passeioPanel;
    }

}
