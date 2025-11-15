package javafx.demojavafxs3.service;

import javafx.demojavafxs3.entity.SonyAccounts;
import javafx.demojavafxs3.repository.SonyAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SonyAccountsServiceImpl implements SonyAccountsService {
    @Autowired
    private SonyAccountsRepository sonyAccountsRepository;


    @Override
    public boolean addAccount(SonyAccounts account) {
        return sonyAccountsRepository.save(account) != null;
    }

    @Override
    public SonyAccounts getAccount(String phone, String password) {
        return sonyAccountsRepository.findByPhoneAndPassword(phone, password);
    }
}
