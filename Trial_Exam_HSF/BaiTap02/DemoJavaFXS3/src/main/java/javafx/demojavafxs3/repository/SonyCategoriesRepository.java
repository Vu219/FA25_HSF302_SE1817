package javafx.demojavafxs3.repository;

import javafx.demojavafxs3.entity.SonyCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SonyCategoriesRepository extends JpaRepository<SonyCategories, Integer> {
    public SonyCategories findByCateName(String name);
    public SonyCategories findByCateID(Integer id);
}
