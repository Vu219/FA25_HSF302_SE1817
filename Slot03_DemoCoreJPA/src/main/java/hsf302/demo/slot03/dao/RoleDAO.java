package hsf302.demo.slot03.dao;

import hsf302.demo.slot03.model.RoleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RoleDAO {
    private EntityManager em;
    private EntityManagerFactory emf;

    public RoleDAO(String jpaName) {
        emf = Persistence.createEntityManagerFactory(jpaName);
        em = emf.createEntityManager();
    }

    public RoleEntity getRoleById(Long roleId) {
        return em.find(RoleEntity.class, roleId);
    }

    public boolean addRole(RoleEntity role) {
        try {
            em.getTransaction().begin();
            em.persist(role);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
