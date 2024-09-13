package br.com.erik.cardapio.entity;

public record FoodResponseDTO(Long id, String title, String image, Integer price) {
    public FoodResponseDTO(Food food) {
        this(food.getId(), food.getTitle(), food.getImageUrl(), food.getPrice());
    }
}
