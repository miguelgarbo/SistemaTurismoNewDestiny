package Repositorio;
import Entidades.Pacote_PasseiosEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class Pacote_PasseiosRepository {

    private EntityManager em;
    
    public Pacote_PasseiosRepository(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Pacote_PasseiosEntity roteiroPasseios){
        em.getTransaction().begin();
        em.persist(roteiroPasseios);
        em.getTransaction().commit();
    }

    public void atualizar(Pacote_PasseiosEntity roteiroPasseios){
        em.getTransaction().begin();
        em.merge(roteiroPasseios);
        em.getTransaction().commit();
    }

    public void remover(Pacote_PasseiosEntity roteiroPasseios){

        em.getTransaction().begin();
        em.remove(em.contains(roteiroPasseios) ? roteiroPasseios : em.merge(roteiroPasseios));
        em.getTransaction().commit();
    }

    public List<Pacote_PasseiosEntity> buscarTodos(){

        return em.createQuery("SELECT r FROM pacote_passeios r", Pacote_PasseiosEntity.class).getResultList();

    }
    

    public List<Pacote_PasseiosEntity> buscarPoridPacote(Long idPacote){
        return em.createQuery("SELECT r FROM pacote_passeios r WHERE r.idPacote = : idPacote", Pacote_PasseiosEntity.class)
                .setParameter("idPacote", idPacote).getResultList();
    }


    public List<Pacote_PasseiosEntity> buscarPorIdPasseio(Long idPasseio){

        return em.createQuery("SELECT r FROM pacote_passeios r WHERE r.idPasseio = : idPasseio", Pacote_PasseiosEntity.class)
                .setParameter("idPasseio", idPasseio).getResultList();
    }



}
