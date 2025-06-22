package View;

import Controller.AdmnistradorController;
import Controller.UsuarioController;

import java.util.Scanner;

public class MenuPrincipal {

    private final UsuarioController usuarioController;
    private final AdmnistradorController admController;
    private final Scanner sc = new Scanner(System.in);

    public MenuPrincipal(UsuarioController usuarioController, AdmnistradorController admController) {
        this.usuarioController = usuarioController;
        this.admController = admController;
    }
//TERMINAL CODIGO
    
    public void mostrarMenuPrincipal(){

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("==SISTEMA DE TURISMO (NEW DESTINY)==");
            System.out.println("===MENU PRINCIPAL===");
            System.out.println("1 - Login Como Usuário");
            System.out.println("2 - Cadastrar Como Usuário");
            System.out.println("3 - Entrar Como Usuario");
            System.out.println("4 - Entrar Como Administrador");
            System.out.println("5 - Sair");
            System.out.println("Informe A opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    if(usuarioController.getUserLogged()!=null){
                        System.out.println("Voce ja esta logado");
                        usuarioController.menuUsuario();

                    }else {
                        usuarioController.menuLogin();
                    }
                    break;

                case 2:
                    usuarioController.menuCadastroUsuario();
                    break;

                case 3:

                    usuarioController.menuUsuario();
                    break;

                case 4:
                    admController.menuAdministrador();
                    break;
                default:
                    System.out.println("Opcao Invalida");
                    break;
            }
        }while (opcao !=5);
        
    }
}

