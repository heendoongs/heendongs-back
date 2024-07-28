package com.heendoongs.coordibattle.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberCoordiVoteResponseDto {
    private Long memberId;
    private Long coordiId;
    private Character liked;
}