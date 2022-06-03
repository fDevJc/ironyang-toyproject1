package ironyang.toyproject1.service;

import ironyang.toyproject1.domain.Food;
import ironyang.toyproject1.exception.NoSuchFoodException;
import ironyang.toyproject1.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Transactional
    public Long addFood(Food food) {
        return foodRepository.save(food).getId();
    }

    public Food findFood(Long foodId) {
        return foodRepository.findById(foodId)
                .orElseThrow(() -> new NoSuchFoodException());
    }

    @Transactional
    public Long updateFood(Long id, Food food) {
        Food foundFood = findFood(id);
        foundFood.update(food);
        return foundFood.getId();
    }
}
