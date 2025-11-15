package fa25.se193604.service;

import fa25.se193604.entity.Accounts;
import org.springframework.stereotype.Service;

@Service
public interface AccountsService {
    boolean add(Accounts user);
    Accounts getAccount(String email, String password);
}
