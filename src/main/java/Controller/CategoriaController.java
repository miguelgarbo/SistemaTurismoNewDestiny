package Controller;

import Model.Entidades.CategoriaEntity;
import Servicos.CategoriaService;

import java.util.Scanner;
import java.util.List;

public class CategoriaController {

    private final CategoriaService categoriaService;
    private final Scanner sc = new Scanner(System.in);

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public void menuCadastrarCategoria() {
        CategoriaEntity categoriaNova = new CategoriaEntity();

        System.out.println("== CADASTRO DE CATEGORIA ==");

        System.out.print("Digite o nome da categoria: ");
        categoriaNova.setNome(sc.nextLine());

        System.out.print("Digite a descrição da categoria: ");
        categoriaNova.setDescricao(sc.nextLine());

        categoriaService.cadastrar(categoriaNova);
        System.out.println("Categoria '" + categoriaNova.getNome() + "' cadastrada com sucesso!");
    }

    public void exibirTodasCategorias() {
        List<CategoriaEntity> categorias = categoriaService.buscarTodos();

        System.out.println("== CATEGORIAS DISPONÍVEIS ==");
        for (CategoriaEntity categoria : categorias) {
            System.out.println("ID: " + categoria.getId());
            System.out.println("Nome: " + categoria.getNome());
            System.out.println("Descrição: " + categoria.getDescricao());
            System.out.println("-------------------------------------");
        }
    }
}
