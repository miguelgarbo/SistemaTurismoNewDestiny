package Servicos;

import Model.Entidades.CategoriaEntity;
import Repositorio.CategoriaRepository;
import javax.transaction.Transactional;
import java.util.List;

public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

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



} 