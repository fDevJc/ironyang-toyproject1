package ironyang.toyproject1.service;

import ironyang.toyproject1.domain.Food;
import ironyang.toyproject1.exception.NoSuchFoodException;
import ironyang.toyproject1.repository.FoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {
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
}