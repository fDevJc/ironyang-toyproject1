package ironyang.toyproject1.web.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FoodRequestDto {
    private Long id;
    private String name;
    private int price;
}
