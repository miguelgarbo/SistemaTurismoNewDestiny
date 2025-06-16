package View;

import Controller.PacoteController;
import Controller.PasseioController;
import Controller.RoteiroController;
import Controller.UsuarioController;
import Model.Entidades.CategoriaEntity;
import Model.Entidades.PacoteTuristicoEntity;
import Model.Entidades.PasseioEntity;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class TelaVisualizacao extends JFrame {

    private UsuarioController usuarioController;
    private PasseioController passeioController;
    private PacoteController pacoteController;
    private RoteiroController roteiroController;
    private Font interFont = null;
    private Font interFontBold = null;
    private CoresProjeto coresProjeto = new CoresProjeto();

    public TelaVisualizacao(UsuarioController usuarioController, PasseioController passeioController, PacoteController pacoteController, RoteiroController roteiroController) {
        this.usuarioController = usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
        this.roteiroController =roteiroController;
    }

    public void iniciarTela() {

        setTitle("Tela Visualização de Pacotes e Passeios");
        setSize(440, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        interFont = FontLoader.loadFont("/fontsNewDestiny/Inter.ttc", 16f);
        interFontBold = FontLoader.loadFont("/fontsNewDestiny/InterVariable.ttf", 16f);

        BackgroundPanel containerMain = new BackgroundPanel("/photos/backgroundMain.png");
        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(15, 0, 30, 0));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setOpaque(false);

        ImageIcon imgIconND = null;
        URL logoUrl = getClass().getResource("/photos/logo.png");
        if (logoUrl != null) {
            imgIconND = new ImageIcon(logoUrl);
        } else {
            System.err.println("Imagem do logo New Destiny não encontrada: /photos/logo.png");
        }
        JLabel iconNewDestiny = new JLabel();
        iconNewDestiny.setIcon(imgIconND);
        iconNewDestiny.setSize(100, 50);


        JTextArea searchBar = new JTextArea("Pesquise aqui", 1, 15);
        searchBar.setLineWrap(false); // impede quebra de linha
        searchBar.setWrapStyleWord(false);
        searchBar.setPreferredSize(new Dimension(220, 27));
        searchBar.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none");

        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                    String termo = searchBar.getText().trim();
                    if (!termo.isEmpty()) {
                        buscarPasseios(termo);
                    }

                }
            }
        });


        ImageIcon imgButton = null;
        URL menuIconUrl = getClass().getResource("/photos/menu.png");
        if (menuIconUrl != null) {
            Image img = new ImageIcon(menuIconUrl).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            imgButton = new ImageIcon(img);
        } else {
            System.err.println("Imagem do botão de menu não encontrada: /photos/menu.png");
        }

        JButton miniMenuButton = new JButton();
        miniMenuButton.setIcon(imgButton);

        miniMenuButton.setBorderPainted(false);
        miniMenuButton.setContentAreaFilled(false);
        miniMenuButton.setFocusPainted(false);
        miniMenuButton.setOpaque(false);

        if(usuarioController.getUserLogged()==null){
            miniMenuButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    ModalMenu modalMenu = new ModalMenu(usuarioController, pacoteController, passeioController, roteiroController);
                    modalMenu.iniciarModal(TelaVisualizacao.this);
                }
            });
        }else{

            miniMenuButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    ModalMenu modalMenu = new ModalMenu(usuarioController, pacoteController, passeioController, roteiroController);
                    modalMenu.iniciarModal(TelaVisualizacao.this, usuarioController.getUserLogged());
                }
            });

        }

        header.add(iconNewDestiny);
        header.add(Box.createHorizontalStrut(10));
        header.add(searchBar);
        header.add(miniMenuButton);

        containerMain.add(header, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        List<CategoriaEntity> categoriasBD = passeioController.categoriaService().buscarTodos();

        String[] nomesCategoriasSemTodos = categoriasBD.stream()
                .map(CategoriaEntity::getNome)
                .toArray(String[]::new);

        String[] nomesCategoriasComTodos = new String[nomesCategoriasSemTodos.length + 1];
        nomesCategoriasComTodos[0] = "Todos";

        System.arraycopy(nomesCategoriasSemTodos, 0, nomesCategoriasComTodos, 1, nomesCategoriasSemTodos.length);


        JComboBox<String> filter = new JComboBox<>(nomesCategoriasComTodos);
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
            }
        });

        JPanel filterRow = new JPanel();
        filterRow.setLayout(new BoxLayout(filterRow, BoxLayout.X_AXIS));
        filterRow.setOpaque(false);

        filterRow.add(filter);
        filterRow.add(Box.createRigidArea(new Dimension(10, 0)));
        filterRow.add(buttonFilterApply);
        filterRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel text1 = new JLabel("<html><b>Pacotes Disponiveis !</b></html>");
        text1.setFont(interFontBold.deriveFont(20f));
        text1.setForeground(Color.WHITE);

        center.add(Box.createVerticalStrut(15));
        center.add(filterRow);

        center.add(Box.createVerticalStrut(15));
        center.add(text1);
        containerMain.add(center, BorderLayout.CENTER);

        JPanel pacotesRow = new JPanel();
        pacotesRow.setLayout(new FlowLayout());
        pacotesRow.setOpaque(false);


        for(PacoteTuristicoEntity pacoteTuristico : pacoteController.buscarTodos()){

            JPanel pacoteCard = criarPacoteCard(pacoteTuristico);
            pacotesRow.add(pacoteCard);
            pacotesRow.add(Box.createRigidArea(new Dimension(15, 0)));
        }


        JScrollPane scrollHorizontal = new JScrollPane(pacotesRow);
        scrollHorizontal.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollHorizontal.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        // Ajustado a altura para permitir a visualização e rolagem
        scrollHorizontal.setPreferredSize(new Dimension(370, 210));
        scrollHorizontal.setMaximumSize(new Dimension(Integer.MAX_VALUE, 230));

        scrollHorizontal.setBorder(null);
        scrollHorizontal.setOpaque(false);
        scrollHorizontal.getViewport().setOpaque(false);

        // Personaliza a barra de rolagem horizontal
        JScrollBar horizontalScrollBar1 = scrollHorizontal.getHorizontalScrollBar();
        horizontalScrollBar1.setUI(new BasicScrollBarUI() {
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
                return new Dimension(super.getPreferredSize(c).width, 5); // Define a altura para 5 pixels
            }
        });

        center.add(scrollHorizontal);

        JLabel text2 = new JLabel("<html><b>Passeios Disponiveis !</b></html>");
        text2.setFont(interFontBold.deriveFont(20f));
        text2.setForeground(Color.WHITE);
        center.add(Box.createVerticalStrut(5));
        center.add(text2);

        JPanel passeioRow = new JPanel(new FlowLayout());
        passeioRow.setOpaque(false);

        for(PasseioEntity passeio: passeioController.listaPasseiosCadastrados()){
            JPanel passeioCard = criarPasseioCard(passeio);
            passeioRow.add(passeioCard);
            passeioRow.add(Box.createRigidArea(new Dimension(15, 0)));
        }

        JScrollPane scrollHorizontal2 = new JScrollPane(passeioRow);
        scrollHorizontal2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollHorizontal2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollHorizontal2.setPreferredSize(new Dimension(370, 210));
        scrollHorizontal2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 230));

        scrollHorizontal2.setBorder(null);
        scrollHorizontal2.setOpaque(false);
        scrollHorizontal2.getViewport().setOpaque(false);

        JScrollBar horizontalScrollBar2 = scrollHorizontal2.getHorizontalScrollBar();
        horizontalScrollBar2.setUI(new BasicScrollBarUI() {
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
                return new Dimension(super.getPreferredSize(c).width, 5); // Define a altura para 5 pixels
            }
        });
        center.add(scrollHorizontal2);

        JScrollPane mainScroll = new JScrollPane(containerMain);
        mainScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainScroll.getViewport().setOpaque(false);
        mainScroll.setOpaque(false);
        mainScroll.setBorder(null);

        JScrollBar verticalScrollBar = mainScroll.getVerticalScrollBar();
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

        scrollHorizontal.setWheelScrollingEnabled(true);
        scrollHorizontal2.setWheelScrollingEnabled(true);
        mainScroll.setWheelScrollingEnabled(true);

        setContentPane(mainScroll);
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

            container.add(Box.createVerticalGlue());
            container.add(titlePanel);

            cardPanel.add(container);
            cardPanel.add(imageLabel);

        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + e.getMessage());
            cardPanel.add(new JLabel("Imagem indisponível"));
        }

        // Clique no card
        cardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TelaConteudoSelecionado tela = new TelaConteudoSelecionado(
                        usuarioController, passeioController, pacoteController, passeio, roteiroController);
                tela.iniciarTela();
                dispose();

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



    public JPanel criarPacoteCard(PacoteTuristicoEntity pacoteTuristico) {
        JPanel cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(300, 200));
        cardPanel.setLayout(new OverlayLayout(cardPanel));

        // Lista com as imagens a exibir
        List<ImageIcon> imagensPasseios = new ArrayList<>();

        try {
            for (PasseioEntity passeio : pacoteTuristico.getPasseios()) {
                if (!passeio.getListaFotos().isEmpty()) {
                    String caminhoImagem = passeio.getListaFotos().get(0).getUrl();
                    URL url = new URL(caminhoImagem);
                    ImageIcon imgIcon = new ImageIcon(url);
                    Image imagem = imgIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                    imagensPasseios.add(new ImageIcon(imagem));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagens: " + e.getMessage());
        }

        JLabel imageLabel = new JLabel();
        if (!imagensPasseios.isEmpty()) {
            imageLabel.setIcon(imagensPasseios.get(0));
        } else {
            imageLabel.setText("Imagem indisponível");
        }
        imageLabel.setPreferredSize(new Dimension(300, 200));
        imageLabel.setAlignmentX(0.0f);
        imageLabel.setAlignmentY(0.0f);

        // Painel com título
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(true);
        titlePanel.setBackground(new Color(0, 0, 0, 150));
        titlePanel.setPreferredSize(new Dimension(300, 50));
        titlePanel.setMaximumSize(new Dimension(300, 50));
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));

        JLabel titleLabel = new JLabel(pacoteTuristico.getTitulo());
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(interFont.deriveFont(16f));
        titlePanel.add(titleLabel);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);
        container.setPreferredSize(new Dimension(300, 200));
        container.setMaximumSize(new Dimension(300, 200));
        container.setAlignmentX(0.0f);
        container.setAlignmentY(0.0f);
        container.add(Box.createVerticalGlue());
        container.add(titlePanel);

        cardPanel.add(container);
        cardPanel.add(imageLabel);

        // Timer para alternar imagens a cada 2,5 segundos
        if (imagensPasseios.size() > 1) {
            final int[] index = {0};
            Timer timer = new Timer(2500, e -> {
                index[0] = (index[0] + 1) % imagensPasseios.size();
                imageLabel.setIcon(imagensPasseios.get(index[0]));
            });
            timer.start();
        }

        cardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                TelaConteudoSelecionado telaConteudoSelecionado = new TelaConteudoSelecionado(
                        usuarioController, passeioController, pacoteController, pacoteTuristico, roteiroController);
                telaConteudoSelecionado.iniciarTela();
                dispose();
            }
        });

        cardPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(coresProjeto.corPrincipalAzul, 1, true),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                )
        );

        return cardPanel;
    }

    public void buscarPasseios(String termo) {
        List<PasseioEntity> resultados = passeioController.passeioService().buscarPorTitulo(termo);

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum passeio encontrado com esse nome.");
            return;
        }

        // Pega o primeiro resultado e abre a tela de conteúdo
        PasseioEntity passeioEncontrado = resultados.get(0);
        TelaConteudoSelecionado telaConteudo = new TelaConteudoSelecionado(usuarioController,passeioController,pacoteController,passeioEncontrado, roteiroController); // ou passe o que sua tela precisa
        telaConteudo.iniciarTela();
    }



}