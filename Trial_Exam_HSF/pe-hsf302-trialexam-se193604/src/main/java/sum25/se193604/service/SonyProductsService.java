package sum25.se193604.service;

import org.springframework.stereotype.Service;
import sum25.se193604.entity.SonyProducts;

import java.util.List;

@Service
public interface SonyProductsService {
    public boolean addProduct(SonyProducts product);
    public List<SonyProducts> getAllProducts();
    public boolean existsSonyProductsByName(String name);
    public SonyProducts getProductById(Long productId);
    public void deleteProduct(Long productId);
    public void updateProduct(Long productId, SonyProducts product);

}
