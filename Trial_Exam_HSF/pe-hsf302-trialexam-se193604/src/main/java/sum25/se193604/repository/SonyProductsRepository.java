package sum25.se193604.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sum25.se193604.entity.SonyProducts;

import java.util.List;

@Repository
public interface SonyProductsRepository extends JpaRepository<SonyProducts, Long> {
    public SonyProducts findByProductName(String name);
    public boolean existsByProductName(String name);
    public List<SonyProducts> findAllByOrderByCreatedAtDesc();
}
