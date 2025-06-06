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

public class TelaRoteiroSelecionado extends JFrame {

    private UsuarioController usuarioController;
    private PasseioController passeioController;
    private PacoteController pacoteController;
    private Font interFont = null;
    private Font interFontBold = null;

    public TelaRoteiroSelecionado(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController){
        this.usuarioController =usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
    }

    public void iniciarTela(){

        // Configurações padrão da tela de login
        setTitle("Roteiros Selecionado");
        setSize(440, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        interFont = FontLoader.loadFont("/fontsNewDestiny/Inter.ttc", 16f);
        interFontBold = FontLoader.loadFont("/fontsNewDestiny/InterVariable.ttf", 16f);

        BackgroundPanel containerMain = new BackgroundPanel("/photos/backgroundMain.png");
        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

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
                TelaVisualizacao telaVisualizacao = new TelaVisualizacao(usuarioController, passeioController,pacoteController);
                telaVisualizacao.iniciarTela();
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
        logoNew.setSize(100,50);


        header.setOpaque(false);
        header.add(buttonBack);
        header.add(Box.createHorizontalStrut(75)); // Seu strut original
        header.add(logoNew);

// fim da header

        // Título
        JLabel titleRoteiro = new JLabel("<html><b>'Nome Roteiro'</b></html>");
        titleRoteiro.setFont(interFontBold != null ? interFontBold.deriveFont(20f) : new Font("SansSerif", Font.BOLD, 20)); // Fallback para fonte
        titleRoteiro.setForeground(Color.WHITE);
        titleRoteiro.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel rowTitle = new JPanel();
        rowTitle.setOpaque(false);
        rowTitle.setLayout(new BoxLayout(rowTitle, BoxLayout.X_AXIS));
        rowTitle.add(titleRoteiro);
        rowTitle.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));


        center.add(Box.createVerticalStrut(20));
        center.add(rowTitle);
        center.add(Box.createVerticalStrut(20));

        //criação dos dias
        for (int i = 1; i <= 3; i++) {
            JPanel blocoDiaWrapper = createDayBlock(i);
            center.add(blocoDiaWrapper);
            if (i < 10) {
                center.add(Box.createVerticalStrut(15));
            }
        }

        JScrollPane scrollPane = new JScrollPane(center);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // barra de scroll
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                // Cores opacas, mas discretas
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
                // Define a largura preferencial da barra de rolagem (5 pixels)
                return new Dimension(5, super.getPreferredSize(c).height);
            }
        });


        containerMain.add(header,BorderLayout.NORTH);
        containerMain.add(scrollPane,BorderLayout.CENTER);
        setContentPane(containerMain);
        setVisible(true);
    }


    private JPanel createDayBlock(int diaEntidade) {
        JPanel blockDia = new JPanel();
        blockDia.setLayout(new BorderLayout());
        blockDia.setBackground(new Color(0x13A8AD >> 16 & 0xFF, 0x13A8AD >> 8 & 0xFF, 0x13A8AD & 0xFF, 102));

        JPanel topoPanel = new JPanel(new BorderLayout());
        topoPanel.setBackground(new Color(0x13A8AD >> 16 & 0xFF, 0x13A8AD >> 8 & 0xFF, 0x13A8AD & 0xFF, 102));
        topoPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel diaLabel = new JLabel("<html><b>Dia " + diaEntidade + "</b></html>");
        diaLabel.setForeground(Color.WHITE);
        diaLabel.setFont(interFont.deriveFont(16f));

        JLabel dataLabel = new JLabel("<html><b>xx/xx/xxxx</b></html>");
        dataLabel.setForeground(Color.WHITE);
        dataLabel.setFont(interFont.deriveFont(16f));

        topoPanel.add(diaLabel, BorderLayout.WEST);
        topoPanel.add(dataLabel, BorderLayout.EAST);

        BackgroundPanel passeioPanel = new BackgroundPanel("/photos/backgroundPasseio.png");
        passeioPanel.setOpaque(false);
        passeioPanel.setLayout(new BorderLayout());
        passeioPanel.setPreferredSize(new Dimension(350, 50));
        passeioPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel nomePasseio = new JLabel("<html><b>'Nome Passeio'</b></html>");
        nomePasseio.setForeground(Color.WHITE);
        nomePasseio.setFont(interFont.deriveFont(18f));
        JLabel horario = new JLabel("<html><b>12:30</b></html>");
        horario.setForeground(Color.WHITE);
        horario.setFont(interFont.deriveFont(18f));

        passeioPanel.add(nomePasseio, BorderLayout.WEST);
        passeioPanel.add(horario, BorderLayout.EAST);

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

        centroPanel.add(passeioPanel);
        centroPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Alterado de 0 para 5 pixels
        centroPanel.add(addButton);
        centroPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Top/Bottom de 5 pixels

        blockDia.add(topoPanel, BorderLayout.NORTH);
        blockDia.add(centroPanel, BorderLayout.CENTER);

        JPanel blocoDiaPanelCentralizado = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        blocoDiaPanelCentralizado.setOpaque(false);
        blocoDiaPanelCentralizado.add(blockDia);

        return blocoDiaPanelCentralizado;
    }
}
