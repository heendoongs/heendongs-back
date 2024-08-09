package com.heendoongs.coordibattle.coordi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 코디 리스트 Response DTO (모든 배틀)
 * @author 임원정
 * @since 2024.07.26
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	임원정       최초 생성
 * 2024.08.01   임원정       DTO 이름 변경(RankingOrderCoordiListResponseDTO -> CoordiListResponseDTO)
 * </pre>
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordiListResponseDTO {
    private Long coordiId;
    private String coordiTitle;
    private String coordiImage;
    private String nickname;
    private Long voteCount;
}