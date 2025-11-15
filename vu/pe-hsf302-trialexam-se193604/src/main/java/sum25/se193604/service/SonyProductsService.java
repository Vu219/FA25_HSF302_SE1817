package sum25.se193604.service;

import org.springframework.stereotype.Service;
import sum25.se193604.entity.SonyProducts;

import java.util.List;

@Service
public interface SonyProductsService {
    boolean add(SonyProducts product);
    List<SonyProducts> getAll();
    SonyProducts getById(long id);

    void delete(long id);
    void update(long id, SonyProducts sonyProducts);
}
