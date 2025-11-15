package fa25.se193604.repository;

import fa25.se193604.entity.users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usersRepository extends JpaRepository<users, Integer> {
    public users findByEmailAndPassword(String email, String password);
}
