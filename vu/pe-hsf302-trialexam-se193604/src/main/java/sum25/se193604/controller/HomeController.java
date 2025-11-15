package sum25.se193604.controller;


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
import sum25.se193604.entity.SonyAccounts;
import sum25.se193604.entity.SonyCategories;
import sum25.se193604.entity.SonyProducts;
import sum25.se193604.service.SonyCategoriesService;
import sum25.se193604.service.SonyProductsService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HomeController {
    SonyProductsService sonyProductsService;
    SonyCategoriesService sonyCategoriesService;

    @GetMapping("/403")
    public String accessDenied() {
        return "error";
    }

    @GetMapping("/home")
    public ModelAndView showHomePage(HttpSession session) {
        SonyAccounts sonyAccounts = (SonyAccounts) session.getAttribute("sonyAccounts");
        if (sonyAccounts == null) {
            return new ModelAndView("redirect:/login");
        } else if (sonyAccounts.getRoleID() == 1 || sonyAccounts.getRoleID()== 2) {
            ModelAndView modelAndView = new ModelAndView();
            List<SonyProducts> sonyProducts = sonyProductsService.getAll();

            modelAndView.addObject("sonyProducts", sonyProducts);
            modelAndView.addObject("SonyAccountsRole", sonyAccounts.getRoleID());

            modelAndView.setViewName("home");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/403");
        }

    }

    @GetMapping("/delete/{productID}")
    public String deleteComputer(HttpSession session, @PathVariable("productID") int productID, RedirectAttributes redirectAttributes) {
        SonyAccounts sonyAccounts = (SonyAccounts) session.getAttribute("sonyAccounts");
        if (sonyAccounts == null) {
            return "redirect:/login";
        } else if (sonyAccounts.getRoleID() == 1) {
            sonyProductsService.delete(productID);
            redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully!");
            return "redirect:/home";
        } else {
            return "redirect:/403";
        }
    }


    @GetMapping("/create")
    public ModelAndView showCreatePage(HttpSession session) {
        SonyAccounts sonyAccounts = (SonyAccounts) session.getAttribute("sonyAccounts");
        if(sonyAccounts == null) {
            return new ModelAndView("redirect:/login");
        }

        if (sonyAccounts.getRoleID() != 1) {
            return new ModelAndView("redirect:/403");
        }

        List<SonyCategories> sonyCategories = sonyCategoriesService.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sonyProducts", new SonyProducts());
        modelAndView.addObject("sonyCategories", sonyCategories);
        modelAndView.setViewName("create");
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(HttpSession session, @Valid @ModelAttribute("sonyProducts") SonyProducts sonyProducts, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        SonyAccounts sonyAccounts = (SonyAccounts) session.getAttribute("sonyAccounts");
        if(sonyAccounts == null) {
            return "redirect:/login";
        }

        if (sonyAccounts.getRoleID() != 1) {
            return "redirect:/403";
        }

        List<SonyCategories> sonyCategories = sonyCategoriesService.getAll();
        model.addAttribute("sonyCategories", sonyCategories);

        if(bindingResult.hasErrors()) {
            model.addAttribute("sonyProducts", sonyProducts);
            return "create";
        }

        if (sonyProducts.getSonyCategories() != null && sonyProducts.getSonyCategories().getCateID() > 0) {
            SonyCategories manufacturer = sonyCategoriesService.findById(sonyProducts.getSonyCategories().getCateID());
            sonyProducts.setSonyCategories(manufacturer);
        }

        sonyProductsService.add(sonyProducts);
        redirectAttributes.addFlashAttribute("successMessage", "Product created successfully!");
        return "redirect:/home";
    }


    @GetMapping("/update/{productID}")
    public ModelAndView showUpdatePage(HttpSession session, @PathVariable("productID") long productID) {
        SonyAccounts sonyAccounts = (SonyAccounts) session.getAttribute("sonyAccounts");
        if(sonyAccounts == null) {
            return new ModelAndView("redirect:/login");
        }

        if (sonyAccounts.getRoleID() != 1) {
            return new ModelAndView("redirect:/403");
        }

        SonyProducts sonyProducts = sonyProductsService.getById(productID);
        if(sonyProducts == null) {
            return new ModelAndView("redirect:/home");
        }

        List<SonyCategories> sonyCategories = sonyCategoriesService.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sonyProducts", sonyProducts);
        modelAndView.addObject("sonyCategories", sonyCategories);
        modelAndView.setViewName("update");
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(HttpSession session, @Valid @ModelAttribute("sonyProducts") SonyProducts sonyProducts, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        SonyAccounts sonyAccounts = (SonyAccounts) session.getAttribute("sonyAccounts");
        if(sonyAccounts == null) {
            return "redirect:/login";
        }

        if (sonyAccounts.getRoleID() != 1) {
            return "redirect:/403";
        }

        List<SonyCategories> sonyCategories = sonyCategoriesService.getAll();
        model.addAttribute("sonyCategories", sonyCategories);

        if(bindingResult.hasErrors()) {
            model.addAttribute("sonyProducts", sonyProducts);
            return "update";
        }

        if (sonyProducts.getSonyCategories() != null && sonyProducts.getSonyCategories().getCateID() > 0) {
            SonyCategories manufacturer = sonyCategoriesService.findById(sonyProducts.getSonyCategories().getCateID());
            sonyProducts.setSonyCategories(manufacturer);
        }

        sonyProductsService.update(sonyProducts.getProductID(), sonyProducts);
        redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully!");
        return "redirect:/home";
    }
}
