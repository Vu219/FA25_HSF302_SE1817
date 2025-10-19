package sum25.se193604.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String showLoginForm(HttpSession session, @RequestParam(value = "error", required = false) String error, Model model) {
        SonyAccounts sonyAccounts = (SonyAccounts) session.getAttribute("sonyAccounts");
        if (sonyAccounts != null) {
            if (sonyAccounts.getRoleID() == 1 || sonyAccounts.getRoleID() == 2) {
                return "redirect:/product";
            } else {
                return "redirect:/403";
            }
        }

        if (error != null) {
            model.addAttribute("errorMessage", "Invalid phone number or password");
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam("phone") String phone, @RequestParam("password") String password, Model model) {
        SonyAccounts sonyAccounts = sonyAccountsService.getAccount(phone, password);
        if (sonyAccounts == null) {
            model.addAttribute("errorMessage", "Invalid phone number or password");
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
