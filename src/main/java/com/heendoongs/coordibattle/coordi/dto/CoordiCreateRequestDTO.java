package com.heendoongs.coordibattle.coordi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
 * 2024.08.03   임원정       Validation 추가
 * 2024.08.04   임원정       memberId 필드 삭제 -> 어노테이션 사용
 * 2024.08.05   임원정       Valid 수정
 * </pre>
 */

@Valid
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordiCreateRequestDTO {
    @Size(min = 1, max = 15, message = "제목은 1자 이상 15자 이하로 작성해 주세요")
    private String title;   // 1자 이상 15자 이하
    @NotNull
    private String coordiImage; // Base64 인코딩 된 String
    @Size(min = 1, message = "흰디의 옷을 입혀주세요!")
    private List<Long> clothIds;
}