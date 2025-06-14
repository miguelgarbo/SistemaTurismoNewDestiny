package Model.Repositorio;

import Model.Entidades.DiaEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class DiaRepository {
    private EntityManager em;

    public DiaRepository(EntityManager em) {
        this.em = em;
    }

    public DiaEntity findById(Long id) {
        return em.find(DiaEntity.class, id);
    }

    public void cadastrar(DiaEntity dia) {
        em.getTransaction().begin();
        em.persist(dia);
        em.getTransaction().commit();
    }

    public List<DiaEntity> buscarTodos() {
        return em.createQuery("SELECT d FROM dia d", DiaEntity.class)
                .getResultList();
    }

    public void atualizar(DiaEntity dia) {
        em.getTransaction().begin();
        em.merge(dia);
        em.getTransaction().commit();
    }

    public void remover(DiaEntity dia) {
        em.getTransaction().begin();
        em.remove(em.contains(dia) ? dia : em.merge(dia));
        em.getTransaction().commit();
    }

    public List<DiaEntity> buscarPorRoteiroId(Long roteiroId) {
        return em.createQuery("SELECT d FROM dia d WHERE d.roteiro.id = :roteiroId", DiaEntity.class)
                .setParameter("roteiroId", roteiroId)
                .getResultList();
    }
}
