package fa25.se193604.repository;

import fa25.se193604.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Integer> {
    Accounts findByEmailAndPassword(String email, String password);
}
