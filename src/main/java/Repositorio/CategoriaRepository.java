package Repositorio;

import Entidades.CategoriaEntity;
import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaRepository {
    private EntityManager em;

    public CategoriaRepository(EntityManager em) {
        this.em = em;
    }

    public CategoriaEntity findById(Long id) {
        return em.find(CategoriaEntity.class, id);
    }

    public void cadastrar(CategoriaEntity categoria) {
        em.getTransaction().begin();
        em.persist(categoria);
        em.getTransaction().commit();
    }

    public List<CategoriaEntity> buscarTodos() {
        return em.createQuery("SELECT c FROM categorias c", CategoriaEntity.class)
                .getResultList();
    }

    public CategoriaEntity buscarPorNome(String nome) {
        try {
            return em.createQuery("SELECT c FROM categorias c WHERE c.nome = :nome", CategoriaEntity.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void atualizar(CategoriaEntity categoria) {
        em.getTransaction().begin();
        em.merge(categoria);
        em.getTransaction().commit();
    }

    public void remover(CategoriaEntity categoria) {
        em.getTransaction().begin();
        em.remove(em.contains(categoria) ? categoria : em.merge(categoria));
        em.getTransaction().commit();
    }
} 