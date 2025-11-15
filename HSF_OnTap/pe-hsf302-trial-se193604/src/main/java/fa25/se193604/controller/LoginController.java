package fa25.se193604.controller;

import fa25.se193604.entity.users;
import fa25.se193604.service.usersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @Autowired
    private usersService usersService;

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String Login(HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password) {
        users user = usersService.getUser(email, password);
        if(user == null) {
            return "redirect:/login?error";
        }

        session.setAttribute("user", user);

        if(user.getRole().equals("Admin") || user.getRole().equals("Staff ")) {
            return "redirect:/computer";
        } else {
            return "redirect:/403";
        }
    }
}
