package ironyang.toyproject1.web.dto;

import ironyang.toyproject1.domain.Food;
import lombok.Builder;
import lombok.Data;

@Data
public class FoodResponseDto {
    private Long id;
    private String name;
    private int price;

    @Builder
    private FoodResponseDto(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static FoodResponseDto of(Food food) {
        return FoodResponseDto.builder()
                .id(food.getId())
                .name(food.getName())
                .price(food.getPrice())
                .build();
    }
}
