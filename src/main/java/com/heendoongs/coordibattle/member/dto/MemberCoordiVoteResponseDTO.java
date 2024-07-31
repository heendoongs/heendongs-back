package com.heendoongs.coordibattle.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberCoordiVoteResponseDTO {
    private Long memberId;
    private Long coordiId;
    private Character liked;
}