package View;

import Controller.PacoteController;
import Controller.PasseioController;
import Controller.UsuarioController;
import Model.Entidades.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class TelaConteudoSelecionado extends JFrame {

    private UsuarioController usuarioController;
    private PasseioController passeioController;
    private PacoteController pacoteController;
    private Object conteudoSelecionado;
    private Font interFont = null;
    private Font interFontBold = null;

    public TelaConteudoSelecionado(UsuarioController usuarioController, PasseioController passeioController, PacoteController pacoteController, Object conteudoSelecionado) {
        this.usuarioController = usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
        this.conteudoSelecionado = conteudoSelecionado;
    }

    public void iniciarTela() {

        setTitle("Conteúdo Selecionado");
        setSize(440, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        interFont = FontLoader.loadFont("/fontsNewDestiny/Inter.ttc", 16f);
        interFontBold = FontLoader.loadFont("/fontsNewDestiny/InterVariable.ttf", 16f);

        BackgroundPanel containerMain = new BackgroundPanel("/photos/backgroundMain.png");
        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(20, 6, 20, 2));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setOpaque(false);

        // Botão voltar
        ImageIcon imgButtonBack = null;
        URL arrowBackUrl = getClass().getResource("/photos/arrowBack.png");
        if (arrowBackUrl != null) {
            Image img = new ImageIcon(arrowBackUrl).getImage();
            Image imgRedimensionada = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imgButtonBack = new ImageIcon(imgRedimensionada);
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
                TelaVisualizacao telaVisualizacao = new TelaVisualizacao(usuarioController, passeioController, pacoteController);
                telaVisualizacao.iniciarTela();
            }
        });

        // Logo
        ImageIcon imgLogo = null;
        URL logoUrl = getClass().getResource("/photos/logo.png");
        if (logoUrl != null) {
            imgLogo = new ImageIcon(logoUrl);
        }
        JLabel logoNew = new JLabel(imgLogo);
        logoNew.setSize(100, 50);

        header.add(buttonBack);
        header.add(Box.createHorizontalStrut(75));
        header.add(logoNew);
        containerMain.add(header, BorderLayout.NORTH);

        // Painel central com título e detalhes
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setOpaque(false);

        JLabel titleLabel = new JLabel();
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(interFontBold.deriveFont(18f));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ** Retângulo abaixo do título **
        JPanel retangulo = new JPanel();
        retangulo.setPreferredSize(new Dimension(360, 160));
        retangulo.setMaximumSize(new Dimension(260, 160)); // Para garantir o tamanho fixo no BoxLayout
        retangulo.setBackground(new Color(100, 100, 255, 180)); // Exemplo: azul semitransparente
        retangulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setOpaque(false);
        detailsArea.setForeground(Color.WHITE);
        detailsArea.setFont(interFont.deriveFont(14f));
        detailsArea.setWrapStyleWord(true);
        detailsArea.setLineWrap(true);
        detailsArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        JScrollPane scrollDetails = new JScrollPane(detailsArea);
        scrollDetails.setPreferredSize(new Dimension(360, 200));
        scrollDetails.setOpaque(false);
        scrollDetails.getViewport().setOpaque(false);
        scrollDetails.setBorder(null);
        scrollDetails.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDetails.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        center.add(Box.createVerticalStrut(10));
        center.add(titleLabel);
        center.add(Box.createVerticalStrut(10)); // Espaço entre título e retângulo
        center.add(retangulo);
        center.add(Box.createVerticalStrut(10));
        center.add(scrollDetails);

        if (conteudoSelecionado instanceof PacoteTuristicoEntity pacote) {
            titleLabel.setText("<html><b>Pacote: " + pacote.getTitulo() + "</b></html>");
            StringBuilder pacoteDetails = new StringBuilder();
            pacoteDetails.append(String.format("Preço Total: R$ %.2f\n\n", pacote.getPrecoTotal()));
            if (pacote.getCategorias() != null && !pacote.getCategorias().isEmpty()) {
                pacoteDetails.append("Categorias:\n");
                for (CategoriaEntity categoria : pacote.getCategorias()) {
                    pacoteDetails.append("- ").append(categoria.getNome()).append("\n");
                }
            }

            if (pacote.getPasseios() != null && !pacote.getPasseios().isEmpty()) {
                pacoteDetails.append("Passeios Inclusos:\n");
                for (PasseioEntity passeio : pacote.getPasseios()) {
                    pacoteDetails.append("- ").append(passeio.getTitulo()).append("\n");
                    pacoteDetails.append("- ").append(passeio.getDescricao()).append("\n");
                    pacoteDetails.append("--------------------------------------------------");

                }
            }
            detailsArea.setText(pacoteDetails.toString());

        } else if (conteudoSelecionado instanceof PasseioEntity passeio) {
            titleLabel.setText("<html><b>Passeio: " + passeio.getTitulo() + "</b></html>");
            StringBuilder passeioDetails = new StringBuilder();
            passeioDetails.append("Descrição: ").append(passeio.getDescricao()).append("\n\n");
            passeioDetails.append("Duração: ").append(passeio.getDuracao()).append("\n\n");
            passeioDetails.append("Preço Ingresso: R$ ").append(passeio.getPreco()).append("\n\n");
            passeioDetails.append("Localização: ").append(passeio.getLocalizacao()).append("\n\n");
            passeioDetails.append("Horários: ").append(passeio.getHorarios()).append("\n\n");

            if (passeio.getCategorias() != null && !passeio.getCategorias().isEmpty()) {
                passeioDetails.append("Categorias:\n");
                for (CategoriaEntity categoria : passeio.getCategorias()) {
                    passeioDetails.append("- ").append(categoria.getNome()).append("\n");
                }
            }
            detailsArea.setText(passeioDetails.toString());
        } else {
            titleLabel.setText("<html><b>Conteúdo Não Encontrado</b></html>");
            detailsArea.setText("Não foi possível carregar os detalhes do conteúdo selecionado.");
        }

        containerMain.add(center, BorderLayout.CENTER);
        setContentPane(containerMain);
        setVisible(true);
    }
}
