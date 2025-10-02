package hsf302.demo.slot05.demohsfsm2.service;

import hsf302.demo.slot05.demohsfsm2.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public boolean addUser(User user);
    public User getUser(String username, String password);
    public List<User> getAllUsers();
    public User getUserById(int userId);
    public void deleteUser(int userId);
    public void updateUser(int userId, User user);
}
