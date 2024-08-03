package com.heendoongs.coordibattle.coordi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 코디 업로드 Request DTO
 * @author 임원정
 * @since 2024.08.02
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.02  	임원정       최초 생성
 * </pre>
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordiCreateRequestDTO {
    private Long memberId;
    private String title;
    private String coordiImage; // Base64 encoded string
    private List<Long> clothIds;
}
