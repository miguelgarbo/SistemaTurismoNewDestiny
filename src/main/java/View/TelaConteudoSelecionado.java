package View;

import Controller.PacoteController;
import Controller.PasseioController;
import Controller.RoteiroController;
import Controller.UsuarioController;
import Model.Entidades.CategoriaEntity;
import Model.Entidades.FotoEntity;
import Model.Entidades.PacoteTuristicoEntity;
import Model.Entidades.PasseioEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class TelaConteudoSelecionado extends JFrame{

    private Font interFont;
    private UsuarioController usuarioController;
    private PacoteController pacoteController;
    private PasseioController passeioController;
    private RoteiroController roteiroController;
    private Object conteudo;

    public TelaConteudoSelecionado(UsuarioController usuarioController, PasseioController passeioController, PacoteController pacoteController, Object conteudo, RoteiroController roteiroController){
        this.usuarioController = usuarioController;
        this.passeioController = passeioController;
        this.pacoteController = pacoteController;
        this.conteudo = conteudo;
        this.roteiroController = roteiroController;
    }

    public void iniciarTela() {
        // Configurações padrão da tela de login
        setTitle("Conteudo Selecionado");
        setSize(440, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        interFont = FontLoader.loadFont("/fontsNewDestiny/Inter.ttc", 16f);

        BackgroundPanel containerMain = new BackgroundPanel("/photos/backgroundMain.png");
        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(20, 6, 20, 2));

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
                TelaVisualizacao telaVisualizacao = new TelaVisualizacao(usuarioController, passeioController,pacoteController, roteiroController);
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
        header.add(Box.createHorizontalStrut(75));
        header.add(logoNew);

        // fim da header

        //começo da center
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setOpaque(false);

        //titulo
        JLabel titleContent = new JLabel();
        titleContent.setForeground(Color.WHITE);
        titleContent.setFont(interFont.deriveFont(18f));
        titleContent.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleContent.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 2));

        //detalhes conteudo
        JLabel detailsContentPasseio = new JLabel();
        detailsContentPasseio.setForeground(Color.WHITE);
        detailsContentPasseio.setFont(interFont.deriveFont(14f));
        detailsContentPasseio.setVerticalAlignment(SwingConstants.TOP);
        detailsContentPasseio.setHorizontalAlignment(SwingConstants.LEFT);
        detailsContentPasseio.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsContentPasseio.setMaximumSize(new Dimension(370, 180));
        detailsContentPasseio.setOpaque(false);
        detailsContentPasseio.setVerticalTextPosition(JLabel.TOP);

        JLabel detailsContentPacote = new JLabel();
        detailsContentPacote.setForeground(Color.WHITE);
        detailsContentPacote.setFont(interFont.deriveFont(14f));
        detailsContentPacote.setVerticalAlignment(SwingConstants.TOP);
        detailsContentPacote.setHorizontalAlignment(SwingConstants.LEFT);
        detailsContentPacote.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsContentPacote.setOpaque(false);
        detailsContentPacote.setVerticalTextPosition(JLabel.TOP);
        detailsContentPacote.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 2));


        JScrollPane scrollHorizontal = new JScrollPane();
        scrollHorizontal.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollHorizontal.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollHorizontal.setPreferredSize(new Dimension(370, 210));
        scrollHorizontal.setBorder(null);
        scrollHorizontal.setOpaque(false);
        scrollHorizontal.getViewport().setOpaque(false);

        //painel botoes

        // Painel de botões
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        botoesPanel.setOpaque(false);
        botoesPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE,45));
        botoesPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));


// Botão Comprar
        JButton buttonComprar = new JButton("Comprar");
        buttonComprar.setPreferredSize(new Dimension(150, 35));
        buttonComprar.setForeground(Color.WHITE);
        buttonComprar.setBackground(new Color(0x13A8AD));
        buttonComprar.setFont(interFont.deriveFont(15f));
        buttonComprar.setFocusPainted(false);
        buttonComprar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if(usuarioController.getUserLogged()!= null){

            buttonComprar.addActionListener(e -> JOptionPane.showMessageDialog(null, "Compra realizada!"));

        }else{
                ModalMenu modalMenu = new ModalMenu(usuarioController, pacoteController, passeioController, roteiroController);
                modalMenu.iniciarModal(TelaConteudoSelecionado.this);
                dispose();
        }

// Botão Adicionar ao Carrinho
        JButton buttonAddCarrinho = new JButton("Add Carrinho");
        buttonAddCarrinho.setPreferredSize(new Dimension(180, 35));
        buttonAddCarrinho.setForeground(Color.WHITE);
        buttonAddCarrinho.setBackground(new Color(0xFFA500));
        buttonAddCarrinho.setFont(interFont.deriveFont(15f));
        buttonAddCarrinho.setFocusPainted(false);
        buttonAddCarrinho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botoesPanel.add(buttonAddCarrinho);
        botoesPanel.add(buttonComprar);

        //fim painel botoes


        JPanel passeioRow = new JPanel(new FlowLayout());
        passeioRow.setOpaque(false);

        if (conteudo instanceof PacoteTuristicoEntity pacote) {
            titleContent.setText("<html><b>Pacote: " + pacote.getTitulo() + "</b>");
            StringBuilder pacoteDetails = new StringBuilder("<html>");

            if (pacote.getCategorias() != null && !pacote.getCategorias().isEmpty()) {
                pacoteDetails.append("<b>Categoria:</b><br>");
                for (CategoriaEntity categoria : pacote.getCategorias()) {
                    System.out.println("Categoria encontrada: " + categoria.getNome());

                    pacoteDetails.append(categoria.getNome()).append(", ");
                }
                pacoteDetails.append("<br><br>");
            }else{

                System.out.println("Categoria nao encontrada");
            }

            pacoteDetails.append(String.format("<b>Preço Total:</b> R$ %.2f<br>", pacote.getPrecoTotal()));


            if (pacote.getPasseios() != null && !pacote.getPasseios().isEmpty()) {
                pacoteDetails.append("<b>Passeios Inclusos:</b>");
                for (PasseioEntity passeio : pacote.getPasseios()) {
                    JPanel passeioCard = criarPasseioCard(passeio);
                    passeioRow.add(passeioCard);
                    passeioRow.add(Box.createRigidArea(new Dimension(15, 0)));
                }
            }

            scrollHorizontal.setViewportView(passeioRow);
            pacoteDetails.append("</html>");
            detailsContentPacote.setText(pacoteDetails.toString());

            //logica add carrinho pacote
            buttonAddCarrinho.addActionListener(e -> {


            if(usuarioController.getUserLogged()!=null){

                JOptionPane.showMessageDialog(null, "Adicionado ao carrinho!");

                usuarioController.carrinhoService().adicionarItemAoCarrinho(usuarioController.getUserLogged(), "Pacote", pacote.getId(), pacote.getTitulo(), pacote.getPrecoTotal());
            }else{
                ModalMenu modalMenu = new ModalMenu(usuarioController, pacoteController,passeioController,roteiroController);
                modalMenu.iniciarModal(TelaConteudoSelecionado.this);
                dispose();
                }
            });

            //fim logica

        } else if (conteudo instanceof PasseioEntity passeio) {
            titleContent.setText("<html><b>Passeio: " + passeio.getTitulo() + "</b></html>");
            StringBuilder passeioDetails = new StringBuilder("<html>");

            if (passeio.getCategorias() != null && !passeio.getCategorias().isEmpty()) {
                passeioDetails.append("<b>Categoria:</b><br>");
                for (CategoriaEntity categoria : passeio.getCategorias()) {
                    System.out.println("Categoria encontrada: " + categoria.getNome());

                    passeioDetails.append(categoria.getNome()).append(", ");
                }
                passeioDetails.append("<br><br>");
            }else{
                System.out.println("Sem categorias");
            }
            passeioDetails.append("<b>Descrição:</b> ").append(passeio.getDescricao()).append("<br><br>");
            passeioDetails.append("<b>Duração:</b> ").append(passeio.getDuracao()).append("<br><br>");
            passeioDetails.append("<b>Preço Ingresso:</b> R$ ").append(passeio.getPreco()).append("<br><br>");
            passeioDetails.append("<b>Localização:</b> ").append(passeio.getLocalizacao()).append("<br><br>");
            passeioDetails.append("<b>Horários:</b> ").append(passeio.getHorarios()).append("<br><br>");


            JScrollPane fotosScroll;
            if(passeio.getListaFotos() != null && !passeio.getListaFotos().isEmpty()){
                fotosScroll = criarFotosPasseioComScroll(passeio);
                System.out.println(passeio.getListaFotos().size());
                scrollHorizontal.setViewportView(fotosScroll);
            }

            passeioDetails.append("</html>");
            detailsContentPasseio.setText(passeioDetails.toString());
            //logica do botao add carrinho

            buttonAddCarrinho.addActionListener(e -> {

                JOptionPane.showMessageDialog(null, "Adicionado ao carrinho!");

                if(usuarioController.getUserLogged()!=null){

                    usuarioController.carrinhoService().adicionarItemAoCarrinho(usuarioController.getUserLogged(), "Passeio", passeio.getId(), passeio.getTitulo(), passeio.getPreco());

                }else{
                    ModalMenu modalMenu = new ModalMenu(usuarioController, pacoteController,passeioController,roteiroController);
                    modalMenu.iniciarModal(TelaConteudoSelecionado.this);
                    dispose();
                }

            });


        } else {
            titleContent.setText("<html><b>Conteúdo Não Encontrado</b></html>");
            detailsContentPasseio.setText("Não foi possível carregar os detalhes do conteúdo selecionado.");
        }

        //botao

        //fim botoes

        center.add(Box.createVerticalStrut(10));
        center.add(titleContent);
        center.add(Box.createVerticalStrut(10));

        if(conteudo instanceof PasseioEntity){

            center.add(detailsContentPasseio);

        }else{
            center.add(detailsContentPacote);
        }

        center.add(Box.createVerticalStrut(20));
        center.add(botoesPanel);


        center.add(Box.createVerticalStrut(10));
        center.add(scrollHorizontal);


        containerMain.add(header,BorderLayout.NORTH);
        containerMain.add(center, BorderLayout.CENTER);
        setContentPane(containerMain);
        setVisible(true);

    }

    public JScrollPane criarFotosPasseioComScroll(PasseioEntity passeio) {
        JPanel painelFotos = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelFotos.setOpaque(false);

        if (passeio.getListaFotos() != null && !passeio.getListaFotos().isEmpty()) {
            for (FotoEntity foto : passeio.getListaFotos()) {
                try {
                    URL url = new URL(foto.getUrl());
                    ImageIcon imgIcon = new ImageIcon(url);
                    Image imgRedimensionada = imgIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                    JLabel labelImagem = new JLabel(new ImageIcon(imgRedimensionada));
                    labelImagem.setPreferredSize(new Dimension(300, 200));
                    painelFotos.add(labelImagem);
                } catch (Exception e) {
                    System.out.println("Erro ao carregar imagem: " + e.getMessage());
                }
            }
        } else {
            painelFotos.add(new JLabel("Sem fotos disponíveis"));
        }

        JScrollPane scrollFotos = new JScrollPane(painelFotos,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollFotos.setPreferredSize(new Dimension(370, 220));
        scrollFotos.setBorder(null);
        scrollFotos.setOpaque(false);
        scrollFotos.getViewport().setOpaque(false);

        return scrollFotos;
    }


    public JPanel criarPasseioCard(PasseioEntity passeio) {
        JPanel cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(300, 200));
        cardPanel.setLayout(new OverlayLayout(cardPanel));

        try {
            String urlImagem = passeio.getListaFotos().get(0).getUrl();
            URL url = new URL(urlImagem);
            ImageIcon imgIcon = new ImageIcon(url);
            Image imagem = imgIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(imagem));
            imageLabel.setPreferredSize(new Dimension(300, 200));
            imageLabel.setAlignmentX(0.0f);
            imageLabel.setAlignmentY(0.0f);

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


}
