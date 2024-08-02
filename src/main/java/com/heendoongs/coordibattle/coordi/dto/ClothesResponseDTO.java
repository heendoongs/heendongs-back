package com.heendoongs.coordibattle.coordi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClothesResponseDTO {
    private Long clothId;
    private String type;    // { 상의 : 'Top', 하의 : 'Bottom', 신발 : 'Shoe' }
    private String clothImageURL;
}
