package ironyang.toyproject1.repository;

import ironyang.toyproject1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
