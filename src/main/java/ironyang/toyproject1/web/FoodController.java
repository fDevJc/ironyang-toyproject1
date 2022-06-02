package ironyang.toyproject1.web;

import ironyang.toyproject1.domain.Food;
import ironyang.toyproject1.service.FoodService;
import ironyang.toyproject1.web.dto.FoodRequestDto;
import ironyang.toyproject1.web.dto.FoodResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/api/foods")
    public ResponseEntity<FoodResponseDto> addFood(@RequestBody FoodRequestDto foodRequestDto) {
        System.out.println("foodRequestDto = " + foodRequestDto);
        Food food = new Food();
        food.setName(foodRequestDto.getName());
        food.setPrice(foodRequestDto.getPrice());
        Long savedFoodId = foodService.addFood(food);
        FoodResponseDto foodResponseDto = new FoodResponseDto();
        foodResponseDto.setId(savedFoodId);
        return ResponseEntity.created(URI.create("/api/foods/"+savedFoodId)).body(foodResponseDto);
    }
}
