package sum25.se193604.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sum25.se193604.entity.SonyAccounts;
import sum25.se193604.service.SonyAccountsService;

@Controller
public class LoginController {
    @Autowired
    private SonyAccountsService sonyAccountsService;

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        SonyAccounts sonyAccounts = (SonyAccounts) session.getAttribute("sonyAccounts");
        if (sonyAccounts != null) {
            if (sonyAccounts.getRoleID() == 1 || sonyAccounts.getRoleID() == 2) {
                return "redirect:/product";
            } else {
                return "redirect:/403";
            }
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam("phone") String phone, @RequestParam("password") String password) {
        SonyAccounts sonyAccounts = sonyAccountsService.getAccount(phone, password);
        if (sonyAccounts == null) {
            return "redirect:/login?error";
        }
        session.setAttribute("sonyAccounts", sonyAccounts);

        if (sonyAccounts.getRoleID() == 1 || sonyAccounts.getRoleID() == 2) {
            return "redirect:/product";
        } else {
            return "redirect:/403";
        }
    }

}
