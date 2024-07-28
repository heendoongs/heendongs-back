package com.heendoongs.coordibattle.coordi.domain;

import com.heendoongs.coordibattle.clothes.domain.ClothDetailsResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordiDetailsResponseDto {
    private Long memberId;
    private String nickname;
    private LocalDate createDate;
    private String coordiImage;
    private String coordiTitle;
    private List<ClothDetailsResponseDto> clothesList;
    private Integer voteCount;
}
