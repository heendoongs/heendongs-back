package com.heendoongs.coordibattle.member.dto;

import com.heendoongs.coordibattle.coordi.dto.CoordiListResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberMyClosetResponseDTO {
    private String nickname;
    private Page<CoordiListResponseDTO> coordiList;
}
