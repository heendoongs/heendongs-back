package com.heendoongs.coordibattle.coordi.dto;

import com.heendoongs.coordibattle.clothes.dto.ClothDetailsResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 코디 상세 조회 Response DTO
 * @author 남진수
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	남진수        최초 생성
 * </pre>
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordiDetailsResponseDTO {
    private Long memberId;
    private String nickname;
    private LocalDateTime createDate;
    private String coordiImage;
    private String coordiTitle;
    private List<ClothDetailsResponseDTO> clothesList;
    private Integer voteCount;
    private Boolean isVotingPeriod;
    private Boolean isCoordiPeriod;
    private Boolean isVoted;
}
