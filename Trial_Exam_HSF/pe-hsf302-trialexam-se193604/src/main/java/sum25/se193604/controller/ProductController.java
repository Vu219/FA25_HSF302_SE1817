package sum25.se193604.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import sum25.se193604.entity.SonyAccounts;
import sum25.se193604.entity.SonyCategories;
import sum25.se193604.entity.SonyProducts;
import sum25.se193604.service.SonyCategoriesService;
import sum25.se193604.service.SonyProductsService;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final SonyProductsService sonyProductsService;
    private final SonyCategoriesService sonyCategoriesService;
    private final HomeController homeController;

    @GetMapping("/403")
    public String accessDenied() {
        return "error";
    }

    @GetMapping("/product")
    public ModelAndView showProductPage(HttpSession session) {
        SonyAccounts account = (SonyAccounts) session.getAttribute("sonyAccounts");
        if(account == null) {
            return new ModelAndView("redirect:/login");
        }

        List<SonyProducts> products = sonyProductsService.getAllProducts();
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("products", products);
        modelAndView.addObject("userRole", account.getRoleID());

        if(account.getRoleID() == 1) {
            List<SonyProducts> topProducts = sonyProductsService.getTop3ProductsByCategory();
            modelAndView.addObject("topProducts", topProducts);

            Set<String> uniqueCategories = new TreeSet<>();
            for (SonyProducts product : topProducts) {
                if (product.getCategory() != null) {
                    uniqueCategories.add(product.getCategory().getCateName());
                }
            }
            modelAndView.addObject("uniqueCategories", uniqueCategories);
        }

        modelAndView.setViewName("product");
        return modelAndView;
    }

    @GetMapping("/createProduct")
    public ModelAndView showAddProductPage(HttpSession session) {
        SonyAccounts account = (SonyAccounts) session.getAttribute("sonyAccounts");
        if(account == null) {
            return new ModelAndView("redirect:/login");
        }

        if(account.getRoleID() != 1) {
            return new ModelAndView("redirect:/403");
        }

        List<SonyCategories> categories = sonyCategoriesService.getAllCategories();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", new SonyProducts());
        modelAndView.addObject("categories", categories);
        modelAndView.setViewName("createproduct");
        return modelAndView;
    }

    @PostMapping("/createProduct")
    public String createProduct(HttpSession session, @Valid @ModelAttribute("product") SonyProducts product, BindingResult bindingResult, Model model) {
        SonyAccounts account = (SonyAccounts) session.getAttribute("sonyAccounts");
        if(account == null) {
            return "redirect:/login";
        }

        if(account.getRoleID() != 1) {
            return "redirect:/403";
        }

        List<SonyCategories> categories = sonyCategoriesService.getAllCategories();
        model.addAttribute("categories", categories);

        if(bindingResult.hasErrors()) {
            model.addAttribute("product", product);
            return "createproduct";
        }

        if (product.getCategory() != null && product.getCategory().getCateID() > 0) {
            SonyCategories category = sonyCategoriesService.findSonyCategoriesById(product.getCategory().getCateID());
            product.setCategory(category);
        }

        sonyProductsService.addProduct(product);
        return "redirect:/product";
    }


    @GetMapping("/deleteProduct/{productId}")
    public String deleteProduct(HttpSession session, @PathVariable("productId") Long productId) {
        SonyAccounts account = (SonyAccounts) session.getAttribute("sonyAccounts");
        if(account == null) {
            return "redirect:/login";
        }

        if(account.getRoleID() != 1) {
            return "redirect:/403";
        }

        sonyProductsService.deleteProduct(productId);
        return "redirect:/product";
    }

    @GetMapping("/editProduct/{productId}")
    public ModelAndView showUpdateProductPage(HttpSession session, @PathVariable("productId") Long productId) {
        SonyAccounts account = (SonyAccounts) session.getAttribute("sonyAccounts");
        if(account == null) {
            return new ModelAndView("redirect:/login");
        }

        if(account.getRoleID() != 1) {
            return new ModelAndView("redirect:/403");
        }

        SonyProducts product = sonyProductsService.getProductById(productId);
        if(product == null) {
            return new ModelAndView("redirect:/product");
        }

        List<SonyCategories> categories = sonyCategoriesService.getAllCategories();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", product);
        modelAndView.addObject("categories", categories);
        modelAndView.setViewName("editproduct");
        return modelAndView;
    }

    @PostMapping("/editProduct")
    public String updateProduct(HttpSession session, @Valid @ModelAttribute("product") SonyProducts product, BindingResult bindingResult, Model model) {
        SonyAccounts account = (SonyAccounts) session.getAttribute("sonyAccounts");
        if(account == null) {
            return "redirect:/login";
        }

        if(account.getRoleID() != 1) {
            return "redirect:/403";
        }

        List<SonyCategories> categories = sonyCategoriesService.getAllCategories();
        model.addAttribute("categories", categories);

        if(bindingResult.hasErrors()) {
            model.addAttribute("product", product);
            return "editproduct";
        }

        if (product.getCategory() != null && product.getCategory().getCateID() > 0) {
            SonyCategories category = sonyCategoriesService.findSonyCategoriesById(product.getCategory().getCateID());
            product.setCategory(category);
        }

        sonyProductsService.updateProduct(product.getProductID(), product);
        return "redirect:/product";
    }
}
