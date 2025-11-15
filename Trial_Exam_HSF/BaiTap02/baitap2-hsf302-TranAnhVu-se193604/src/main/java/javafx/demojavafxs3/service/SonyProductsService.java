package javafx.demojavafxs3.service;

import javafx.demojavafxs3.entity.SonyProducts;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface SonyProductsService {
    public boolean addProduct(SonyProducts product);
    public List<SonyProducts> getAllProducts();
    public boolean existsSonyProductsByName(String name);
    public SonyProducts getProductById(Long productId);
    public void deleteProduct(Long productId);
    public void updateProduct(Long productId, SonyProducts product);
    public List<SonyProducts> getTop3ProductsByCategory();

}
