package Repositorio;

import Entidades.HorarioEntity;
import javax.persistence.EntityManager;
import java.util.List;

public class HorarioRepository {

    private EntityManager em;

    public HorarioRepository(EntityManager em){
        this.em = em;
    }

    public HorarioEntity findById (Long id){
        return em.find(HorarioEntity.class,id);
    }

    public void salvar(HorarioEntity horario){
        em.getTransaction().begin();
        em.persist(horario);
        em.getTransaction().commit();
    }

    public List<HorarioEntity> buscarTodos(){
        return em.createQuery("SELECT u FROM Horario u", HorarioEntity.class).getResultList();
    }

    public void atualizar(HorarioEntity horario){

        em.getTransaction().begin();
        em.merge(horario);
        em.getTransaction().commit();
    }
    public void remover(HorarioEntity horario){

        em.getTransaction().begin();
        em.remove(em.contains(horario)? horario : em.merge(horario));
        em.getTransaction().commit();
    }



}
