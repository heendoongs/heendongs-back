package com.heendoongs.coordibattle.battle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 배틀 필터용 제목 Response DTO
 * @author 임원정
 * @since 2024.08.01
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.01  	임원정       최초 생성
 * </pre>
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BattleTitleResponseDTO {
    private Long battleId;
    private String title;
}
