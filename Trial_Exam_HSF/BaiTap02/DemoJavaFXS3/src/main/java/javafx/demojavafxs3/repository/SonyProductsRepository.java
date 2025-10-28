package javafx.demojavafxs3.repository;

import javafx.demojavafxs3.entity.SonyCategories;
import javafx.demojavafxs3.entity.SonyProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface SonyProductsRepository extends JpaRepository<SonyProducts, Long> {
    public SonyProducts findByProductName(String name);
    public boolean existsByProductName(String name);
    public List<SonyProducts> findAllByOrderByCreatedAtDesc();
    public List<SonyProducts> findTop3ByCategoryOrderByStockDesc(SonyCategories categories);
}
