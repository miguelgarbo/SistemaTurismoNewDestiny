package Repositorio;

import Model.Entidades.CartaoEntity;
import Model.Entidades.PacoteTuristicoEntity;
import Model.Entidades.UsuarioEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class CartaoRepositorio {
    private EntityManager em;

    public CartaoRepositorio(EntityManager em) {
        this.em = em;
    }

    public CartaoRepositorio(){}


    public CartaoEntity findById(Long id){

        return em.find(CartaoEntity.class,id);
    }

    public void cadastrar(CartaoEntity cartaoEntity){

        em.getTransaction().begin();
        em.persist(cartaoEntity);
        em.getTransaction().commit();
    }

    public List<CartaoEntity> buscarTodosPorUsuario(UsuarioEntity usuario) {
        return em.createQuery(
                        "SELECT c FROM CartaoEntity c WHERE c.usuario = :usuario", CartaoEntity.class)
                .setParameter("usuario", usuario)
                .getResultList();
    }

    /*public List<CartaoEntity> buscarPorTituloInicial(String prefixo){

        return em.createQuery("SELECT u FROM PacoteTuristico u WHERE u.titulo LIKE :prefixo", CartaoEntity.class)
                .setParameter("prefixo", prefixo + "%")
                .getResultList();
    }*/

    public void atualizar(PacoteTuristicoEntity PacoteTuristico){

        em.getTransaction().begin();
        em.merge(PacoteTuristico);
        em.getTransaction().commit();

    }

    public void remover(CartaoEntity cartaoEntity){

        em.getTransaction().begin();
        em.remove(em.contains(cartaoEntity)? cartaoEntity : em.merge(cartaoEntity));
        em.getTransaction().commit();
    }

}


