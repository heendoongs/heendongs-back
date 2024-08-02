package com.heendoongs.coordibattle.battle.service;

import com.heendoongs.coordibattle.battle.dto.BannerResponseDTO;
import com.heendoongs.coordibattle.battle.dto.BattleTitleResponseDTO;
import com.heendoongs.coordibattle.battle.dto.BattleResponseDTO;
import com.heendoongs.coordibattle.member.dto.MemberCoordiVoteRequestDTO;

import java.util.List;

/**
 * 배틀 서비스
 * @author 남진수
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.27  	남진수       최초 생성
 * 2024.07.27   남진수       getBattleCoordies 메소드 추가
 * 2024.07.28   남진수       postBattleResult 메소드 추가
 * 2024.07.29   남진수       getBattleCoordies 서비스에서 battleId 받아오도록 수정
 * 2024.07.31   임원정       getCurrentBattles 메소드 추가
 * </pre>
 */

public interface BattleService {
    List<BattleResponseDTO> getBattleCoordies(Long memberId);
    BattleResponseDTO postBattleResult(MemberCoordiVoteRequestDTO memberCoordiVoteRequestDTO);
    List<BannerResponseDTO> getCurrentBattles();
    List<BattleTitleResponseDTO> getBattleTitles();
}
