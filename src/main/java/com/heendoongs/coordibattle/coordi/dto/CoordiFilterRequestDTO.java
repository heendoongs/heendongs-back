package com.heendoongs.coordibattle.coordi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordiFilterRequestDTO {
    private Long battleId;
    @
    private String order;
    private int page = 0;
    private int size = 6;
}


