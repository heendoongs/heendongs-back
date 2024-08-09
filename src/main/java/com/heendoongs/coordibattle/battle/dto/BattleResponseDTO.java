package com.heendoongs.coordibattle.battle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 배틀 Response DTO
 * @author 남진수
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.27  	남진수        최초 생성
 * </pre>
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BattleResponseDTO {
    private Long coordiId;
    private String coordiTitle;
    private String coordiImage;
    private String nickname;
}
