package com.heendoongs.coordibattle.coordi.dto;

import com.heendoongs.coordibattle.clothes.dto.ClothDetailsResponseDTO;
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
public class CoordiDetailsResponseDTO {
    private Long memberId;
    private String nickname;
    private LocalDate createDate;
    private String coordiImage;
    private String coordiTitle;
    private List<ClothDetailsResponseDTO> clothesList;
    private Integer voteCount;
    private Boolean isVotingPeriod;
    private Boolean isCoordiPeriod;
    private Boolean isVoted;
}
