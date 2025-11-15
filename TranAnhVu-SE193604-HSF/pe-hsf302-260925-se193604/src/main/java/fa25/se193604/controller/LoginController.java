package fa25.se193604.controller;


import fa25.se193604.entity.Accounts;
import fa25.se193604.service.AccountsService;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginController {
    AccountsService accountsService;

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(HttpSession session, @RequestParam(value = "error", required = false) String error, Model model) {
        Accounts accounts = (Accounts) session.getAttribute("accounts");
        if (accounts != null) {
            if (accounts.getRole().equals("Admin") || accounts.getRole().equals("Staff")) {
                return "redirect:/home";
            } else {
                return "redirect:/403";
            }
        }

        if (error != null) {
            model.addAttribute("errorMessage", "Invalid email or password");
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        Accounts accounts = accountsService.getAccount(email, password);
        if (accounts == null) {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "redirect:/login?error";
        }
        session.setAttribute("accounts", accounts);

        if (accounts.getRole().equals("Admin") || accounts.getRole().equals("Staff")) {
            return "redirect:/home";
        } else {
            return "redirect:/403";
        }
    }


}
