package fa25.se193604.service;

import fa25.se193604.entity.Suppliers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SuppliersService {
    boolean add(Suppliers suppliers);
    Suppliers findById(int id);
    List<Suppliers> getAll();
}
