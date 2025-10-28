package javafx.demojavafxs3.repository;

import javafx.demojavafxs3.entity.SonyAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SonyAccountsRepository extends JpaRepository<SonyAccounts, Integer> {
    public SonyAccounts findByPhoneAndPassword(String phone, String password);
}
