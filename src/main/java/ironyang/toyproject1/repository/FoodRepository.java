package ironyang.toyproject1.repository;

import ironyang.toyproject1.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
