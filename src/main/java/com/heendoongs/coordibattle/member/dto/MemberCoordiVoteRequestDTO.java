package com.heendoongs.coordibattle.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 멤버 코디 투표 requestDTO
 * @author 남진수
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	남진수       최초 생성
 * </pre>
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCoordiVoteRequestDTO {
    private Long winnerCoordiId;
    private Long loserCoordiId;
}