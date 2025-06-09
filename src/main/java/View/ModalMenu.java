package View;

import Controller.PacoteController;
import Controller.PasseioController;
import Controller.UsuarioController;
import Model.Entidades.UsuarioEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModalMenu {

    private UsuarioController usuarioController;
    private PacoteController pacoteController;
    private PasseioController passeioController;

    public ModalMenu(UsuarioController usuarioController, PacoteController pacoteController, PasseioController passeioController) {
        this.usuarioController = usuarioController;
        this.passeioController = passeioController;
        this.pacoteController = pacoteController;
    }

    public void iniciarModal(JFrame frame, UsuarioEntity userLogged){

        CoresProjeto coresProjeto = new CoresProjeto();

        JDialog modal = new JDialog(frame, "Menu", true);
        modal.setSize(300, 150);
        modal.setLayout(null);

        JButton buttonUserPage = new JButton();
        buttonUserPage.setMaximumSize(new Dimension(120, 30));
        buttonUserPage.setForeground(new Color(0x208482));
        buttonUserPage.setBackground(Color.WHITE);

        buttonUserPage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //TelaUsuario telaUsuario = new TelaUsuario();
                //telaUsuario.iniciarTela(userLogged);
            }
        });

        buttonUserPage.setText("Minha Conta");
        buttonUserPage.setBounds(70, 20, 150, 30);


        JButton buttonFechar = new JButton("Fechar");

        buttonFechar.setForeground(coresProjeto.corVermelha);
        buttonFechar.setBackground(Color.WHITE);
        buttonFechar.setBounds(70, 60, 150, 30);
        buttonFechar.addActionListener(ev -> modal.dispose());

        modal.add(buttonUserPage);
        modal.add(buttonFechar);
        modal.setLocationRelativeTo(frame); // Centraliza
        modal.setVisible(true);

    }


    public void iniciarModal(JFrame frame) {

        CoresProjeto coresProjeto = new CoresProjeto();

        JDialog modal = new JDialog(frame, "Menu", true);
        modal.setSize(350, 250);
        modal.setLayout(null);

        // Botão Login
        JButton buttonLogin = new JButton("Login");
        buttonLogin.setBounds(100, 30, 150, 30);
        buttonLogin.setForeground(coresProjeto.corPrincipalAzul);
        buttonLogin.setBackground(Color.WHITE);

        buttonLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e);
                modal.dispose();
                TelaLogin telaLogin = new TelaLogin(usuarioController, pacoteController, passeioController);
                telaLogin.iniciarTela();
            }
        });

        // Botão Cadastrar
        JButton buttonRegister = new JButton("Cadastrar");
        buttonRegister.setBounds(100, 80, 150, 30);
        buttonRegister.setForeground(coresProjeto.corAzulSecundaria);
        buttonRegister.setBackground(Color.WHITE);

        buttonRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                modal.dispose();
                TelaCadastro telaCadastro = new TelaCadastro(usuarioController, passeioController, pacoteController);
                telaCadastro.iniciarTela();
            }
        });

        JButton buttonFechar = new JButton("Fechar");
        buttonFechar.setBounds(100, 130, 150, 30);
        buttonFechar.setForeground(coresProjeto.corVermelha);
        buttonFechar.setBackground(Color.WHITE);
        buttonFechar.addActionListener(ev -> modal.dispose());

        modal.add(buttonLogin);
        modal.add(buttonRegister);
        modal.add(buttonFechar);

        modal.setLocationRelativeTo(frame);
        modal.setVisible(true);
    }



}
