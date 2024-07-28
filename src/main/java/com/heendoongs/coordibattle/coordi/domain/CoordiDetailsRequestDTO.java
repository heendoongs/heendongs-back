package com.heendoongs.coordibattle.coordi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordiDetailsRequestDTO {
    private Long memberId;
    private Long battleId;
    private String coordiTitle;
    private String coordiImage;
    private LocalDate createDate;
}
