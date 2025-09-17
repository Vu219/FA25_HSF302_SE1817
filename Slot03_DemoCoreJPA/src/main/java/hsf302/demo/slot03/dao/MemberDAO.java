package hsf302.demo.slot03.dao;

import hsf302.demo.slot03.model.MemberEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MemberDAO {
    private static EntityManager em;
    private static EntityManagerFactory emf;

    //Hàm khởi tạo
    public MemberDAO(String JpaName) {
        emf = Persistence.createEntityManagerFactory(JpaName);
        em = emf.createEntityManager();
    }

    public boolean addMember(MemberEntity member) {
        try {
            em.getTransaction().begin();
            em.persist(member);
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
