package ironyang.toyproject1.web;

import ironyang.toyproject1.domain.Food;
import ironyang.toyproject1.exception.NoSuchFoodException;
import ironyang.toyproject1.service.FoodService;
import ironyang.toyproject1.web.dto.FoodRequestDto;
import ironyang.toyproject1.web.dto.FoodResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/api/foods")
    public ResponseEntity<FoodResponseDto> addFood(@RequestBody FoodRequestDto foodRequestDto) {
        foodService.addFood(foodRequestDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/foods/{id}")
    public ResponseEntity<FoodResponseDto> findFood(@PathVariable Long id) {
        return ResponseEntity.ok().body(FoodResponseDto.of(foodService.findFood(id)));
    }

    @ExceptionHandler(NoSuchFoodException.class)
    public ResponseEntity handleNoSuchFoodException(NoSuchFoodException e) {
        return ResponseEntity.badRequest().build();
    }
}
