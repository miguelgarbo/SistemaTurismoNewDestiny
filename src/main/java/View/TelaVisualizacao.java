package View;

import Controller.UsuarioController;
import Model.Entidades.UsuarioEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;


public class TelaVisualizacao extends JFrame {

    private UsuarioController usuarioController;

    public TelaVisualizacao(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public void iniciarTela() {

        setTitle("Tela Visualização de Pacotes e Passeios");
        setSize(440, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Carregar a fonte inter
        Font interFont = null;
        Font interFontBold = null;

        try {
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

        JPanel containerMain = new JPanel() {
            Image img = new ImageIcon(getClass().getResource("/photos/backgroundMain.png")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(30, 6, 30, 2));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setOpaque(false);

        ImageIcon imgIconND = new ImageIcon(getClass().getResource("/photos/logo.png"));
        JLabel iconNewDestiny = new JLabel();
        iconNewDestiny.setIcon(imgIconND);
        iconNewDestiny.setSize(100, 50);

        JTextArea searchBar = new JTextArea("Pesquise Aqui ", 1, 15);
        searchBar.setPreferredSize(new Dimension(220, 27));

        ImageIcon imgButton = new ImageIcon(getClass().getResource("/photos/menu.png"));

        Image img = imgButton.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);

        JButton miniMenuButton = new JButton();
        miniMenuButton.setIcon(new ImageIcon(img));

        miniMenuButton.setBorderPainted(false);
        miniMenuButton.setContentAreaFilled(false);
        miniMenuButton.setFocusPainted(false);
        miniMenuButton.setOpaque(false);

        miniMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                ModalMenu modalMenu = new ModalMenu(usuarioController);
                modalMenu.iniciarModal(TelaVisualizacao.this);
            }
        });


        header.add(iconNewDestiny);
        header.add(Box.createHorizontalStrut(10));
        header.add(searchBar);
        header.add(miniMenuButton);

        containerMain.add(header, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        String[] categorias = {"Todos", "Aventura", "Romântico", "Cultural"};
        JComboBox<String> filter = new JComboBox<>(categorias);
        filter.setMaximumSize(new Dimension(130, 30));
        filter.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton buttonFilterApply = new JButton("Aplicar Filtro");
        buttonFilterApply.setMaximumSize(new Dimension(120, 30));
        buttonFilterApply.setForeground(new Color(0x208482));
        buttonFilterApply.setBackground(Color.WHITE);

        buttonFilterApply.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // aplicar filtro
            }
        });


        JPanel filterRow = new JPanel();
        filterRow.setLayout(new BoxLayout(filterRow, BoxLayout.X_AXIS));
        filterRow.setOpaque(false);

        filterRow.add(filter);
        filterRow.add(Box.createRigidArea(new Dimension(10, 0)));
        filterRow.add(buttonFilterApply);
        filterRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel text1 = new JLabel("Pacotes Disponiveis ! ");
        text1.setFont(interFontBold.deriveFont(20f));
        text1.setForeground(Color.WHITE);

        center.add(Box.createVerticalStrut(10));
        center.add(filterRow);

        center.add(Box.createVerticalStrut(15));
        center.add(text1);
        containerMain.add(center, BorderLayout.CENTER);

        //pacotes section
        JPanel pacotesRow = new JPanel();
        pacotesRow.setLayout(new FlowLayout());
        pacotesRow.setOpaque(false);

        for (int i = 1; i <= 6; i++) {
            JButton pacoteBtn = new JButton("Pacote " + i);
            pacoteBtn.setPreferredSize(new Dimension(180, 190));
            pacotesRow.add(pacoteBtn);
            pacotesRow.add(Box.createRigidArea(new Dimension(15, 0)));
        }

        JScrollPane scrollHorizontal = new JScrollPane(pacotesRow);
        scrollHorizontal.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollHorizontal.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollHorizontal.setPreferredSize(new Dimension(300, 0));
        scrollHorizontal.setBorder(null);
        scrollHorizontal.setOpaque(false);
        scrollHorizontal.getViewport().setOpaque(false);

        center.add(scrollHorizontal);

        //passeio section

        JLabel text2 = new JLabel("Passeio Disponiveis ! ");
        text2.setFont(interFontBold.deriveFont(20f));
        text2.setForeground(Color.WHITE);
        center.add(Box.createVerticalStrut(20));
        center.add(text2);

        JPanel passeioRow = new JPanel(new FlowLayout());
        passeioRow.setOpaque(false);

        for (int i = 1; i <= 6; i++) {
            JButton passeioBtn = new JButton("Passeio " + i);
            passeioBtn.setPreferredSize(new Dimension(180, 190));
            passeioRow.add(passeioBtn);
            passeioRow.add(Box.createRigidArea(new Dimension(15, 0)));
        }

        JScrollPane scrollHorizontal2 = new JScrollPane(passeioRow);
        scrollHorizontal2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollHorizontal2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollHorizontal2.setPreferredSize(new Dimension(300, 5));
        scrollHorizontal2.setBorder(null);
        scrollHorizontal2.setOpaque(false);
        scrollHorizontal2.getViewport().setOpaque(false);
        center.add(scrollHorizontal2);

        new JScrollPane(containerMain).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        add(containerMain);
        setContentPane(containerMain);
        setVisible(true);

    }
}
