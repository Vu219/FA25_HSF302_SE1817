package hsf302.demo.slot05.demohsfsm2.repository;

import hsf302.demo.slot05.demohsfsm2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUserNameAndPassword(String username, String password);
}
