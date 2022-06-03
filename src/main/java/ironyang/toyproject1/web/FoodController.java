package ironyang.toyproject1.web;

import ironyang.toyproject1.domain.Food;
import ironyang.toyproject1.exception.NoSuchFoodException;
import ironyang.toyproject1.service.FoodService;
import ironyang.toyproject1.web.dto.FoodRequestDto;
import ironyang.toyproject1.web.dto.FoodResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/api/foods")
    public ResponseEntity<FoodResponseDto> addFood(@RequestBody FoodRequestDto foodRequestDto) {
        Food food = new Food(foodRequestDto.getName(), foodRequestDto.getPrice());
        Long savedFoodId = foodService.addFood(food);
        FoodResponseDto foodResponseDto = new FoodResponseDto();
        foodResponseDto.setId(savedFoodId);
        return ResponseEntity.created(URI.create("/api/foods/"+savedFoodId)).body(foodResponseDto);
    }

    @GetMapping("/api/foods/{id}")
    public ResponseEntity<FoodResponseDto> findFood(@PathVariable Long id) {
        Food food = foodService.findFood(id);
        FoodResponseDto foodResponseDto = new FoodResponseDto();
        foodResponseDto.setId(food.getId());
        foodResponseDto.setName(food.getName());
        foodResponseDto.setPrice(food.getPrice());
        return ResponseEntity.ok().body(foodResponseDto);
    }

    @ExceptionHandler(NoSuchFoodException.class)
    public ResponseEntity handleNoSuchFoodException(NoSuchFoodException e) {
        return ResponseEntity.badRequest().build();
    }
}
