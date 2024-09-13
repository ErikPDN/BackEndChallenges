package br.com.erik.cardapio.controller;

import br.com.erik.cardapio.entity.Food;
import br.com.erik.cardapio.entity.FoodRequestDTO;
import br.com.erik.cardapio.entity.FoodResponseDTO;
import br.com.erik.cardapio.repository.FoodRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {
    private final FoodRepository repository;

    public FoodController(FoodRepository foodRepository) {
        this.repository = foodRepository;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data) {
        Food newFood = new Food(data);
        repository.save(newFood);

        return;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(FoodResponseDTO::new)
                .toList();
    }
}
