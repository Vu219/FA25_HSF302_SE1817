package fa25.se193604.repository;

import fa25.se193604.entity.computers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputersRepository extends JpaRepository<computers, Integer> {
}
