package ironyang.toyproject1.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import ironyang.toyproject1.domain.Food;
import ironyang.toyproject1.exception.NoSuchFoodException;
import ironyang.toyproject1.service.FoodService;
import ironyang.toyproject1.service.UserService;
import ironyang.toyproject1.web.dto.FoodRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FoodController.class)
class FoodControllerTest {
    @MockBean
    FoodService foodService;
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("음식을 저장한다")
    void addFood() throws Exception {
        //given
        given(foodService.addFood(any(Food.class))).willReturn(1L);

        ObjectMapper objectMapper = new ObjectMapper();

        FoodRequestDto foodRequestDto = new FoodRequestDto();
        foodRequestDto.setName("떡볶이");
        foodRequestDto.setPrice(15_000);

        //when & then
        mockMvc.perform(
                        post("/api/foods")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(foodRequestDto))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("음식을 조회한다")
    void findFood() throws Exception {
        //given
        Long foodId = 1L;
         Food resultFood = Food.builder()
                .name("떡볶이")
                .price(15_000)
                .build();
        given(foodService.findFood(any(Long.class))).willReturn(resultFood);

        //when & then
        mockMvc.perform(
                        get("/api/foods/" + foodId)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("떡볶이"))
                .andExpect(jsonPath("$.price").value(15_000))
                .andDo(print());
    }

    @Test
    @DisplayName("음식 조회에 실패한다")
    void findFood_NoSuchFoodException() throws Exception {
        //given
        Long notExistFoodId = 9999L;
        given(foodService.findFood(any())).willThrow(NoSuchFoodException.class);

        //when & then
        mockMvc.perform(
                        get("/api/foods/" + notExistFoodId)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}