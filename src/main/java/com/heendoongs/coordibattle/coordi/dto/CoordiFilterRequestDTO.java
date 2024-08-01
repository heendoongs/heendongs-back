package com.heendoongs.coordibattle.coordi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordiFilterRequestDTO {
    private Long id;
    private String title;
    private Long battleId;
    private LocalDate createDate;
    private Long voteCount;
}


