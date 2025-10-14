package hsf302.demo.slot05.demohsfsm2.controller;

import hsf302.demo.slot05.demohsfsm2.entity.User;
import hsf302.demo.slot05.demohsfsm2.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userService.getUser(username, password);
        if (user == null) {
            return "redirect:/login?error"; // Redirect back to login page with error
        }
        session.setAttribute("user", user);
        return "redirect:/user"; // Redirect to home page after successful login
    }
}
