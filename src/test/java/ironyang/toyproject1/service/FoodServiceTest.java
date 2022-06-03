package ironyang.toyproject1.service;

import ironyang.toyproject1.domain.Food;
import ironyang.toyproject1.exception.NoSuchFoodException;
import ironyang.toyproject1.repository.FoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {
    @Autowired
    EntityManager em;

    @Mock
    FoodRepository foodRepository;
    @InjectMocks
    FoodService foodService;

    Food food;

    @BeforeEach
    void setUp() {
        food = Food.builder()
                .name("떡볶이")
                .price(15_000)
                .build();
    }

    @Test
    void addFoodAndFindFood() {
        //given
        given(foodRepository.save(any(Food.class))).willReturn(food);
        given(foodRepository.findById(any())).willReturn(Optional.of(food));

        //when
        Long savedFoodId = foodService.addFood(food);
        Food foundFood = foodService.findFood(savedFoodId);

        //then
        assertThat(foundFood.getName()).isEqualTo(food.getName());
        assertThat(foundFood.getPrice()).isEqualTo(food.getPrice());
    }

    @Test
    void addFoodAndFindFood_NoSuchFoodException() {
        //given
        Long notExistFoodId = 9999L;
        given(foodRepository.findById(any())).willReturn(Optional.empty());

        //when & then
        assertThatThrownBy(() -> foodService.findFood(notExistFoodId))
                .isInstanceOf(NoSuchFoodException.class);
    }

    @Test
    @DisplayName("상품을 수정한다")
    void updateFood() {
        //given
        Long anyId = 1L;
        given(foodRepository.findById(any())).willReturn(Optional.of(food));

        //when
        Food updateFood = Food.builder()
                .name("불맛 떡볶이")
                .build();
        foodService.updateFood(anyId, updateFood);
        
        //then
        assertThat(food.getName()).isEqualTo(updateFood.getName());
    }
}