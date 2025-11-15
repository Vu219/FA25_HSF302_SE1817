package sum25.se193604.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sum25.se193604.entity.SonyAccounts;
import sum25.se193604.repository.SonyAccountsRepository;

@Service
@RequiredArgsConstructor
public class SonyAccountsServiceImpl implements SonyAccountsService {
    private final SonyAccountsRepository accountRepository;

    @Override
    public boolean add(SonyAccounts user) {
        return accountRepository.save(user) != null;
    }

    @Override
    public SonyAccounts getAccount(String phone, String password) {
        return accountRepository.findByPhoneAndPassword(phone, password);
    }
}
