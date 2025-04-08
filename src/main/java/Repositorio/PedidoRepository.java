package Repositorio;

import Entidades.PedidoEntity;
import javax.persistence.EntityManager;
import java.util.List;

public class PedidoRepository {

    private EntityManager em;

    public PedidoRepository(EntityManager em){
        this.em = em;
    }

    public PedidoEntity findById(Long id){

        return em.find(PedidoEntity.class,id);
    }
    
    public void cadastrar(PedidoEntity pedido){
        
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
    }
    
    public void atualizar (PedidoEntity pedido){
        em.getTransaction().begin();
        em.merge(pedido);
        em.getTransaction().commit();
    }
    
    public void remover (PedidoEntity pedido){
        em.getTransaction().begin();
        em.remove(em.contains(pedido)? pedido : em.merge(pedido));
        em.getTransaction().commit();
    }
    
    public List<PedidoEntity> buscarTodos(){
        
        return em.createQuery("SELECT p Pedido p", PedidoEntity.class).getResultList();
        
    }

}
