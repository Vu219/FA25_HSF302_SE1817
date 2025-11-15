package fa25.se193604.repository;

import fa25.se193604.entity.manufacturers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturersRepository extends JpaRepository<manufacturers, Integer> {
    public manufacturers findByManufacturerName(String name);
    public manufacturers findByManufacturerId(Integer id);
}
