package fa25.se193604.config;

import fa25.se193604.entity.manufacturers;
import fa25.se193604.entity.users;
import fa25.se193604.service.manufacturersService;
import fa25.se193604.service.usersService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {
    private final usersService usersService;
    private final manufacturersService manufacturersService;

    @Override
    public void run(String... args) throws Exception {
        initUsers();
        initComputers();
        initManufacturers();
    }

    private void initUsers() {
        if(usersService.getUser("admin@laptopshop.com", "@1") == null) {
            users user = new users();
            user.setEmail("admin@laptopshop.com");
            user.setPassword("@1");
            user.setRole("Admin");
            usersService.addUser(user);
        }
        if(usersService.getUser("staff@laptopshop.com", "@2") == null) {
            users user = new users();
            user.setEmail("staff@laptopshop.com");
            user.setPassword("@2");
            user.setRole("Staff");
            usersService.addUser(user);
        }
        if(usersService.getUser("member@laptopshop.com", "@3") == null) {
            users user = new users();
            user.setEmail("member@laptopshop.com");
            user.setPassword("@3");
            user.setRole("Member");
            usersService.addUser(user);
        }
    }

    private void initManufacturers() {
        if(!manufacturersService.exitsManufacturersByName("Dell")) {
            manufacturers  manufacturer = new manufacturers();
            manufacturer.setManufacturerName("Dell");
            manufacturer.setCountry("USA");
            manufacturersService.addManufacturers(manufacturer);
        }
        if(!manufacturersService.exitsManufacturersByName("Lenovo")) {
            manufacturers  manufacturer = new manufacturers();
            manufacturer.setManufacturerName("Lenovo");
            manufacturer.setCountry("China");
            manufacturersService.addManufacturers(manufacturer);
        }
        if(!manufacturersService.exitsManufacturersByName("HP")) {
            manufacturers  manufacturer = new manufacturers();
            manufacturer.setManufacturerName("HP");
            manufacturer.setCountry("USA");
            manufacturersService.addManufacturers(manufacturer);
        }
    }

    private void initComputers() {
    }
}
