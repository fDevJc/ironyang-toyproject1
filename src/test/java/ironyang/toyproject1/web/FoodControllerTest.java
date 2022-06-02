package ironyang.toyproject1.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import ironyang.toyproject1.domain.Food;
import ironyang.toyproject1.service.FoodService;
import ironyang.toyproject1.web.dto.FoodRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FoodControllerTest {
    @MockBean
    FoodService foodService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void addFood() throws Exception {
        //given
        given(foodService.addFood(any(Food.class))).willReturn(1L);

        ObjectMapper objectMapper = new ObjectMapper();

        //when & then
        FoodRequestDto foodRequestDto = new FoodRequestDto();
        foodRequestDto.setName("떡볶이");
        foodRequestDto.setPrice(15_000);


        mockMvc.perform(post("/api/foods")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(foodRequestDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}