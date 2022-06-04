package ironyang.toyproject1.web.dto;

import ironyang.toyproject1.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String password;

    protected UserRequestDto() {
    }

    @Builder
    private UserRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .build();
    }
}
