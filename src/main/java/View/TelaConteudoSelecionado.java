package View;
import Controller.PacoteController;
import Controller.PasseioController;
import Controller.UsuarioController;
import Model.Entidades.PacoteTuristicoEntity;
import Model.Entidades.MockRoteiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;

public class TelaConteudoSelecionado extends JFrame {

    private UsuarioController usuarioController;
    private PacoteController pacoteController;
    private PasseioController passeioController;
    private Object conteudoSelecionado; // Pode ser PacoteTuristicoEntity ou MockRoteiro

    private Font interFont = null;
    private Font interFontBold = null;

    public TelaConteudoSelecionado(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController, Object conteudoSelecionado){
        this.usuarioController =usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
        this.conteudoSelecionado = conteudoSelecionado;
    }

    public void iniciarTela(){

        // Configurações padrão da tela
        setTitle("Detalhes do Conteúdo");
        setSize(440, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Carregar a fonte inter
        try {
            InputStream fontStream = getClass().getResourceAsStream("/fontsNewDestiny/Inter.ttc");
            if (fontStream != null) {
                interFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(16f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(interFont);
            } else {
                System.err.println("Fonte Inter.ttc não encontrada!");
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
                System.err.println("Fonte InterVariable.ttf não encontrada!");
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

        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon imgButtonBack = new ImageIcon(getClass().getResource("/photos/arrowBack.png"));

        Image img = imgButtonBack.getImage();
        Image imgRedimensionada = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon imgRedimensionadaIcon = new ImageIcon(imgRedimensionada);

        JButton buttonBack = new JButton(imgRedimensionadaIcon);

        buttonBack.setBorderPainted(false);
        buttonBack.setContentAreaFilled(false);
        buttonBack.setFocusPainted(false);
        buttonBack.setOpaque(false);

        buttonBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // Voltar para a tela anterior (TelaRoteiros)
                TelaRoteiros telaRoteiros = new TelaRoteiros(usuarioController, pacoteController, passeioController);
                telaRoteiros.inicarTela();
                dispose();
            }
        });

        ImageIcon imgLogo = new ImageIcon(getClass().getResource("/photos/logo.png"));
        JLabel logoNew = new JLabel(imgLogo);
        logoNew.setSize(100,50);

        header.setOpaque(false);
        header.add(buttonBack);
        header.add(Box.createHorizontalStrut(75)); // Ajuste o espaçamento conforme necessário
        header.add(logoNew);

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        // Conteúdo dinâmico
        JLabel titleLabel = new JLabel();
        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setOpaque(false);
        detailsArea.setForeground(Color.WHITE);
        detailsArea.setFont(interFont.deriveFont(14f));
        detailsArea.setWrapStyleWord(true);
        detailsArea.setLineWrap(true);
        detailsArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (conteudoSelecionado instanceof PacoteTuristicoEntity) {
            PacoteTuristicoEntity pacote = (PacoteTuristicoEntity) conteudoSelecionado;
            titleLabel.setText("<html><b>Pacote: " + pacote.getTitulo() + "</b></html>");

            StringBuilder pacoteDetails = new StringBuilder();
            pacoteDetails.append(String.format("Preço Total: R$ %.2f\n\n", pacote.getPrecoTotal()));

            if (pacote.getPasseios() != null && !pacote.getPasseios().isEmpty()) {
                pacoteDetails.append("Passeios Inclusos:\n");
                for (int i = 0; i < pacote.getPasseios().size(); i++) {
                    pacoteDetails.append(String.format("- %s: %s\n", pacote.getPasseios().get(i).getTitulo(), pacote.getPasseios().get(i).getDescricao()));
                }
            }
            if (pacote.getCategorias() != null && !pacote.getCategorias().isEmpty()) {
                pacoteDetails.append("\nCategorias:\n");
                for (int i = 0; i < pacote.getCategorias().size(); i++) {
                    pacoteDetails.append(String.format("- %s\n", pacote.getCategorias().get(i).getNome()));
                }
            }
            detailsArea.setText(pacoteDetails.toString());

        } else if (conteudoSelecionado instanceof MockRoteiro) {
            MockRoteiro roteiro = (MockRoteiro) conteudoSelecionado;
            titleLabel.setText("<html><b>Roteiro: " + roteiro.getNome() + "</b></html>");

            StringBuilder roteiroDetails = new StringBuilder();
            roteiroDetails.append("Data Início: " + roteiro.getDataInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n\n");
            roteiroDetails.append("Descrição: " + roteiro.getDescricao() + "\n\n");

            if (roteiro.getPasseiosInclusos() != null && !roteiro.getPasseiosInclusos().isEmpty()) {
                roteiroDetails.append("Passeios Inclusos:\n");
                for (String passeio : roteiro.getPasseiosInclusos()) {
                    roteiroDetails.append("- " + passeio + "\n");
                }
            }
            detailsArea.setText(roteiroDetails.toString());

        } else {
            titleLabel.setText("<html><b>Conteúdo Não Encontrado</b></html>");
            detailsArea.setText("Não foi possível carregar os detalhes do conteúdo selecionado.");
        }

        titleLabel.setFont(interFontBold.deriveFont(20f));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(Box.createVerticalStrut(30));
        center.add(titleLabel);
        center.add(Box.createVerticalStrut(20));
        center.add(detailsArea);
        center.add(Box.createVerticalStrut(20));

        // Adicionar um JScrollPane para a área de detalhes, se necessário
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(new Dimension(380, 500)); // Tamanho máximo para a área de detalhes
        center.add(scrollPane);

        containerMain.add(header,BorderLayout.NORTH);
        containerMain.add(center, BorderLayout.CENTER);

        setContentPane(containerMain);
        setVisible(true);
    }
} 