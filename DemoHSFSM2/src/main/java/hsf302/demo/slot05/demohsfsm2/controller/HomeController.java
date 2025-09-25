package hsf302.demo.slot05.demohsfsm2.controller;

import hsf302.demo.slot05.demohsfsm2.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String showHomePage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return  "redirect:/login";
        } else if (user.getRole().getRoleName().equals("admin")) {
            return "home";
        } else {
            return "redirect:/403"; // Return the name of the home view (e.g., home.html)

        }

    }
}
