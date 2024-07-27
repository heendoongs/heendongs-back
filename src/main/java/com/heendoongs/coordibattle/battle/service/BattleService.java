package com.heendoongs.coordibattle.battle.service;

import com.heendoongs.coordibattle.battle.domain.BattleResponseDto;
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
 * </pre>
 */

public interface BattleService {
    List<BattleResponseDto> getBattleCoordies(Long battleId, Long memberId);
}
