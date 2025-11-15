package sum25.se193604.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sum25.se193604.entity.SonyProducts;
import sum25.se193604.repository.SonyProductsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SonyProductsServiceImpl implements SonyProductsService {
    private final SonyProductsRepository sonyProductsRepository;

    @Override
    public boolean add(SonyProducts product) {
        return sonyProductsRepository.save(product) != null;
    }

    @Override
    public List<SonyProducts> getAll() {
        return sonyProductsRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public SonyProducts getById(long id) {
        return sonyProductsRepository.findById(id).get();
    }

    @Override
    public void delete(long id) {
        SonyProducts product = sonyProductsRepository.findById(id).orElse(null);
        if(product != null) {
            sonyProductsRepository.delete(product);
        }
    }

    @Override
    public void update(long id, SonyProducts sonyProducts) {
        SonyProducts existingProduct = sonyProductsRepository.findById(id).orElse(null);
        if(existingProduct != null){
            existingProduct.setProductName(sonyProducts.getProductName());
            existingProduct.setPrice(sonyProducts.getPrice());
            existingProduct.setStock(sonyProducts.getStock());
            existingProduct.setCreatedAt(sonyProducts.getCreatedAt());
            sonyProductsRepository.save(existingProduct);
        }
    }
}
