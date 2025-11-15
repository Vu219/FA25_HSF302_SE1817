package fa25.se193604.repository;

import fa25.se193604.entity.Rices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RicesRepository extends JpaRepository<Rices, Integer> {
}
