package sum25.se193604.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.se193604.entity.SonyAccounts;
import sum25.se193604.repository.SonyAccountsRepository;

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
