package com.heendoongs.coordibattle.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCoordiVoteRequestDTO {
    private Long memberId;
    private Long winnerCoordiId;
    private Long loserCoordiId;
}