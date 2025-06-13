package Model.Repositorio;

import Model.Entidades.UsuarioEntity;


import javax.persistence.EntityManager;
import java.util.List;


public class UsuarioRepository {

    private EntityManager em;

    public UsuarioRepository(EntityManager em) {
        this.em = em;
    }

    public UsuarioEntity findById(Long id){

        return em.find(UsuarioEntity.class,id);
    }


    public void cadastrar(UsuarioEntity usuario) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.persist(usuario);
        em.getTransaction().commit();
    }


    public List<UsuarioEntity> buscarTodos(){
        return em.createQuery("SELECT u FROM Usuario u", UsuarioEntity.class).getResultList();
    }


    public List<UsuarioEntity> buscarPorNomeInicial(String prefixo){

        return em.createQuery("SELECT u FROM Usuario u WHERE u.nome LIKE :prefixo", UsuarioEntity.class)
                .setParameter("prefixo", prefixo + "%")
                .getResultList();
    }

    public void atualizar(UsuarioEntity usuario){

        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }

    public void remover(UsuarioEntity usuario){

        em.getTransaction().begin();
        em.remove(em.contains(usuario)? usuario : em.merge(usuario));
        em.getTransaction().commit();
   }
}
