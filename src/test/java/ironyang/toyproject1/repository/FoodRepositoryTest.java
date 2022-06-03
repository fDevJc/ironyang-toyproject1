package ironyang.toyproject1.repository;

import ironyang.toyproject1.domain.Food;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FoodRepositoryTest {
    @Autowired
    FoodRepository foodRepository;

    @Test
    void foodSaveAndFind() {
        //given
        Food food = Food.builder()
                .name("떡볶이")
                .price(15_000)
                .build();
        //when
        foodRepository.save(food);
        Food foundFood = foodRepository.findById(food.getId()).get();

        //then
        assertThat(foundFood.getName()).isEqualTo(food.getName());
        assertThat(foundFood.getPrice()).isEqualTo(food.getPrice());
    }
}