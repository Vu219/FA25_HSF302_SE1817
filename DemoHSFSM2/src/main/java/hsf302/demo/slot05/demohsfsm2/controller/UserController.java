package hsf302.demo.slot05.demohsfsm2.controller;

import hsf302.demo.slot05.demohsfsm2.entity.Role;
import hsf302.demo.slot05.demohsfsm2.entity.User;
import hsf302.demo.slot05.demohsfsm2.service.RoleService;
import hsf302.demo.slot05.demohsfsm2.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final RoleService roleService;

    @GetMapping("/user")
    public ModelAndView showUserPage() {
        List<User> userList = userService.getAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userList);
        modelAndView.setViewName("user");
        return modelAndView; // Return the name of the user view (e.g., user.html)
    }

    @GetMapping("/createuser")
    public ModelAndView createUserPage() {
        List<Role> roles = roleService.getAllRoles();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.addObject("roles", roles);
        modelAndView.setViewName("createuser");
        return modelAndView;
    }

    @PostMapping("/createuser")
    public String createUser(User user) {
        userService.addUser(user);
        return "redirect:/user";
    }

    @GetMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }
}
