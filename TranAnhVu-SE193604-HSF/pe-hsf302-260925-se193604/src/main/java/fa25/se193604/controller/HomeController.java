package fa25.se193604.controller;


import fa25.se193604.entity.Accounts;
import fa25.se193604.entity.Rices;
import fa25.se193604.entity.Suppliers;
import fa25.se193604.service.RicesService;
import fa25.se193604.service.SuppliersService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HomeController {
    RicesService ricesService;
    SuppliersService suppliersService;

    @GetMapping("/403")
    public String accessDenied() {
        return "error";
    }

    @GetMapping("/home")
    public ModelAndView showHomePage(HttpSession session) {
        Accounts accounts = (Accounts) session.getAttribute("accounts");
        if (accounts == null) {
            return new ModelAndView("redirect:/login");
        } else if (accounts.getRole().equals("Admin") || accounts.getRole().equals("Staff")) {
            ModelAndView modelAndView = new ModelAndView();
            List<Rices> rices = ricesService.getAll();

            modelAndView.addObject("rices", rices);
            modelAndView.addObject("accountsRole", accounts.getRole());

            modelAndView.setViewName("home");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/403");
        }

    }

    @GetMapping("/delete/{productID}")
    public String deleteComputer(HttpSession session, @PathVariable("productID") int productID, RedirectAttributes redirectAttributes) {
        Accounts accounts = (Accounts) session.getAttribute("accounts");
        if (accounts == null) {
            return "redirect:/login";
        } else if (accounts.getRole().equals("Admin")) {
            ricesService.delete(productID);
            redirectAttributes.addFlashAttribute("successMessage", "Rices deleted successfully!");
            return "redirect:/home";
        } else {
            return "redirect:/403";
        }
    }


    @GetMapping("/create")
    public ModelAndView showCreatePage(HttpSession session) {
        Accounts accounts = (Accounts) session.getAttribute("accounts");
        if(accounts == null) {
            return new ModelAndView("redirect:/login");
        }

        if (!accounts.getRole().equals("Admin")) {
            return new ModelAndView("redirect:/403");
        }

        List<Suppliers> suppliers = suppliersService.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("rices", new Rices());
        modelAndView.addObject("suppliers", suppliers);
        modelAndView.setViewName("create");
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(HttpSession session, @Valid @ModelAttribute("rices") Rices rices, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        Accounts accounts = (Accounts) session.getAttribute("accounts");
        if(accounts == null) {
            return "redirect:/login";
        }

        if (!accounts.getRole().equals("Admin")) {
            return "redirect:/403";
        }

        List<Suppliers> suppliers = suppliersService.getAll();
        model.addAttribute("suppliers", suppliers);

        if(bindingResult.hasErrors()) {
            model.addAttribute("rices", rices);
            return "create";
        }

        if (rices.getSuppliers() != null && rices.getSuppliers().getSupplierId() > 0) {
            Suppliers manufacturer = suppliersService.findById(rices.getSuppliers().getSupplierId());
            rices.setSuppliers(manufacturer);
        }

        ricesService.add(rices);
        redirectAttributes.addFlashAttribute("successMessage", "Rices created successfully!");
        return "redirect:/home";
    }


    @GetMapping("/update/{riceId}")
    public ModelAndView showUpdatePage(HttpSession session, @PathVariable("riceId") int riceId) {
        Accounts accounts = (Accounts) session.getAttribute("accounts");
        if(accounts == null) {
            return new ModelAndView("redirect:/login");
        }

        if (!accounts.getRole().equals("Admin")) {
            return new ModelAndView("redirect:/403");
        }

        Rices rices = ricesService.getById(riceId);
        if(rices == null) {
            return new ModelAndView("redirect:/home");
        }

        List<Suppliers> suppliers = suppliersService.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("rices", rices);
        modelAndView.addObject("suppliers", suppliers);
        modelAndView.setViewName("update");
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(HttpSession session, @Valid @ModelAttribute("rices") Rices rices, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        Accounts accounts = (Accounts) session.getAttribute("accounts");
        if(accounts == null) {
            return "redirect:/login";
        }

        if (!accounts.getRole().equals("Admin")) {
            return "redirect:/403";
        }

        List<Suppliers> suppliers = suppliersService.getAll();
        model.addAttribute("suppliers", suppliers);

        if(bindingResult.hasErrors()) {
            model.addAttribute("rices", rices);
            return "update";
        }

        if (rices.getSuppliers() != null && rices.getSuppliers().getSupplierId() > 0) {
            Suppliers manufacturer = suppliersService.findById(rices.getSuppliers().getSupplierId());
            rices.setSuppliers(manufacturer);
        }

        ricesService.update(rices.getRiceId(), rices);
        redirectAttributes.addFlashAttribute("successMessage", "Rices updated successfully!");
        return "redirect:/home";
    }
}
