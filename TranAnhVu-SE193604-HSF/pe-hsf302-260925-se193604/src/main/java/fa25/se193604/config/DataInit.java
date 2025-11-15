package fa25.se193604.config;

import fa25.se193604.entity.Accounts;
import fa25.se193604.entity.Rices;
import fa25.se193604.entity.Suppliers;
import fa25.se193604.service.AccountsService;
import fa25.se193604.service.RicesService;
import fa25.se193604.service.SuppliersService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataInit implements CommandLineRunner {

    AccountsService accountsService;
    RicesService ricesService;
    SuppliersService suppliersService;

    @Override
    public void run(String... args) throws Exception {
        Accounts a1 = new Accounts("admin@ricestore.com", "@1", "Admin");
        accountsService.add(a1);
        Accounts a2 = new Accounts("staff@ricestore.com", "@1", "Staff");
        accountsService.add(a2);
        Accounts a3 = new Accounts("member@ricestore.com", "@1", "Member");
        accountsService.add(a3);

        Suppliers b1 = new Suppliers("Golden Rice Traders", "+1-555-0101", "contact@goldenrice.com");
        suppliersService.add(b1);
        Suppliers b2 = new Suppliers("Premium Grains Ltd", "+1-555-0202", "sales@premiumgrains.com");
        suppliersService.add(b2);
        Suppliers b3 = new Suppliers("Asia Rice Solutions Inc", "+1-555-0303", "info@asiarice.com");
        suppliersService.add(b3);
        Suppliers b4 = new Suppliers("Organic Rice Co", "+1-555-0404", "support@organicrice.com");
        suppliersService.add(b4);

        Rices c1 = new Rices("Jasmine Rice Premium 5kg", "89.99", LocalDateTime.of(2024,01,15 ,9,30, 00), b1);
        ricesService.add(c1);
        Rices c2 = new Rices("Basmati Rice Long Grain", "125.99", LocalDateTime.of(2024,02,20 ,14,15, 00), b2);
        ricesService.add(c2);
        Rices c3 = new Rices("Brown Rice Organic 10kg", "159.99", LocalDateTime.of(2024, 03,10, 11,45,00), b3);
        ricesService.add(c3);
        Rices c4 = new Rices("Sushi Rice Special Grade", "79.99", LocalDateTime.of(2024,04,04, 16,20,00), b4);
        ricesService.add(c4);
    }

}

