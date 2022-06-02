package ironyang.toyproject1.repository;

import ironyang.toyproject1.domain.Food;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FoodRepositoryTest {
    @Autowired
    FoodRepository foodRepository;

    @Test
    void foodSaveAndFind() {
        //given
        Food food = new Food();
        food.setName("떡볶이");
        food.setPrice(15_000);
        //when
        foodRepository.save(food);
        Optional<Food> optionalFood = foodRepository.findById(food.getId());
        //then
        assertThat(optionalFood.get().getName()).isEqualTo("떡볶이");
        assertThat(optionalFood.get().getPrice()).isEqualTo(15_000);
    }
}