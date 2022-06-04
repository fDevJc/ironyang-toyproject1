package ironyang.toyproject1.web;

import ironyang.toyproject1.domain.User;
import ironyang.toyproject1.service.UserService;
import ironyang.toyproject1.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<User> join(@RequestBody UserRequestDto userRequestDto) {
        User user = userRequestDto.toEntity();
        userService.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
