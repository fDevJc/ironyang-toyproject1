package ironyang.toyproject1.repository;

import ironyang.toyproject1.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void joinAndFindOne() {
        //given
        User user = User.builder()
                .email("email@iron.com")
                .password("password")
                .name("yang")
                .build();

        //when
        userRepository.save(user);
        User foundUser = userRepository.findById(user.getId()).get();

        //then
        assertThat(foundUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(foundUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(foundUser.getName()).isEqualTo(user.getName());

    }

}