package ironyang.toyproject1.web.dto;

import lombok.Builder;

public class UserRequestDto {
    private String email;
    private String password;

    @Builder
    private UserRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
