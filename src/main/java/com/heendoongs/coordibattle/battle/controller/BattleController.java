package com.heendoongs.coordibattle.battle.controller;

import com.heendoongs.coordibattle.battle.domain.BattleResponseDto;
import com.heendoongs.coordibattle.battle.service.BattleService;
import com.heendoongs.coordibattle.member.domain.MemberCoordiVoteRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 배틀 컨트롤러
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
 * </pre>
 */

@RestController
@RequiredArgsConstructor
public class BattleController {

    private final BattleService battleService;

    @GetMapping("/battle")
    public ResponseEntity<List<BattleResponseDto>> getBattleCoordies(@RequestParam Long battleId, @RequestParam Long memberId) {
        List<BattleResponseDto> responseDtos = battleService.getBattleCoordies(battleId, memberId);
        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping("/battle")
    public ResponseEntity<BattleResponseDto> postBattleResult(@RequestBody MemberCoordiVoteRequestDto memberCoordiVoteRequestDto) {
        BattleResponseDto battleResponseDto = battleService.postBattleResult(memberCoordiVoteRequestDto);
        return ResponseEntity.ok(battleResponseDto);
    }

}
