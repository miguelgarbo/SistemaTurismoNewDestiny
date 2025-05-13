package Servicos;

import Model.Entidades.CategoriaEntity;
import Repositorio.CategoriaRepository;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Scanner;

public class CategoriaService {
    @Inject
    private CategoriaRepository categoriaRepository;

    private final Scanner sc = new Scanner(System.in);

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }


    @Transactional
    public void cadastrar(CategoriaEntity categoria) {
        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome da categoria não pode estar vazio");
        }
        categoriaRepository.cadastrar(categoria);
    }

    @Transactional
    public void remover(Long id) {
        CategoriaEntity categoria = categoriaRepository.findById(id);
        if (categoria != null) {
            categoriaRepository.remover(categoria);
        }
    }

    @Transactional
    public void atualizar(CategoriaEntity categoria) {
        if (categoria.getId() == null) {
            throw new IllegalArgumentException("ID da categoria não pode ser nulo");
        }
        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome da categoria não pode estar vazio");
        }
        categoriaRepository.atualizar(categoria);
    }

    public List<CategoriaEntity> buscarTodos() {
        return categoriaRepository.buscarTodos();
    }

    public CategoriaEntity findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public void mostrarTodasCategorias() {

        System.out.println("==CATEGORIAS DISPONÍVEIS==");
        for (CategoriaEntity categoria : buscarTodos()) {
            System.out.println("ID: " + categoria.getId());
            System.out.println("Nome: " + categoria.getNome());
            System.out.println("Descrição: " + categoria.getDescricao());
            System.out.println("-------------------------------------");
        }
    }

    public void cadastrarCategoria() {
        CategoriaEntity categoriaNova = new CategoriaEntity();

        System.out.println("== CADASTRO DE CATEGORIA ==");

        System.out.print("Digite o nome da categoria: ");
        categoriaNova.setNome(sc.nextLine());

        System.out.print("Digite a descrição da categoria: ");
        categoriaNova.setDescricao(sc.nextLine());

        cadastrar(categoriaNova);
        System.out.println("Categoria '" + categoriaNova.getNome() + " cadastrada com sucesso!");
    }
} 