package sum25.se193604.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sum25.se193604.entity.SonyCategories;

@Repository
public interface SonyCategoriesRepository extends JpaRepository<SonyCategories, Integer> {
}
