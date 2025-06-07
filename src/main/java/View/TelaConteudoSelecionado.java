package View;

import Controller.PacoteController;
import Controller.PasseioController;
import Controller.UsuarioController;
import Model.Entidades.CategoriaEntity;
import Model.Entidades.FotoEntity;
import Model.Entidades.PacoteTuristicoEntity;
import Model.Entidades.PasseioEntity;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class TelaConteudoSelecionado extends JFrame {

    private UsuarioController usuarioController;
    private PacoteController pacoteController;
    private PasseioController passeioController;
    private Object conteudoSelecionado; // Pode ser PacoteTuristicoEntity ou PasseioEntity

    private Font interFont = null;
    private Font interFontBold = null;
    private CoresProjeto coresProjeto = new CoresProjeto();

    public TelaConteudoSelecionado(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController, Object conteudoSelecionado){
        this.usuarioController = usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
        this.conteudoSelecionado = conteudoSelecionado;
    }

    public void iniciarTela(){

        setTitle("Detalhes do Conteúdo");
        setSize(440, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Carregar as fontes
        interFont = FontLoader.loadFont("/fontsNewDestiny/Inter.ttc", 16f);
        interFontBold = FontLoader.loadFont("/fontsNewDestiny/InterVariable.ttf", 16f);

        BackgroundPanel containerMain = new BackgroundPanel("/photos/backgroundMain.png");
        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        // Header (Botão de voltar e Logo)
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setOpaque(false);

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

        header.add(buttonBack);
        header.add(Box.createHorizontalStrut(75));
        header.add(logoNew);

        // Painel central para o conteúdo (título, categoria, imagem, detalhes)
        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        // Título principal (Nome do Passeio/Pacote)
        JLabel titleLabel = new JLabel();
        titleLabel.setFont(interFontBold.deriveFont(22f));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // ALINHANDO À ESQUERDA

        // Label para a categoria (abaixo do título)
        JLabel categoryLabel = new JLabel();
        categoryLabel.setFont(interFont.deriveFont(16f)); // Fonte e tamanho para categoria
        categoryLabel.setForeground(Color.WHITE); // Cor branca
        categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // ALINHANDO À ESQUERDA

        // Painel genérico que pode conter miniaturas de fotos ou cards de passeios
        JPanel contentGalleryPanel = new JPanel();
        contentGalleryPanel.setOpaque(false);
        contentGalleryPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0)); // Alinha conteúdos à esquerda com espaçamento

        JScrollPane contentGalleryScrollPane = new JScrollPane(contentGalleryPanel);
        contentGalleryScrollPane.setOpaque(false);
        contentGalleryScrollPane.getViewport().setOpaque(false);
        contentGalleryScrollPane.setBorder(BorderFactory.createEmptyBorder());
        contentGalleryScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        contentGalleryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        contentGalleryScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        // Definir um tamanho padrão, que pode ser ajustado na lógica de conteúdo
        contentGalleryScrollPane.setPreferredSize(new Dimension(360, 120));
        contentGalleryScrollPane.setMaximumSize(new Dimension(360, 120));

        // Estilo da barra de rolagem horizontal para a galeria de conteúdo
        JScrollBar horizontalScrollBar = contentGalleryScrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(100, 100, 100, 255);
                this.trackColor = new Color(50, 50, 50, 255);
            }
            @Override
            protected JButton createDecreaseButton(int orientation) { return createZeroButton(); }
            @Override
            protected JButton createIncreaseButton(int orientation) { return createZeroButton(); }
            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(super.getPreferredSize(c).width, 5);
            }
        });


        // Painel para os detalhes, que será rolado (o scrollPane original)
        JPanel detailsPanel = new JPanel();
        detailsPanel.setOpaque(false);
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Lógica para carregar os detalhes e as imagens/cards
        if (conteudoSelecionado instanceof PacoteTuristicoEntity) {
            PacoteTuristicoEntity pacote = (PacoteTuristicoEntity) conteudoSelecionado;
            titleLabel.setText("<html><b>Pacote: " + pacote.getTitulo() + "</b></html>");

            // Exibindo categorias do pacote
            String categoriasPacote = pacote.getCategorias().stream()
                    .map(CategoriaEntity::getNome)
                    .collect(Collectors.joining(", "));
            categoryLabel.setText("Categoria: " + (categoriasPacote.isEmpty() ? "N/A" : categoriasPacote));

            // Para Pacotes, a galeria de conteúdo será os cards dos passeios inclusos
            if (pacote.getPasseios() != null && !pacote.getPasseios().isEmpty()) {
                for (PasseioEntity passeio : pacote.getPasseios()) {
                    JPanel passeioCard = criarPasseioCardPequeno(passeio);
                    contentGalleryPanel.add(passeioCard);
                }
                // Ajusta a altura do scroll pane de galeria para os cards de passeio
                contentGalleryScrollPane.setPreferredSize(new Dimension(360, 170)); // Altura para cards de 150 + espaçamento
                contentGalleryScrollPane.setMaximumSize(new Dimension(360, 170));
            } else {
                // Se não houver passeios no pacote, mostra uma imagem padrão para "pacote sem imagem"
                addImageThumbnail(contentGalleryPanel, "/photos/default_package.jpg", 100, 100);
            }

            addDetailLabel(detailsPanel, "Preço Total:", pacote.getPrecoTotal().doubleValue(), interFont, Color.WHITE);
            // addDetailLabel(detailsPanel, "Período:", "XX/XX/XXXX - YY/YY/YYYY", interFont, Color.WHITE); // Se tiver datas no pacote

            // Removendo a seção "Passeios Inclusos:" textual, pois agora eles aparecem na galeria principal como cards.

        } else if (conteudoSelecionado instanceof PasseioEntity) {
            PasseioEntity passeio = (PasseioEntity) conteudoSelecionado;
            titleLabel.setText("<html><b>Passeio: " + (passeio.getTitulo() != null ? passeio.getTitulo() : "N/A") + "</b></html>");

            // Exibindo categorias do passeio
            String categoriasPasseio = passeio.getCategorias().stream()
                    .map(CategoriaEntity::getNome)
                    .collect(Collectors.joining(", "));
            categoryLabel.setText("Categorias: " + (categoriasPasseio.isEmpty() ? "N/A" : categoriasPasseio));

            // Para Passeios, a galeria de conteúdo são as miniaturas de fotos
            if (passeio.getListaFotos() != null && !passeio.getListaFotos().isEmpty()) {
                for (FotoEntity foto : passeio.getListaFotos()) {
                    addImageThumbnail(contentGalleryPanel, foto.getUrl(), 100, 100); // Miniaturas 100x100
                }
            }
            // Se não houver fotos, adiciona uma imagem padrão
            if (contentGalleryPanel.getComponentCount() == 0) {
                addImageThumbnail(contentGalleryPanel, "/photos/default_passeio.jpg", 100, 100);
            }
            // Ajusta a altura do scroll pane de galeria para as miniaturas de foto
            contentGalleryScrollPane.setPreferredSize(new Dimension(360, 120));
            contentGalleryScrollPane.setMaximumSize(new Dimension(360, 120));


            addDetailLabel(detailsPanel, "Descrição:", passeio.getDescricao(), interFont, Color.WHITE);
            addDetailLabel(detailsPanel, "Duração:", passeio.getDuracao(), interFont, Color.WHITE);
            addDetailLabel(detailsPanel, "Localização:", passeio.getLocalizacao(), interFont, Color.WHITE);
            addDetailLabel(detailsPanel, "Preço Ingresso:", passeio.getPreco().doubleValue(), interFont, Color.WHITE);
            addDetailLabel(detailsPanel, "Horário:", passeio.getHorarios(), interFont, Color.WHITE);

        } else {
            titleLabel.setText("<html><b>Conteúdo Não Encontrado</b></html>");
            categoryLabel.setText(""); // Sem categoria para conteúdo não encontrado
            addImageThumbnail(contentGalleryPanel, "/photos/no_content_found.jpg", 100, 100); // Imagem padrão
            addDetailLabel(detailsPanel, "Detalhes:", "Não foi possível carregar os detalhes do conteúdo selecionado.", interFont, Color.WHITE);
        }

        // JScrollPane para os detalhes (o original)
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT); // ALINHANDO À ESQUERDA
        scrollPane.setMaximumSize(new Dimension(360, Integer.MAX_VALUE));

        // Estilo da barra de rolagem vertical (para os detalhes)
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(100, 100, 100, 255);
                this.trackColor = new Color(50, 50, 50, 255);
            }
            @Override
            protected JButton createDecreaseButton(int orientation) { return createZeroButton(); }
            @Override
            protected JButton createIncreaseButton(int orientation) { return createZeroButton(); }
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

        // Adicionar componentes ao painel central
        center.add(Box.createVerticalStrut(30));
        center.add(titleLabel);
        center.add(Box.createVerticalStrut(5)); // Pequeno espaço entre título e categoria
        center.add(categoryLabel); // Adiciona a categoria
        center.add(Box.createVerticalStrut(20));
        center.add(contentGalleryScrollPane); // AGORA É O SCROLLPANE DE CONTEÚDO GENÉRICO
        center.add(Box.createVerticalStrut(20));
        center.add(scrollPane); // SCROLLPANE COM DETALHES
        center.add(Box.createVerticalStrut(20));

        containerMain.add(header, BorderLayout.NORTH);
        containerMain.add(center, BorderLayout.CENTER);

        setContentPane(containerMain);
        setVisible(true);
    }

    // --- Métodos Auxiliares ---

    // Método auxiliar para adicionar uma miniatura de imagem a um painel
    private void addImageThumbnail(JPanel parentPanel, String imagePath, int width, int height) {
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(width, height));
        imageLabel.setMinimumSize(new Dimension(width, height));
        imageLabel.setMaximumSize(new Dimension(width, height));
        imageLabel.setBorder(BorderFactory.createLineBorder(coresProjeto.corPrincipalAzul, 1)); // Borda mais fina para miniaturas

        try {
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                Image img = originalIcon.getImage();
                Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImg));
            } else {
                System.err.println("Miniatura de imagem não encontrada: " + imagePath);
                ImageIcon placeholderIcon = new ImageIcon(createPlaceholderImage(width, height, "N/A"));
                imageLabel.setIcon(placeholderIcon);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar miniatura: " + imagePath);
            ImageIcon placeholderIcon = new ImageIcon(createPlaceholderImage(width, height, "ERRO"));
            imageLabel.setIcon(placeholderIcon);
        }
        parentPanel.add(imageLabel);
    }

    private Image createPlaceholderImage(int width, int height, String text) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.setFont(interFont.deriveFont(Font.BOLD, 14f));
        FontMetrics fm = g.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        int y = (height - fm.getHeight()) / 2 + fm.getAscent();
        g.drawString(text, x, y);
        g.dispose();
        return img;
    }

    // Adiciona uma linha de detalhe (Título: Valor)
    private void addDetailLabel(JPanel parentPanel, String title, String value, Font font, Color color) {
        JPanel linePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        linePanel.setOpaque(false);

        JLabel titlePart = new JLabel("<html><b>" + title + "</b></html>");
        titlePart.setFont(font.deriveFont(15f));
        titlePart.setForeground(color);
        linePanel.add(titlePart);

        // Trata o valor nulo ou vazio aqui para evitar NullPointerException
        String displayValue = (value != null && !value.isEmpty()) ? value : "N/A";
        String formattedValue = displayValue.replaceAll("\n", "<br>");
        JLabel valuePart = new JLabel(" " + formattedValue);
        valuePart.setFont(font.deriveFont(15f));
        valuePart.setForeground(color);
        linePanel.add(valuePart);

        parentPanel.add(linePanel);
        parentPanel.add(Box.createVerticalStrut(5));
    }

    // Sobrecarga para números (preço)
    private void addDetailLabel(JPanel parentPanel, String title, double value, Font font, Color color) {
        JPanel linePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        linePanel.setOpaque(false);

        JLabel titlePart = new JLabel("<html><b>" + title + "</b></html>");
        titlePart.setFont(font.deriveFont(15f));
        titlePart.setForeground(color);
        linePanel.add(titlePart);

        JLabel valuePart = new JLabel(String.format(" R$ %.2f", value));
        valuePart.setFont(font.deriveFont(15f));
        valuePart.setForeground(color);
        linePanel.add(valuePart);

        parentPanel.add(linePanel);
        parentPanel.add(Box.createVerticalStrut(5));
    }

    // Adiciona um título de seção (e.g., "Passeios Inclusos:")
    private void addSectionTitle(JPanel parentPanel, String title, Font font, Color color) {
        parentPanel.add(Box.createVerticalStrut(15));
        JLabel label = new JLabel("<html><b>" + title + "</b></html>");
        label.setFont(font.deriveFont(16f));
        label.setForeground(color);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        parentPanel.add(label);
        parentPanel.add(Box.createVerticalStrut(5));
    }

    // Adiciona um item de lista com marcador (e.g., "- Descrição do passeio")
    private void addBulletPointDetail(JPanel parentPanel, String text, Font font, Color color) {
        // Esta função não é mais utilizada na estrutura atual para Pacotes,
        // mas pode ser mantida para outros propósitos ou para uma lista textual futura.
        JLabel label = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;- " + text.replaceAll("\n", "<br>") + "</html>");
        label.setFont(font.deriveFont(14f));
        label.setForeground(color);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        parentPanel.add(label);
        parentPanel.add(Box.createVerticalStrut(3));
    }

    // Novo método para criar um card de passeio pequeno, similar ao da TelaVisualizacao
    public JPanel criarPasseioCardPequeno(PasseioEntity passeio) {
        JPanel cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(150, 150)); // Tamanho menor para o card
        cardPanel.setLayout(new OverlayLayout(cardPanel));
        cardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Adiciona cursor de mão
        cardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ao clicar no card, abre a tela de detalhes do passeio
                TelaConteudoSelecionado telaConteudoSelecionado = new TelaConteudoSelecionado(usuarioController, pacoteController, passeioController, passeio);
                telaConteudoSelecionado.iniciarTela();
                dispose(); // Fecha a tela atual de detalhes do pacote
            }
        });


        // Imagem do card
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(150, 150));
        imageLabel.setMinimumSize(new Dimension(150, 150));
        imageLabel.setMaximumSize(new Dimension(150, 150));

        String imagePath = "/photos/default_passeio.jpg"; // Imagem padrão
        if (passeio.getListaFotos() != null && !passeio.getListaFotos().isEmpty()) {
            // Usa a primeira foto do passeio, se existir
            imagePath = passeio.getListaFotos().get(0).getUrl();
        }

        try {
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                Image img = originalIcon.getImage();
                Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImg));
            } else {
                System.err.println("Imagem do card de passeio não encontrada: " + imagePath);
                imageLabel.setIcon(new ImageIcon(createPlaceholderImage(150, 150, "N/A")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar imagem do card de passeio: " + imagePath);
            imageLabel.setIcon(new ImageIcon(createPlaceholderImage(150, 150, "ERRO")));
        }

        imageLabel.setAlignmentX(0.5f);
        imageLabel.setAlignmentY(0.5f);

        // Painel para o título sobreposto
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 0, 0, 150)); // Fundo semi-transparente
        titlePanel.setPreferredSize(new Dimension(150, 40)); // Altura menor
        titlePanel.setMaximumSize(new Dimension(150, 40));
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10)); // Ajuste de espaçamento

        JLabel titlePasseio = new JLabel(passeio.getTitulo() != null ? passeio.getTitulo() : "N/A");
        titlePasseio.setForeground(Color.WHITE);
        titlePasseio.setFont(interFont.deriveFont(12f)); // Fonte menor
        titlePanel.add(titlePasseio);

        titlePanel.setAlignmentX(0.5f);
        titlePanel.setAlignmentY(1.0f); // Alinha na parte inferior

        cardPanel.add(titlePanel);
        cardPanel.add(imageLabel);

        return cardPanel;
    }
}