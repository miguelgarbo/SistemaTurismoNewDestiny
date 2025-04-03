package SistemaTurismo;

import Repositorio.CustomizerFactory;
import Repositorio.UsuarioRepository;

import javax.persistence.EntityManager;

public class Main {


    public static void main(String[] args) {

        EntityManager em = CustomizerFactory.getEntityManager();
        UsuarioRepository usuarioRepository = new UsuarioRepository(em);

        System.out.println(usuarioRepository.findById(3L));

    }
}