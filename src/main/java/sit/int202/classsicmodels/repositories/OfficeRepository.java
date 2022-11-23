package sit.int202.classsicmodels.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sit.int202.classsicmodels.entites.Office;

import java.util.List;

public class OfficeRepository {
    private EntityManager entityManager;

    private EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = EntityManagerBuilder.getEntityManager();
        }
        return entityManager;
    }

    public List<Office> findAll() {
        return getEntityManager().createQuery("SELECT o FROM Office o").getResultList();
    }

    public Office find(String officeCode) {
        return getEntityManager().find(Office.class, officeCode);
    }

    public List<Office> findByOfficeCityOrCountry(String cityOfCountry) {
        cityOfCountry = cityOfCountry.toLowerCase() + '%';
        Query query = getEntityManager().createNamedQuery("Office.FIND_BY_CITY_OR_COUNTRY");
        query.setParameter("city", cityOfCountry);
        query.setParameter("country", cityOfCountry);
        return query.getResultList();
    }

    public boolean insert(Office office) {
        try {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(office);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean delete(String officeCode) {
        entityManager = getEntityManager();
        Office office = find(officeCode);
        if (office != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(office);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    public boolean delete(Office office) {
        if (office != null) {
            entityManager = getEntityManager();
            if (entityManager.contains(office)) {
                entityManager.getTransaction().begin();
                entityManager.remove(office);
                entityManager.getTransaction().commit();
                return true;
            } else {
                return delete(office.getOfficeCode());
            }
        }
        return false;
    }

    public EntityTransaction getTransaction() {
        return getEntityManager().getTransaction();
    }
}
