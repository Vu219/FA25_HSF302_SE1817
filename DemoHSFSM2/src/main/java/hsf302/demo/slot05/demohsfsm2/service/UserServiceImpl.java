package hsf302.demo.slot05.demohsfsm2.service;

import hsf302.demo.slot05.demohsfsm2.entity.User;
import hsf302.demo.slot05.demohsfsm2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user != null) {
            userRepository.delete(user);
        }
    }

    @Override
    public void updateUser(int userId, User user) {
        User existUser = userRepository.findById(userId).orElse(null);
        if(user != null) {
            existUser.setUsername(user.getUsername());
            existUser.setPassword(user.getPassword());
            existUser.setRole(user.getRole());
            userRepository.save(existUser);
        }
    }
}
