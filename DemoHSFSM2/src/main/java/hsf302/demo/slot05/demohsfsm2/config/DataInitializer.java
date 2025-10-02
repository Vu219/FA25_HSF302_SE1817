package hsf302.demo.slot05.demohsfsm2.config;

import hsf302.demo.slot05.demohsfsm2.entity.Role;
import hsf302.demo.slot05.demohsfsm2.entity.User;
import hsf302.demo.slot05.demohsfsm2.service.RoleService;
import hsf302.demo.slot05.demohsfsm2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if(roleService.findByRoleName("admin") == null) {
            Role role1 = new Role();
            role1.setRoleName("admin");
            roleService.addRole(role1);

            Role role = new Role();
            role.setRoleName("user");
            roleService.addRole(role);
        }

        if(userService.getUser("admin", "admin") == null) {
            User user = new User();
            Role userRole = roleService.findByRoleName("admin");
            user.setUsername("admin");
            user.setPassword("admin");
            user.setRole(userRole);
            userService.addUser(user);

            User user1 = new User();
            user1.setUsername("user");
            user1.setPassword("user");
            user1.setRole(roleService.findByRoleName("user"));
            userService.addUser(user1);
        }

    }
}
