package sit.int202.classsicmodels.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerBuilder {
    public final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("classic-models");
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
