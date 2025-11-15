package fa25.se193604.service;

import fa25.se193604.entity.users;
import org.springframework.stereotype.Service;

@Service
public interface usersService {
    public boolean addUser(users user);
    public users getUser(String email, String password);
}
