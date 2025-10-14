package sum25.se193604.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sum25.se193604.entity.SonyAccounts;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String showHomePage(HttpSession session) {
        SonyAccounts sonvAccounts = (SonyAccounts) session.getAttribute("sonyAccounts");
        if (sonvAccounts == null) {
            return  "redirect:/login";
        } else if (sonvAccounts.getRoleID() == 1 || sonvAccounts.getRoleID() == 2) {
            return "product";
        } else {
            return "redirect:/403";
        }

    }

    public boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("sonyAccounts") != null;
    }
}