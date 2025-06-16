package Model.Repositorio;

import Model.Entidades.CarrinhoEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class CarrinhoRepository {

    private EntityManager em;

    public CarrinhoRepository(EntityManager em) {
        this.em = em;
    }

    public CarrinhoEntity findById(Long id) {
        return em.find(CarrinhoEntity.class, id);
    }

    public void cadastrar(CarrinhoEntity carrinho) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.persist(carrinho);
        em.getTransaction().commit();
    }

    public void atualizar(CarrinhoEntity carrinho) {
        em.getTransaction().begin();
        em.merge(carrinho);
        em.getTransaction().commit();
    }

    public void remover(CarrinhoEntity carrinho) {
        em.getTransaction().begin();
        em.remove(em.contains(carrinho) ? carrinho : em.merge(carrinho));
        em.getTransaction().commit();
    }

    public List<CarrinhoEntity> buscarTodos() {
        return em.createQuery("SELECT c FROM CarrinhoEntity c", CarrinhoEntity.class).getResultList();
    }

    public CarrinhoEntity buscarPorUsuarioId(Long usuarioId) {
        List<CarrinhoEntity> resultado = em.createQuery("SELECT c FROM CarrinhoEntity c WHERE c.usuario.id = :usuarioId", CarrinhoEntity.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();

        return resultado.isEmpty() ? null : resultado.get(0);
    }
}
