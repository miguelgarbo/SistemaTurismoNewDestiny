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

public class TelaRoteiroSelecionado extends JFrame {

    private UsuarioController usuarioController;
    private PasseioController passeioController;
    private PacoteController pacoteController;
    private RoteiroController roteiroController;
    private Font interFont = null;
    private Font interFontBold = null;

    public TelaRoteiroSelecionado(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController, RoteiroController roteiroController){
        this.usuarioController =usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
        this.roteiroController = roteiroController;
    }

    public void iniciarTela(RoteiroPersonalizadoEntity roteiro){

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
                TelaRoteiroSelecionado.this.dispose();
                TelaRoteiros telaRoteiros = new TelaRoteiros(usuarioController, pacoteController, passeioController, roteiroController);
                telaRoteiros.inicarTela(usuarioController.getUserLogged());
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
        logoNew.setSize(100,50);

        header.setOpaque(false);
        header.add(buttonBack);
        header.add(Box.createHorizontalStrut(75)); // Seu strut original
        header.add(logoNew);

// fim da header

        // Título
        JLabel titleRoteiro = new JLabel("<html><b>"+ roteiro.getTitulo()+"</b></html>");
        titleRoteiro.setFont(interFontBold != null ? interFontBold.deriveFont(20f) : new Font("SansSerif", Font.BOLD, 20));
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


        if(roteiro.getDias()!=null && !roteiro.getDias().isEmpty()){

            for(DiaEntity dia : roteiro.getDias()){
                JPanel blocoDiaWrapper = createDayBlock(dia);
                center.add(blocoDiaWrapper);
                center.add(Box.createVerticalStrut(10));
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

        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TelaRoteiroSelecionado.this.dispose();
                TelaAddPasseioDia telaCriarRoteiro3Etapa = new TelaAddPasseioDia(usuarioController, pacoteController, passeioController, roteiroController,dia);
                telaCriarRoteiro3Etapa.iniciarTela();
                dispose();
            }
        });

        JPanel centroPanel = new JPanel();
        centroPanel.setBackground(new Color(0x13A8AD));
        centroPanel.setOpaque(false);
        centroPanel.setLayout(new BoxLayout(centroPanel, BoxLayout.Y_AXIS));

        int alturaMinima = dia.getPasseios().isEmpty() ? 50 : dia.getPasseios().size() * 60;

        centroPanel.setMinimumSize(new Dimension(350, alturaMinima));
        centroPanel.setPreferredSize(new Dimension(350, alturaMinima));
        centroPanel.setMaximumSize(new Dimension(350, Integer.MAX_VALUE));
        centroPanel.setAlignmentX(Component.CENTER_ALIGNMENT);



        for(PasseioEntity passeio : dia.getPasseios()){
            JPanel passeioPanel = criarPasseioDentroDia(passeio);
            centroPanel.add(passeioPanel);
            centroPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Alterado de 0 para 5 pixels
        }

        centroPanel.add(addButton);
        centroPanel.revalidate();
        centroPanel.repaint();

        centroPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Top/Bottom de 5 pixels

        blockDia.add(topoPanel, BorderLayout.NORTH);
        blockDia.add(centroPanel, BorderLayout.CENTER);

        JPanel blocoDiaPanelCentralizado = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        blocoDiaPanelCentralizado.setOpaque(false);
        blocoDiaPanelCentralizado.add(blockDia);

        return blocoDiaPanelCentralizado;
    }

    public JPanel criarPasseioDentroDia(PasseioEntity passeio){

        JPanel passeioPanel = new JPanel();
        passeioPanel.setOpaque(false);
        passeioPanel.setLayout(new BorderLayout());
        passeioPanel.setPreferredSize(new Dimension(350, 50));
        passeioPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel nomePasseio = new JLabel("<html><b>"+passeio.getTitulo()+"</b></html>");
        nomePasseio.setForeground(Color.WHITE);
        nomePasseio.setFont(interFont.deriveFont(18f));
        JLabel horario = new JLabel("<html><b>"+passeio.getHorarios()+"</b></html>");
        horario.setForeground(Color.WHITE);
        horario.setFont(interFont.deriveFont(18f));

        passeioPanel.add(nomePasseio, BorderLayout.WEST);
        passeioPanel.add(horario, BorderLayout.EAST);

        return passeioPanel;
    }

}
