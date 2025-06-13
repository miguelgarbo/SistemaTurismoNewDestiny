package Model.Repositorio;

import Model.Entidades.PasseioEntity;
import Model.Servicos.CategoriaService;


import javax.persistence.EntityManager;
import java.util.List;


public class PasseioRepository {
    private EntityManager em;

    public PasseioRepository(EntityManager em) {
        this.em = em;
    }

    public PasseioEntity findById(Long id){

        return em.find(PasseioEntity.class,id);
    }

    public void cadastrar(PasseioEntity passeio){

        em.getTransaction().begin();
        em.persist(passeio);
        em.getTransaction().commit();
    }


    public void atualizar (PasseioEntity passeio){

        em.getTransaction().begin();
        em.merge(passeio);
        em.getTransaction().commit();
    }

    public void remover(PasseioEntity passeio){

        em.getTransaction().begin();
        em.remove(em.contains(passeio)? passeio : em.merge(passeio));
        em.getTransaction().commit();

    }
    public List<PasseioEntity> buscarPorTituloInicial(String prefixo){

        return em.createQuery("SELECT p FROM Passeio p WHERE p.titulo LIKE :prefixo", PasseioEntity.class)
                .setParameter("prefixo", prefixo+"%").getResultList();
    }

    public List<PasseioEntity> buscarTodos(){
        return em.createQuery("SELECT p FROM Passeio p", PasseioEntity.class).getResultList();
    }


}
