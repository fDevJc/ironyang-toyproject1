package ironyang.toyproject1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "USERS")
public class User {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private String email;
    private String password;
}
