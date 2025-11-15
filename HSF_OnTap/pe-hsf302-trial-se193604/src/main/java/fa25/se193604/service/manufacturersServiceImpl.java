package fa25.se193604.service;

import fa25.se193604.entity.manufacturers;
import fa25.se193604.repository.ManufacturersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class manufacturersServiceImpl implements manufacturersService {
    @Autowired
    ManufacturersRepository manufacturersRepository;


    @Override
    public boolean exitsManufacturersByName(String name) {
        return manufacturersRepository.findByManufacturerName(name) != null;
    }

    @Override
    public boolean addManufacturers(manufacturers manu) {
        return manufacturersRepository.save(manu) != null;
    }

    @Override
    public manufacturers findManufacturersById(Integer id) {
        return manufacturersRepository.findByManufacturerId(id);
    }
}
