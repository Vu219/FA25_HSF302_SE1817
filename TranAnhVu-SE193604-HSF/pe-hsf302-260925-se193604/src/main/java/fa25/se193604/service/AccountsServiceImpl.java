package fa25.se193604.service;

import fa25.se193604.entity.Accounts;
import fa25.se193604.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepository accountsRepository;

    @Override
    public boolean add(Accounts user) {
        return accountsRepository.save(user) != null;
    }

    @Override
    public Accounts getAccount(String email, String password) {
        return accountsRepository.findByEmailAndPassword(email, password);
    }
}
