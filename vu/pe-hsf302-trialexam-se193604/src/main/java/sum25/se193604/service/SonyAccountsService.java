package sum25.se193604.service;

import org.springframework.stereotype.Service;
import sum25.se193604.entity.SonyAccounts;

@Service
public interface SonyAccountsService {
    boolean add(SonyAccounts user);
    SonyAccounts getAccount(String phone, String password);
}
