package sum25.se193604.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.se193604.entity.SonyCategories;
import sum25.se193604.entity.SonyProducts;
import sum25.se193604.repository.SonyCategoriesRepository;
import sum25.se193604.repository.SonyProductsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SonyProductsServiceImpl implements SonyProductsService {
    private SonyProductsRepository sonyProductsRepository;
    private SonyCategoriesRepository sonyCategoriesRepository;

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
        SonyProducts existingProduct = sonyProductsRepository.findById(productId).orElse(null);
        if(existingProduct != null){
            existingProduct.setProductName(product.getProductName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            existingProduct.setCategory(product.getCategory());
            sonyProductsRepository.save(existingProduct);
        }
    }

    @Override
    public List<SonyProducts> getTop3ProductsByCategory() {
        List<SonyProducts> topList = new ArrayList<>();
        List<SonyCategories> categories = sonyCategoriesRepository.findAll();
        for(SonyCategories category : categories){
            topList.addAll(sonyProductsRepository.findTop3ByCategoryOrderByStockDesc(category));
        }
        return topList;
    }
}
