package ironyang.toyproject1.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Food {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;

    @Builder
    private Food(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void update(Food food) {
        this.name = food.getName();
        this.price = food.getPrice();
    }
}
