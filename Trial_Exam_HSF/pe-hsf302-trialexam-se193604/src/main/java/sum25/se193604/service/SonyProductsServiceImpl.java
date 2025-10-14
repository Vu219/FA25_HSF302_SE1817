package sum25.se193604.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.se193604.entity.SonyProducts;
import sum25.se193604.repository.SonyProductsRepository;

import java.util.List;

@Service
public class SonyProductsServiceImpl implements SonyProductsService {
    @Autowired
    private SonyProductsRepository sonyProductsRepository;


    @Override
    public boolean addProduct(SonyProducts product) {
        return sonyProductsRepository.save(product) != null;
    }

    @Override
    public List<SonyProducts> getAllProducts() {
        return sonyProductsRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public boolean existsSonyProductsByName(String name) {
        return sonyProductsRepository.existsByProductName(name);
    }

    @Override
    public SonyProducts getProductById(Long productId) {
        return sonyProductsRepository.findById(productId).get();
    }

    @Override
    public void deleteProduct(Long productId) {
        SonyProducts product = sonyProductsRepository.findById(productId).orElse(null);
        if(product != null){
            sonyProductsRepository.delete(product);
        }
    }

    @Override
    public void updateProduct(Long productId, SonyProducts product) {

    }
}
