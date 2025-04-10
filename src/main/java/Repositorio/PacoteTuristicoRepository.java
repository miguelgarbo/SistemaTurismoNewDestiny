package Repositorio;

import Entidades.PacoteTuristicoEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class PacoteTuristicoRepository {

    private EntityManager em;

    public PacoteTuristicoRepository(EntityManager em) {
        this.em = em;
    }

    public PacoteTuristicoRepository(){}

    public PacoteTuristicoEntity findById(Long id){

        return em.find(PacoteTuristicoEntity.class,id);
    }

    public void cadastrar(PacoteTuristicoEntity PacoteTuristico){

        em.getTransaction().begin();
        em.persist(PacoteTuristico);
        em.getTransaction().commit();
    }

    public List<PacoteTuristicoEntity> buscarTodos(){
        return em.createQuery("SELECT p FROM PacoteTuristico p", PacoteTuristicoEntity.class).getResultList();
    }



    public List<PacoteTuristicoEntity> buscarPorTituloInicial(String prefixo){

        return em.createQuery("SELECT u FROM PacoteTuristico u WHERE u.titulo LIKE :prefixo", PacoteTuristicoEntity.class)
                .setParameter("prefixo", prefixo + "%")
                .getResultList();
    }

    public void atualizar(PacoteTuristicoEntity PacoteTuristico){

        em.getTransaction().begin();
        em.merge(PacoteTuristico);
        em.getTransaction().commit();

    }

    public void remover(PacoteTuristicoEntity PacoteTuristico){

        em.getTransaction().begin();
        em.remove(em.contains(PacoteTuristico)? PacoteTuristico : em.merge(PacoteTuristico));
        em.getTransaction().commit();
    }






}
