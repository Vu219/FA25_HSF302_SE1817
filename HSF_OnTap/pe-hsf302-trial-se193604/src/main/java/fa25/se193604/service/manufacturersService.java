package fa25.se193604.service;

import fa25.se193604.entity.manufacturers;
import org.springframework.stereotype.Service;

@Service
public interface manufacturersService {
    public boolean exitsManufacturersByName(String name);
    public boolean addManufacturers(manufacturers manu);
    public manufacturers findManufacturersById(Integer id);
}
