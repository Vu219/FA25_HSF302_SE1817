package fa25.se193604.service;

import fa25.se193604.entity.Rices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RicesService {
    boolean add(Rices product);
    List<Rices> getAll();
    Rices getById(int id);

    void delete(int id);
    void update(int id, Rices sonyProducts);
}
