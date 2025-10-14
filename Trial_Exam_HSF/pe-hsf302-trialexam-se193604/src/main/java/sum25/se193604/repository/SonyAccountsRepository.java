package sum25.se193604.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sum25.se193604.entity.SonyAccounts;

@Repository
public interface SonyAccountsRepository extends CrudRepository<SonyAccounts, Integer> {
    public SonyAccounts findByPhoneAndPassword(String phone, String password);
}
