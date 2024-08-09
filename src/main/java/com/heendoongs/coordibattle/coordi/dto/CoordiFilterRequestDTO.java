package com.heendoongs.coordibattle.coordi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 코디 리스트 필터 적용 요청 DTO
 * @author 임원정
 * @since 2024.07.26
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
public class CoordiFilterRequestDTO {
    private Long battleId;
    private String order;
    private int page = 0;
    private int size = 6;
}


