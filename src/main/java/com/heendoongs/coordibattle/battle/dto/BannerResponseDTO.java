package com.heendoongs.coordibattle.battle.dto;

import lombok.*;

import java.time.LocalDate;

/**
 * 배너 Response DTO
 * @author 임원정
 * @since 2024.07.26
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	임원정       최초 생성
 * 2024.07.31   임원정       필드 수정(periodType 추가)
 * </pre>
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BannerResponseDTO {
    private Long battleId;
    private String battleTitle;
    private String bannerImageURL;
    private LocalDate startDate;
    private LocalDate endDate;
    private Character periodType; // "C" : 옷입히기 또는 "V" : 투표하기
}