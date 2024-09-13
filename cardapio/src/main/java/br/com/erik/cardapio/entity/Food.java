package br.com.erik.cardapio.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "foods")
@Entity(name = "foods")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String imageUrl;
    private Integer price;

    public Food(FoodRequestDTO foodRequestDTO) {
        this.title = foodRequestDTO.title();
        this.imageUrl = foodRequestDTO.image();
        this.price = foodRequestDTO.price();
    }


}



