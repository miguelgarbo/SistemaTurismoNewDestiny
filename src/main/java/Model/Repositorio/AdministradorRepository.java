package Model.Repositorio;

import Model.Entidades.AdministradorEntity;



import javax.persistence.EntityManager;
import java.util.List;

public class AdministradorRepository {

    private final EntityManager em;

    public AdministradorRepository(EntityManager em) {
        this.em = em;
    }

    public AdministradorEntity findById(Long id) {
        return em.find(AdministradorEntity.class, id);
    }

    public void cadastrar(AdministradorEntity administrador) {
        em.getTransaction().begin();
        em.persist(administrador);
        em.getTransaction().commit();
    }

    public List<AdministradorEntity> buscarTodos() {
        return em.createQuery("SELECT a FROM Administrador a", AdministradorEntity.class).getResultList();
    }

    public AdministradorEntity buscarPorEmail(String email) {
        try {
            return em.createQuery("SELECT a FROM Administrador a WHERE a.email = :email", AdministradorEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void atualizar(AdministradorEntity administrador) {
        em.getTransaction().begin();
        em.merge(administrador);
        em.getTransaction().commit();
    }

    public void remover(AdministradorEntity administrador) {
        em.getTransaction().begin();
        em.remove(em.contains(administrador) ? administrador : em.merge(administrador));
        em.getTransaction().commit();
    }
}