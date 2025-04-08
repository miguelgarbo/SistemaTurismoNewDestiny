package Repositorio;

import Entidades.RoteiroPersonalizadoEntity;
import Entidades.Roteiro_PasseiosEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class Roteiro_PasseiosRepository {

    private EntityManager em;

    public Roteiro_PasseiosRepository(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Roteiro_PasseiosEntity roteiroPasseios){
        em.getTransaction().begin();
        em.persist(roteiroPasseios);
        em.getTransaction().commit();
    }

    public void atualizar(Roteiro_PasseiosEntity roteiroPasseios){
        em.getTransaction().begin();
        em.merge(roteiroPasseios);
        em.getTransaction().commit();
    }

    public void remover(Roteiro_PasseiosEntity roteiroPasseios){

        em.getTransaction().begin();
        em.remove(em.contains(roteiroPasseios) ? roteiroPasseios : em.merge(roteiroPasseios));
        em.getTransaction().commit();
    }

    public List<Roteiro_PasseiosEntity> buscarTodos(){

        return em.createQuery("SELECT r FROM roteiro_passeios r", Roteiro_PasseiosEntity.class).getResultList();

    }

    public List<Roteiro_PasseiosEntity> buscarPorIdRoteiro(Long idRoteiro){
        return em.createQuery("SELECT r FROM roteiro_passeios r WHERE r.idRoteiro = : idRoteiro", Roteiro_PasseiosEntity.class)
                .setParameter("idRoteiro", idRoteiro).getResultList();
    }


    public List<Roteiro_PasseiosEntity> buscarPorIdPasseio(Long idPasseio){

        return em.createQuery("SELECT r FROM roteiro_passeios r WHERE r.idPasseio = : idPasseio", Roteiro_PasseiosEntity.class)
                .setParameter("idPasseio", idPasseio).getResultList();
    }



}
