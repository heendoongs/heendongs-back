package com.heendoongs.coordibattle.coordi.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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
