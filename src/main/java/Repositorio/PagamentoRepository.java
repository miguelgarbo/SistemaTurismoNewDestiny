package Repositorio;

import Entidades.PagamentoEntity;

import javax.persistence.EntityManager;

public class PagamentoRepository {

    private EntityManager em;

    public PagamentoRepository(EntityManager em) {
        this.em = em;
    }

    public PagamentoEntity findById(Long id){

        return em.find(PagamentoEntity.class,id);
    }

    public void cadastrar(PagamentoEntity pagamento){
        em.getTransaction().begin();
        em.persist(pagamento);
        em.getTransaction().commit();
    }

    public void atualizar(PagamentoEntity pagamento){
        em.getTransaction().begin();
        em.merge(pagamento);
        em.getTransaction().commit();
    }

    public void remover(PagamentoEntity pagamento){

        em.getTransaction().begin();
        em.remove(em.contains(pagamento)? pagamento : em.merge(pagamento));
        em.getTransaction().commit();
    }

}
