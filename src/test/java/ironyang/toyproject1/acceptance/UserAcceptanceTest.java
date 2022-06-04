package ironyang.toyproject1.acceptance;

import ironyang.toyproject1.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class UserAcceptanceTest {
    @Autowired
    ApplicationContext context;

    @Test
    void bean() {
        //given
        UserService userService = (UserService) context.getBean("userService");

        System.out.println("userService.getClass() = " + userService.getClass());
        //when

        //then
    }


    /**
     * TODO 회원가입
     * email, password 를 기입하여 회원가입을 진행한다.
     */
    @Test
    @DisplayName("회원가입")
    void join() {
        //given

        //when

        //then
    }
}
