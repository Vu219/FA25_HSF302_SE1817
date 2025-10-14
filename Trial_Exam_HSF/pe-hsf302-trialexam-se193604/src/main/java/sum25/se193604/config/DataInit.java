package sum25.se193604.config;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sum25.se193604.entity.SonyAccounts;
import sum25.se193604.entity.SonyCategories;
import sum25.se193604.entity.SonyProducts;
import sum25.se193604.service.SonyAccountsService;
import sum25.se193604.service.SonyCategoriesService;
import sum25.se193604.service.SonyProductsService;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {
    private final SonyAccountsService sonyAccountsService;
    private final SonyProductsService sonyProductsService;
    private final SonyCategoriesService sonyCategoriesService;

    @Override
    public void run(String... args) throws Exception {
        initAccounts();
        initCategories();
        initProducts();
    }

    private void initAccounts() {
        if (sonyAccountsService.getAccount("0905111111", "@1") == null) {
            SonyAccounts s = new SonyAccounts();
            s.setPhone("0905111111");
            s.setPassword("@1");
            s.setRoleID(1);
            sonyAccountsService.addAccount(s);
        }
        if (sonyAccountsService.getAccount("0905222222", "@1") == null) {
            SonyAccounts s = new SonyAccounts();
            s.setPhone("0905222222");
            s.setPassword("@1");
            s.setRoleID(2);
            sonyAccountsService.addAccount(s);
        }
        if (sonyAccountsService.getAccount("0905333333", "@1") == null) {
            SonyAccounts s = new SonyAccounts();
            s.setPhone("0905333333");
            s.setPassword("@1");
            s.setRoleID(3);
            sonyAccountsService.addAccount(s);
        }
    }

    private void initCategories() {
        if (!sonyCategoriesService.existSonyCategoriesByCateName("HeadPhone")) {
            SonyCategories cate1 = new SonyCategories();
            cate1.setCateName("HeadPhone");
            cate1.setStatus("active");
            sonyCategoriesService.addCategory(cate1);
        } if (!sonyCategoriesService.existSonyCategoriesByCateName("Cameras")) {
            SonyCategories cate2 = new SonyCategories();
            cate2.setCateName("Cameras");
            cate2.setStatus("active");
            sonyCategoriesService.addCategory(cate2);
        } if (!sonyCategoriesService.existSonyCategoriesByCateName("TVs")) {
            SonyCategories cate3 = new SonyCategories();
            cate3.setCateName("TVs");
            cate3.setStatus("active");
            sonyCategoriesService.addCategory(cate3);
        }
    }

    private void initProducts() {
        SonyCategories cameras = sonyCategoriesService.findSonyCategoriesById(2);
        SonyCategories headphones = sonyCategoriesService.findSonyCategoriesById(1);
        SonyCategories tvs = sonyCategoriesService.findSonyCategoriesById(3);

        if(!sonyProductsService.existsSonyProductsByName("Alpha 1 II - Full-frame Mirrorless")){
            SonyProducts product2 = new SonyProducts();
            product2.setProductName("Alpha 1 II - Full-frame Mirrorless");
            product2.setPrice(6000);
            product2.setStock(3);
            product2.setCreatedAt(LocalDateTime.of(2025, 3, 3, 0, 0));
            product2.setCategory(cameras);
            sonyProductsService.addProduct(product2);
            System.out.println("Added product: Alpha 1 II - Full-frame Mirrorless");
        }

        if(!sonyProductsService.existsSonyProductsByName("Alpha 7C II – Full-frame")){
            SonyProducts product3 = new SonyProducts();
            product3.setProductName("Alpha 7C II – Full-frame");
            product3.setPrice(2000);
            product3.setStock(5);
            product3.setCreatedAt(LocalDateTime.of(2025, 4, 4, 0, 0));
            product3.setCategory(cameras);
            sonyProductsService.addProduct(product3);
            System.out.println("Added product: Alpha 7C II – Full-frame");
        }

        if(!sonyProductsService.existsSonyProductsByName("BRAVIA 8 OLED 4K HDR TV")){
            SonyProducts product4 = new SonyProducts();
            product4.setProductName("BRAVIA 8 OLED 4K HDR TV");
            product4.setPrice(2500);
            product4.setStock(10);
            product4.setCreatedAt(LocalDateTime.of(2025, 1, 1, 0, 0));
            product4.setCategory(tvs);
            sonyProductsService.addProduct(product4);
            System.out.println("Added product: BRAVIA 8 OLED 4K HDR TV");
        }

        if(!sonyProductsService.existsSonyProductsByName("LinkBuds Fit Truly Wireless Noise Canceling")){
            SonyProducts product5 = new SonyProducts();
            product5.setProductName("LinkBuds Fit Truly Wireless Noise Canceling");
            product5.setPrice(180);
            product5.setStock(15);
            product5.setCreatedAt(LocalDateTime.of(2025, 3, 3, 0, 0));
            product5.setCategory(headphones);
            sonyProductsService.addProduct(product5);
            System.out.println("Added product: LinkBuds Fit Truly Wireless Noise Canceling");
        }
    }
}
