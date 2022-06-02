package ironyang.toyproject1.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @Setter
@Entity
public class Food {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;
}
