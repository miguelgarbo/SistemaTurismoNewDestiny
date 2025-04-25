package Repositorio;

import Entidades.ServicoCategoriaEntity;
import javax.persistence.EntityManager;
import java.util.List;

public class ServicoCategoriaRepository {
    private EntityManager em;

    public ServicoCategoriaRepository(EntityManager em) {
        this.em = em;
    }

    public ServicoCategoriaEntity findById(Long id) {
        return em.find(ServicoCategoriaEntity.class, id);
    }

    public void cadastrar(ServicoCategoriaEntity servicoCategoria) {
        em.getTransaction().begin();
        em.persist(servicoCategoria);
        em.getTransaction().commit();
    }

    public List<ServicoCategoriaEntity> buscarTodos() {
        return em.createQuery("SELECT sc FROM servico_categorias sc", ServicoCategoriaEntity.class)
                .getResultList();
    }

    public void atualizar(ServicoCategoriaEntity servicoCategoria) {
        em.getTransaction().begin();
        em.merge(servicoCategoria);
        em.getTransaction().commit();
    }

    public void remover(ServicoCategoriaEntity servicoCategoria) {
        em.getTransaction().begin();
        em.remove(em.contains(servicoCategoria) ? servicoCategoria : em.merge(servicoCategoria));
        em.getTransaction().commit();
    }
} 