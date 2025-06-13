package View;

import Controller.PacoteController;
import Controller.PasseioController;
import Controller.RoteiroController;
import Controller.UsuarioController;
import Model.Entidades.RoteiroPersonalizadoEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class TelaCriarRoteiro extends JFrame {

    private UsuarioController usuarioController;
    private PasseioController passeioController;
    private PacoteController pacoteController;
    private RoteiroController roteiroController;
    private Font interFont;
    private Font interFontBold;
    private CoresProjeto coresProjeto = new CoresProjeto();

    private JTextField campoTitulo;
    private JTextField campoData;
    private JTextField campoDias;


    public TelaCriarRoteiro(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController, RoteiroController roteiroController) {
        this.usuarioController = usuarioController;
        this.pacoteController = pacoteController;
        this.passeioController = passeioController;
        this.roteiroController = roteiroController;
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
        containerMain.setLayout(new BorderLayout()); // Mantemos BorderLayout no containerMain
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
                super.mouseClicked(e);
                TelaPerfilUsuario telaPerfilUsuario = new TelaPerfilUsuario(usuarioController,pacoteController, passeioController, roteiroController);
                telaPerfilUsuario.iniciarPerfilUsuário();
                dispose(); // Fecha a tela atual
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

        // Conteúdo central
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(60, 40, 20, 40));

        JLabel tituloLabel = new JLabel("Criando Seu Roteiro");
        tituloLabel.setFont(interFontBold.deriveFont(Font.BOLD, 22f));
        tituloLabel.setForeground(Color.WHITE);
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Garante a centralização

        JPanel campoTituloPanel = criarCampoTexto("Título do Roteiro");
        JPanel campoDataPanel = criarCampoTexto("Data De Início");
        JPanel campoDiasPanel = criarCampoTexto("Quantidade de Dias");

        JButton botaoProximo = new JButton("Próximo");
        botaoProximo.setFont(interFont.deriveFont(16f));
        botaoProximo.setForeground(coresProjeto.corPrincipalAzul);
        botaoProximo.setAlignmentX(Component.CENTER_ALIGNMENT); // Garante a centralização
        botaoProximo.setBackground(Color.WHITE);
        botaoProximo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botaoProximo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String titulo = campoTitulo.getText().trim();
                String data = campoData.getText().trim();
                String diasStr = campoDias.getText().trim();

                if (titulo.isEmpty() || data.isEmpty() || diasStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int qtdDias;
                try {
                    qtdDias = Integer.parseInt(diasStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Quantidade de dias deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    RoteiroPersonalizadoEntity roteiroCriado = roteiroController.criarRoteiro(usuarioController.getUserLogged(), titulo, data, qtdDias);
                    // Pode mostrar mensagem de sucesso ou ir para próxima etapa
                    JOptionPane.showMessageDialog(null, "Roteiro criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    // Exemplo: abrir próxima tela e fechar esta
                    TelaCriarRoteiro2Etapa telaCriarRoteiro2Etapa = new TelaCriarRoteiro2Etapa(usuarioController, pacoteController, passeioController, roteiroController);
                    telaCriarRoteiro2Etapa.iniciarTela(roteiroCriado);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao criar roteiro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Erro ao criar roteiro: " + ex.getMessage());
                }
            }
        });



        formPanel.add(tituloLabel);
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(campoTituloPanel);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(campoDataPanel);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(campoDiasPanel);
        formPanel.add(Box.createVerticalStrut(25));
        formPanel.add(botaoProximo);

        // Novo painel para centralizar o formPanel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false); // Torna o painel transparente para que o fundo seja visível
        centerPanel.add(formPanel, BorderLayout.CENTER); // Adiciona o formPanel ao centro deste novo painel

        containerMain.add(header, BorderLayout.NORTH);
        containerMain.add(centerPanel, BorderLayout.CENTER); // Adiciona o centerPanel ao centro do containerMain

        setContentPane(containerMain);
        setVisible(true);
    }

    private JPanel criarCampoTexto(String labelTexto) {

        JPanel totalidade = new JPanel();
        totalidade.setLayout(new BoxLayout(totalidade, BoxLayout.Y_AXIS));
        totalidade.setOpaque(false);

        JLabel textAboveCampo = new JLabel("<html><b>" + labelTexto + "</b></html>");
        textAboveCampo.setForeground(Color.WHITE);
        textAboveCampo.setFont(interFont.deriveFont(18f));
        textAboveCampo.setAlignmentX(Component.CENTER_ALIGNMENT);
        textAboveCampo.setMaximumSize(new Dimension(250, 35)); // Largura máxima para centralizar
        textAboveCampo.setPreferredSize(new Dimension(250, 35));

        JTextField campo = new JTextField();
        campo.setBackground(coresProjeto.corOpacaField);
        campo.setForeground(Color.BLACK);
        campo.setFont(interFontBold.deriveFont(16f));
        campo.setMaximumSize(new Dimension(250, 35)); // Largura máxima para centralizar
        campo.setPreferredSize(new Dimension(250, 35));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o campo de texto
        campo.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8)); // padding interno

        totalidade.add(textAboveCampo);
        totalidade.add(Box.createVerticalStrut(5));
        totalidade.add(campo);

        // Aqui armazenamos o campo no atributo correto
        if (labelTexto.equals("Título do Roteiro")) {
            campoTitulo = campo;
        } else if (labelTexto.equals("Data De Início")) {
            campoData = campo;
        } else if (labelTexto.equals("Quantidade de Dias")) {
            campoDias = campo;
        }

        return totalidade;
    }

}