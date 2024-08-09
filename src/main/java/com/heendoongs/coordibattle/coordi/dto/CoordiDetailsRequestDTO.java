package com.heendoongs.coordibattle.coordi.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 코디 상세 조회 Request DTO
 * @author 남진수
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	남진수       최초 생성
 * 2024.08.06   남진수       Validation 추가
 * </pre>
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordiDetailsRequestDTO {
    private Long battleId;
    @Size(min = 1, max = 15, message = "제목은 1자 이상 15자 이하로 작성해 주세요")
    private String coordiTitle;
    private String coordiImage;
    private LocalDateTime createDate;
}
