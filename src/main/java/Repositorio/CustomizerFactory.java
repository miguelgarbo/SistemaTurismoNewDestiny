package Repositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomizerFactory {

    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("nome-da-unidade-de-persistencia");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void fechar() {
        emf.close();
    }
}
