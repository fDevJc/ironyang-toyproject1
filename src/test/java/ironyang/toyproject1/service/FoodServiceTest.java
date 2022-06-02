package ironyang.toyproject1.service;

import ironyang.toyproject1.domain.Food;
import ironyang.toyproject1.exception.NoSuchFoodException;
import ironyang.toyproject1.repository.FoodRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {
    @Mock
    FoodRepository foodRepository;
    @InjectMocks
    FoodService foodService;

    @Test
    void addFoodAndFindFood() {
        //given
        Food resultFood = new Food("떡볶이", 15_000);
        given(foodRepository.save(any(Food.class))).willReturn(resultFood);
        given(foodRepository.findById(any())).willReturn(Optional.of(resultFood));

        //when
        Food paramFood = new Food("떡볶이", 15_000);
        Long savedFoodId = foodService.addFood(paramFood);
        Food foundFood = foodService.findFood(savedFoodId);

        //then
        assertThat(foundFood.getName()).isEqualTo("떡볶이");
        assertThat(foundFood.getPrice()).isEqualTo(15_000);
    }

    @Test
    void addFoodAndFindFood_NoSuchFoodException() {
        //given
        Food resultFood = new Food("떡볶이", 15_000);
        given(foodRepository.save(any(Food.class))).willReturn(resultFood);
        given(foodRepository.findById(any())).willReturn(Optional.empty());

        //when
        Food paramFood = new Food("떡볶이", 15_000);
        Long savedFoodId = foodService.addFood(paramFood);

        //then
        assertThatThrownBy(() -> foodService.findFood(1L))
                .isInstanceOf(NoSuchFoodException.class);
    }
}