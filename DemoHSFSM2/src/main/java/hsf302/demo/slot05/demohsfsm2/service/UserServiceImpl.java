package hsf302.demo.slot05.demohsfsm2.service;

import hsf302.demo.slot05.demohsfsm2.entity.User;
import hsf302.demo.slot05.demohsfsm2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean addUser(User user) {
        return userRepository.save(user) != null;
    }

    @Override
    public User getUser(String username, String password) {
        return userRepository.findByUserNameAndPassword(username, password);
    }
}
