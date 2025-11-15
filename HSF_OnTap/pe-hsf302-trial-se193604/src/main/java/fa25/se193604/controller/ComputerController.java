package fa25.se193604.controller;

import fa25.se193604.entity.users;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComputerController {

    @GetMapping("/computer")
    public String showComputerPage(HttpSession session) {
        users user = (users) session.getAttribute("user");
        if(user == null) {
            return "redirect:/login";
        } else if(user.getRole().equals("Admin") || user.getRole().equals("Staff")) {
            return "computer";
        } else {
            return "redirect:/403";
        }
    }
}
