package View;

import Controller.PacoteController;
import Controller.PasseioController;
import Controller.RoteiroController;
import Controller.UsuarioController;
import Model.Entidades.*;
import Model.Servicos.CarrinhoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;

public class TelaCarrinhoUsuario extends JFrame {

    private UsuarioController usuarioController;
    private PasseioController passeioController;
    private PacoteController pacoteController;
    private RoteiroController roteiroController;
    private CarrinhoService carrinhoService;

    private Font interFont;
    private Font interFontBold;
    private CoresProjeto coresProjeto = new CoresProjeto();

    private JPanel carrinhoPanel;
    private JLabel totalLabel;

    public TelaCarrinhoUsuario(UsuarioController usuarioController, PacoteController pacoteController,
                               PasseioController passeioController, RoteiroController roteiroController,
                               CarrinhoService carrinhoService) {
        this.usuarioController = usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
        this.roteiroController = roteiroController;
        this.carrinhoService = carrinhoService;
    }

    public void iniciarTela() {
        setTitle("Criando Seu Roteiro");
        setSize(440, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        interFont = FontLoader.loadFont("/fontsNewDestiny/Inter.ttc", 16f);
        interFontBold = FontLoader.loadFont("/fontsNewDestiny/InterVariable.ttf", 16f);

        BackgroundPanel containerMain = new BackgroundPanel("/photos/backgroundMain.png");
        containerMain.setLayout(new BorderLayout());
        containerMain.setBorder(BorderFactory.createEmptyBorder(30, 0, 70, 0));

        // Header
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
                TelaCarrinhoUsuario.this.dispose();

                TelaPerfilUsuario telaPerfilUsuario = new TelaPerfilUsuario(usuarioController, pacoteController, passeioController, roteiroController);
                telaPerfilUsuario.iniciarPerfilUsuario();

            }
        });

        header.add(buttonBack);
        header.add(Box.createHorizontalStrut(75));

        ImageIcon imgLogo = null;
        URL logoUrl = getClass().getResource("/photos/logo.png");
        if (logoUrl != null) {
            imgLogo = new ImageIcon(logoUrl);
        } else {
            System.err.println("Imagem do logo não encontrada: /photos/logo.png");
        }
        JLabel logoNew = new JLabel(imgLogo);
        logoNew.setSize(100, 50);
        header.add(logoNew);

        // Título
        JLabel tituloLabel = new JLabel("Seu Carrinho");
        tituloLabel.setFont(interFontBold.deriveFont(Font.BOLD, 22f));
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setHorizontalAlignment(SwingConstants.LEFT);
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 0));

        // Painel central com título + scroll
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);

        centerPanel.add(tituloLabel, BorderLayout.NORTH);

        // Painel dos itens do carrinho
        carrinhoPanel = new JPanel();
        carrinhoPanel.setLayout(new BoxLayout(carrinhoPanel, BoxLayout.Y_AXIS));
        carrinhoPanel.setOpaque(false);
        carrinhoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Scroll pane para o carrinho
        JScrollPane scrollPane = new JScrollPane(carrinhoPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Footer com total e botão finalizar
        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        footer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        totalLabel = new JLabel();
        totalLabel.setFont(interFontBold.deriveFont(Font.BOLD, 18f));
        totalLabel.setForeground(Color.WHITE);

        JButton finalizarBtn = new JButton("Finalizar Compra");
        finalizarBtn.setBackground(new Color(0x13A8AD));
        finalizarBtn.setForeground(Color.WHITE);
        finalizarBtn.setFont(interFontBold);
        finalizarBtn.setFocusPainted(false);
        finalizarBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        finalizarBtn.setPreferredSize(new Dimension(180, 45));
        finalizarBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        finalizarBtn.addActionListener(e -> {
            UsuarioEntity usuarioLogado = usuarioController.getUserLogged();
            if (usuarioLogado == null) {
                JOptionPane.showMessageDialog(this, "Usuário não está logado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            carrinhoService.limparCarrinho(usuarioLogado);
            atualizarCarrinho();
            JOptionPane.showMessageDialog(this, "Compra finalizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        });

        footer.add(totalLabel, BorderLayout.WEST);
        footer.add(finalizarBtn, BorderLayout.EAST);

        centerPanel.add(footer, BorderLayout.SOUTH);

        containerMain.add(header, BorderLayout.NORTH);
        containerMain.add(centerPanel, BorderLayout.CENTER);

        setContentPane(containerMain);

        atualizarCarrinho();

        setVisible(true);
    }

    private void atualizarCarrinho() {
        carrinhoPanel.removeAll();

        UsuarioEntity usuarioLogado = usuarioController.getUserLogged();
        if (usuarioLogado == null) {
            JLabel vazio = new JLabel("Nenhum usuário logado.");
            vazio.setForeground(Color.WHITE);
            carrinhoPanel.add(vazio);
            carrinhoPanel.revalidate();
            carrinhoPanel.repaint();
            totalLabel.setText("");
            return;
        }

        CarrinhoEntity carrinho = carrinhoService.getCarrinho(usuarioLogado);
        if (carrinho == null || carrinho.getItens().isEmpty()) {
            JLabel vazio = new JLabel("Seu carrinho está vazio.");
            vazio.setForeground(Color.WHITE);
            carrinhoPanel.add(vazio);
            totalLabel.setText("Total: R$ 0,00");
        } else {
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            for (ItemCarrinhoEntity item : carrinho.getItens()) {
                JPanel itemCard = criarItemCard(item);
                carrinhoPanel.add(itemCard);
                carrinhoPanel.add(Box.createVerticalStrut(10));
            }

            BigDecimal total = carrinho.calcularTotal();
            totalLabel.setText("Total: " + currencyFormat.format(total));
        }

        carrinhoPanel.revalidate();
        carrinhoPanel.repaint();
    }

    private JPanel criarItemCard(ItemCarrinhoEntity item) {
        JPanel cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(300, 200));
        cardPanel.setLayout(new OverlayLayout(cardPanel));

        try {
            // Tenta pegar a imagem do item (exemplo: do passeio ou pacote)
            // Aqui você pode adaptar para pegar a URL certa conforme tipo do item
            String urlImagem = null;

            // Exemplo: se for "passeio", tenta pegar a foto principal do passeio
            if ("passeio".equalsIgnoreCase(item.getTipo())) {
                PasseioEntity passeio = passeioController.findById(item.getItemId());
                if (passeio != null && !passeio.getListaFotos().isEmpty()) {
                    urlImagem = passeio.getListaFotos().get(0).getUrl();
                }
            } else if ("pacote".equalsIgnoreCase(item.getTipo())) {
                PacoteTuristicoEntity pacote = pacoteController.findById(item.getItemId());
                if (pacote.getPasseios() != null && !pacote.getPasseios().isEmpty()) {
                    urlImagem = pacote.getPasseios().get(0).getListaFotos().get(0).getUrl();
                }
            }

            ImageIcon imgIcon;
            if (urlImagem != null && !urlImagem.isEmpty()) {
                URL url = new URL(urlImagem);
                imgIcon = new ImageIcon(url);
            } else {
                // imagem padrão se não tiver URL
                imgIcon = new ImageIcon(getClass().getResource("/photos/backgroundPasseio.png"));
            }

            Image imagem = imgIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(imagem));
            imageLabel.setPreferredSize(new Dimension(300, 200));
            imageLabel.setAlignmentX(0.0f);
            imageLabel.setAlignmentY(0.0f);

            // Painel de título com fundo preto transparente
            JPanel titlePanel = new JPanel();
            titlePanel.setOpaque(true);
            titlePanel.setBackground(new Color(0, 0, 0, 150));
            titlePanel.setPreferredSize(new Dimension(300, 50));
            titlePanel.setMaximumSize(new Dimension(300, 50));
            titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 15));

            JLabel titleLabel = new JLabel(item.getTitulo());
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(interFontBold.deriveFont(16f));
            titlePanel.add(titleLabel);

            JLabel precoLabel = new JLabel(String.format("R$ %.2f", item.getPreco()));
            precoLabel.setForeground(Color.WHITE);
            precoLabel.setFont(interFont.deriveFont(14f));
            titlePanel.add(Box.createHorizontalStrut(20)); // espaçamento
            titlePanel.add(precoLabel);

            // Container vertical para empurrar título para baixo
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
            System.out.println("Erro ao carregar imagem do item do carrinho: " + e.getMessage());
            cardPanel.add(new JLabel("Imagem indisponível"));
        }

        // Clique no card (exemplo: abrir detalhe do passeio/pacote)
        cardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if ("passeio".equalsIgnoreCase(item.getTipo())) {
                    PasseioEntity passeio = passeioController.findById(item.getItemId());
                    if (passeio != null) {
                        TelaCarrinhoUsuario.this.dispose();

                        TelaConteudoSelecionado tela = new TelaConteudoSelecionado(
                                usuarioController, passeioController, pacoteController, passeio, roteiroController);
                        tela.iniciarTela();
                    }
                } else if ("pacote".equalsIgnoreCase(item.getTipo())) {
                    PacoteTuristicoEntity pacote = pacoteController.findById(item.getItemId());
                    if (pacote != null) {
                        TelaCarrinhoUsuario.this.dispose();

                        TelaConteudoSelecionado tela = new TelaConteudoSelecionado(
                                usuarioController, passeioController, pacoteController, pacote, roteiroController);
                        tela.iniciarTela();
                    }
                }
            }
        });

        cardPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY, 1, true),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)
                )
        );

        return cardPanel;
    }

}
