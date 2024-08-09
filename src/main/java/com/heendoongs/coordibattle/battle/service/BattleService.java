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
 * 2024.08.01   임원정       getBattleTitles 메소드 추가
 * </pre>
 */

public interface BattleService {
    /**
     * 투표할 코디 반환
     * @param memberId
     * @return List<BattleResponseDTO>
     */
    List<BattleResponseDTO> getBattleCoordies(Long memberId);

    /**
     * 코디 투표 결과 저장
     * @param memberId
     * @param memberCoordiVoteRequestDTO
     * @return BattleResponseDTO
     */
    BattleResponseDTO postBattleResult(Long memberId, MemberCoordiVoteRequestDTO memberCoordiVoteRequestDTO);

    /**
     * 현재 진행 중인 배틀 반환
     * @return
     */
    List<BannerResponseDTO> getCurrentBattles();

    /**
     * 배틀 타이틀 반환
     * @return
     */
    List<BattleTitleResponseDTO> getBattleTitles();
}
