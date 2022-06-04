package ironyang.toyproject1.service;

import ironyang.toyproject1.domain.User;
import ironyang.toyproject1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long add(User user) {
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}
