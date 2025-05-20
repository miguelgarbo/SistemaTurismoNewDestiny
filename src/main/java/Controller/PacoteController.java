package Controller;

import Model.Entidades.CategoriaEntity;
import Model.Entidades.PacoteTuristicoEntity;
import Model.Entidades.PasseioEntity;
import Model.Entidades.UsuarioEntity;
import Servicos.CategoriaService;
import Servicos.PacoteTuristicoService;
import Servicos.PasseioService;

import java.util.Scanner;

public class PacoteController {

    private final PacoteTuristicoService pacoteTuristicoService;
    private final PagamentoController pagamentoController;
    private final CategoriaController categoriaController;
    private final CategoriaService categoriaService;
    private final PasseioController passeioController;
    private final PasseioService passeioService;
    private final Scanner sc = new Scanner(System.in);


    public PacoteController(PacoteTuristicoService pacoteTuristicoService, PagamentoController pagamentoController, CategoriaController categoriaController, CategoriaService categoriaService, PasseioController passeioController, PasseioService passeioService) {
        this.pacoteTuristicoService = pacoteTuristicoService;
        this.pagamentoController = pagamentoController;
        this.categoriaController = categoriaController;
        this.categoriaService = categoriaService;
        this.passeioController = passeioController;
        this.passeioService = passeioService;
    }

    public void exibirPacotesDisponiveis(UsuarioEntity usuario) {
        System.out.println("==== Lista de Pacotes Turísticos ====");

        for (PacoteTuristicoEntity pacote : pacoteTuristicoService.buscarTodos()) {
            exibirDetalhesPacote(pacote);
        }
            System.out.println("1 - Realizar compra /// 2 - Sair");
            int op = sc.nextInt();
            sc.nextLine();

            if (op == 1) {
                pagamentoController.menuPagarPacote(usuario);
            } else {
                System.out.println("Saindooo");
            }

    }


    public void exibirPacotesDisponiveis() {
        System.out.println("==== Lista de Pacotes Turísticos ====");

        for (PacoteTuristicoEntity pacote : pacoteTuristicoService.buscarTodos()) {
            exibirDetalhesPacote(pacote);
        }

            System.out.println("1 - Voltar ");
            int op = sc.nextInt();
            sc.nextLine();

            if (op == 1) {
                System.out.println("Voltando");
            }

    }


    private void exibirDetalhesPacote(PacoteTuristicoEntity pacote) {
        System.out.printf("ID PACOTE: %d\n", pacote.getId());
        System.out.printf("Título: %s\n", pacote.getTitulo());
        System.out.printf("Preço Total: R$ %.2f\n", pacote.getPrecoTotal());

        if (pacote.getPasseios() != null || !pacote.getPasseios().isEmpty()) {
            System.out.println("PASSEIOS INCLUSOS:");
            for (PasseioEntity passeio : pacote.getPasseios()) {
                System.out.println("ID PASSEIO: " + passeio.getId());
                System.out.println("Título: " + passeio.getTitulo());
                System.out.println("Descrição: " + passeio.getDescricao());
                System.out.println("Localização: " + passeio.getLocalizacao());
                System.out.println("Preço: " + passeio.getPreco());
                System.out.println("Duração: " + passeio.getDuracao());
                System.out.println("-------------------------------------");
            }
        } else {
            System.out.println("Sem Passeios.");
        }

        if (pacote.getCategorias() != null && !pacote.getCategorias().isEmpty()) {
            System.out.println("CATEGORIAS:");
            for (CategoriaEntity categoria : pacote.getCategorias()) {
                System.out.println("Título: " + categoria.getNome());
                System.out.println("-------------------------------------");
            }
        } else {
            System.out.println("Nenhuma categoria inclusa");
        }
    }

    public void menuCadastrarPacote() {
        PacoteTuristicoEntity pacoteNovo = new PacoteTuristicoEntity();
        String continuar;

        System.out.println("== CADASTRO DE PACOTE ==");

        System.out.print("Digite o título do pacote: ");
        pacoteNovo.setTitulo(sc.nextLine());

        do {
            System.out.println("== Categorias Disponíveis ==");
            categoriaController.exibirTodasCategorias();

            System.out.print("Informe o ID da Categoria: ");
            Long idCategoria = sc.nextLong();
            sc.nextLine(); // consumir o ENTER

            CategoriaEntity categoriaSelecionada = categoriaService.findById(idCategoria);

            if (categoriaSelecionada != null) {
                pacoteNovo.addCategoria(categoriaSelecionada);
                categoriaService.atualizar(categoriaSelecionada);
                System.out.println("Categoria adicionada com sucesso!");
            } else {
                System.out.println("Categoria não encontrada.");
            }

            System.out.print("Deseja adicionar mais uma categoria? (sim/nao): ");
            continuar = sc.nextLine().toLowerCase();

        } while (continuar.equals("sim"));

        do {
            System.out.println("=== Passeios disponíveis ===");
            passeioController.exibirTodosPasseios();

            System.out.print("Informe o ID do passeio que deseja adicionar: ");
            Long idPasseioInformado = sc.nextLong();
            sc.nextLine(); // consumir o ENTER

            PasseioEntity passeioEscolhido = passeioService.buscarPorId(idPasseioInformado);
            if (passeioEscolhido != null) {
                pacoteNovo.addPasseio(passeioEscolhido);
                System.out.println("Passeio adicionado com sucesso!");
            } else {
                System.out.println("Passeio não encontrado.");
            }

            System.out.print("Deseja adicionar mais um passeio? (sim/nao): ");
            continuar = sc.nextLine().toLowerCase();

        } while (continuar.equals("sim"));

        pacoteNovo.setPrecoTotal(pacoteTuristicoService.somarValorPasseios(pacoteNovo.getPasseios()));

        pacoteTuristicoService.cadastrar(pacoteNovo);

        System.out.println("Pacote '" + pacoteNovo.getTitulo() + "' cadastrado com sucesso!");
    }


}
