package javafx.demojavafxs3.service;

import javafx.demojavafxs3.entity.SonyAccounts;
import org.springframework.stereotype.Service;


@Service
public interface SonyAccountsService {
    public boolean addAccount(SonyAccounts account);
    public SonyAccounts getAccount(String phone, String password);

}
