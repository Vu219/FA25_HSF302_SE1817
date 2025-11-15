package sum25.se193604.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import sum25.se193604.entity.SonyAccounts;
import sum25.se193604.entity.SonyCategories;
import sum25.se193604.entity.SonyProducts;
import sum25.se193604.service.SonyAccountsService;
import sum25.se193604.service.SonyCategoriesService;
import sum25.se193604.service.SonyProductsService;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataInit implements CommandLineRunner {

    SonyAccountsService sonyAccountsService;
    SonyProductsService sonyProductsService;
    SonyCategoriesService sonyCategoriesService;

    @Override
    public void run(String... args) throws Exception {
        SonyAccounts a1 = new SonyAccounts("0905111111", "@1", 1);
        sonyAccountsService.add(a1);
        SonyAccounts a2 = new SonyAccounts("090522222", "@1", 2);
        sonyAccountsService.add(a2);
        SonyAccounts a3 = new SonyAccounts("0905333333", "@1", 3);
        sonyAccountsService.add(a3);

        SonyCategories b1 = new SonyCategories("HeadPhone", "active");
        sonyCategoriesService.add(b1);
        SonyCategories b2 = new SonyCategories("Cameras", "active");
        sonyCategoriesService.add(b2);
        SonyCategories b3 = new SonyCategories("TVs", "active");
        sonyCategoriesService.add(b3);

        SonyProducts c1 = new SonyProducts("Alpha 1 II - Full-frame Mirrorless", 6000, 3, LocalDate.of(2025, 03, 03), b2);
        sonyProductsService.add(c1);
        SonyProducts c2 = new SonyProducts("Alpha 7C II – Full-frame", 2000, 5, LocalDate.of(2025, 04, 04), b2);
        sonyProductsService.add(c2);
        SonyProducts c3 = new SonyProducts("BRAVIA 8 OLED 4K HDR TV", 2500, 10, LocalDate.of(2025, 01, 01), b3);
        sonyProductsService.add(c3);
        SonyProducts c4 = new SonyProducts("LinkBuds Fit Truly Wireless Noise Canceling", 180, 15, LocalDate.of(2025, 03, 03), b1);
        sonyProductsService.add(c4);
    }

}

