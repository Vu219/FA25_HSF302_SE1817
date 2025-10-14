package hsf302.demo.slot05.demohsfsm2.controller;

import hsf302.demo.slot05.demohsfsm2.entity.Role;
import hsf302.demo.slot05.demohsfsm2.entity.User;
import hsf302.demo.slot05.demohsfsm2.service.RoleService;
import hsf302.demo.slot05.demohsfsm2.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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
    public ModelAndView showCreateUserPage() {
        List<Role> roles = roleService.getAllRoles();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.addObject("roles", roles);
        modelAndView.setViewName("createuser");
        return modelAndView;
    }

    @PostMapping("/createuser")
    public String createUser(HttpSession session, @Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            List<Role> roles = roleService.getAllRoles();
            model.addAttribute("roles", roles);
            return "createuser";
        }

        if (user.getRole() != null && user.getRole().getRoleID() > 0) {
            Role role = roleService.getRoleById(user.getRole().getRoleID()).orElse(null);
            user.setRole(role);
        } else {
            Role defaultRole = roleService.findByRoleName("user");
            user.setRole(defaultRole);
        }

        userService.addUser(user);
        return "redirect:/user";
    }

    @GetMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }

    @GetMapping("/edituser/{id}")
    public ModelAndView showEditUserPage(@PathVariable int id) {
        User user = userService.getUserById(id);
        List<Role> roles = roleService.getAllRoles();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", roles);
        modelAndView.setViewName("edituser");
        return modelAndView;
    }

    @PostMapping("/edituser")
    public String editUser(User user, @RequestParam("roleID") int roleId) {
        Optional<Role> role = roleService.getRoleById(roleId);
        user.setRole(role.get());
        userService.updateUser(user.getUserID(), user);
        return "redirect:/user";
    }
}
