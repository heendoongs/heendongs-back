package com.heendoongs.coordibattle.battle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
 * </pre>
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerResponseDTO {
    private Long id;
    private String bannerImageUrl;
    private String bannerTitle;
    private LocalDate voteStartDate;
    private LocalDate voteEndDate;
    private LocalDate coordiStartDate;
    private LocalDate coordiEndDate;
}