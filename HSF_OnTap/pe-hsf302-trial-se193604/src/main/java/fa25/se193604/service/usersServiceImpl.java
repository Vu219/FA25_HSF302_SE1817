package fa25.se193604.service;

import fa25.se193604.entity.users;
import fa25.se193604.repository.usersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class usersServiceImpl implements usersService {
    private final usersRepository usersRepository;


    @Override
    public boolean addUser(users user) {
        return usersRepository.save(user) != null;
    }

    @Override
    public users getUser(String email, String password) {
        return usersRepository.findByEmailAndPassword(email, password);
    }
}
