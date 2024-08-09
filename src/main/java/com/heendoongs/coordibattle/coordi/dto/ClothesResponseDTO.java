package com.heendoongs.coordibattle.coordi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 옷 아이템 반환 DTO
 * @author 임원정
 * @since 2024.07.26
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
public class ClothesResponseDTO {
    private Long clothId;
    private String type;    // { 상의 : 'Top', 하의 : 'Bottom', 신발 : 'Shoe' }
    private String clothImageURL;
}
