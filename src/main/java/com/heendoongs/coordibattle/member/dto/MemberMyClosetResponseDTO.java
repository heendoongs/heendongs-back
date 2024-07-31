package com.heendoongs.coordibattle.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberMyClosetResponseDTO {

    private Long memberId;
    private String nickname;

}
