package ironyang.toyproject1.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FoodTest {

    @Test
    void update() {
        //given
        Food food = Food.builder()
                .name("떡볶이")
                .price(15_000)
                .build();
        Food updateFood = Food.builder()
                .name("불맛 떡볶이")
                .build();
        //when
        food.update(updateFood);

        //then
        assertThat(food.getName()).isEqualTo(updateFood.getName());
    }
}