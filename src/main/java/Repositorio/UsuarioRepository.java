package Repositorio;

import Entidades.UsuarioEntity;

import javax.persistence.EntityManager;

public class UsuarioRepository {

    private EntityManager em;

    public UsuarioRepository(EntityManager em) {
        this.em = em;
    }

    public UsuarioEntity findById(Long id){

        return em.find(UsuarioEntity.class,id);
    }


}
