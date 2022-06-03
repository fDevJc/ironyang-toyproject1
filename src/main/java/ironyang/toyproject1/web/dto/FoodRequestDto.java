package ironyang.toyproject1.web.dto;

import ironyang.toyproject1.domain.Food;
import lombok.Data;

@Data
public class FoodRequestDto {
    private Long id;
    private String name;
    private int price;

    public Food toEntity() {
        return Food.builder()
                .name(this.name)
                .price(this.price)
                .build();
    }
}
