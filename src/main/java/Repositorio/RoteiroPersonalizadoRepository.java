package Repositorio;

import Entidades.RoteiroPersonalizadoEntity;

import javax.persistence.EntityManager;
import java.util.List;


public class RoteiroPersonalizadoRepository {
    private EntityManager em;

    public RoteiroPersonalizadoRepository(EntityManager em) {
        this.em = em;
    }

    public RoteiroPersonalizadoEntity findById(Long id){

        return em.find(RoteiroPersonalizadoEntity.class,id);
    }

    public void cadastrar(RoteiroPersonalizadoEntity roteiroPersonalizado){
        em.getTransaction().begin();
        em.persist(roteiroPersonalizado);
        em.getTransaction().commit();
    }

    public void atualizar(RoteiroPersonalizadoEntity roteiroPersonalizado){
        em.getTransaction().begin();
        em.merge(roteiroPersonalizado);
        em.getTransaction().commit();
    }

    public void remover(RoteiroPersonalizadoEntity roteiroPersonalizado){

        em.getTransaction().begin();
        em.remove(em.contains(roteiroPersonalizado) ? roteiroPersonalizado : em.merge(roteiroPersonalizado));
        em.getTransaction().commit();
    }

    public List<RoteiroPersonalizadoEntity> buscarPorTituloInicial(String prefixo){

        return em.createQuery("SELECT r FROM RoteiroPersonalizado r WHERE r.titulo LIKE :prefixo", RoteiroPersonalizadoEntity.class)
                .setParameter("prefixo: ", prefixo+ "%").getResultList();
    }

    public List<RoteiroPersonalizadoEntity> buscarTodos(){

        return em.createQuery("SELECT r FROM RoteiroPersonalizado r", RoteiroPersonalizadoEntity.class)
                .getResultList();
    }

}
