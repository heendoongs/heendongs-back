package com.heendoongs.coordibattle.clothes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClothDetailsResponseDTO {
    private Long clothId;
    private String brand;
    private String productName;
    private Integer price;
    private String clothImageURL;
    private String productURL;
}
