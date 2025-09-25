package hsf302.demo.slot05.demohsfsm2.service;

import hsf302.demo.slot05.demohsfsm2.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public boolean addUser(User user);
    public User getUser(String username, String password);
}
