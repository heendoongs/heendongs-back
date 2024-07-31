package com.heendoongs.coordibattle.battle.controller;

import com.heendoongs.coordibattle.battle.dto.BannerResponseDTO;
import com.heendoongs.coordibattle.battle.dto.BattleResponseDTO;
import com.heendoongs.coordibattle.battle.service.BattleService;
import com.heendoongs.coordibattle.member.domain.MemberCoordiVoteRequestDTO;
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
 * 2024.07.29   남진수       getBattleCoordies 서비스에서 battleId 받아오도록 수정
 * 2024.07.30   임원정       getCurrentBattles 메소드 추가
 * </pre>
 */

@RestController
@RequiredArgsConstructor
public class BattleController {

    private final BattleService battleService;

    @GetMapping("/battle")
    public ResponseEntity<List<BattleResponseDTO>> getBattleCoordies(@RequestParam Long memberId) {
        List<BattleResponseDTO> responseDTOs = battleService.getBattleCoordies(memberId);
        return ResponseEntity.ok(responseDTOs);
    }

    @PostMapping("/battle")
    public ResponseEntity<BattleResponseDTO> postBattleResult(@RequestBody MemberCoordiVoteRequestDTO memberCoordiVoteRequestDTO) {
        BattleResponseDTO battleResponseDTO = battleService.postBattleResult(memberCoordiVoteRequestDTO);
        return ResponseEntity.ok(battleResponseDTO);
    }

    /**
     * 배너 출력
     * @return
     */
    @GetMapping("/banner")
    public ResponseEntity<List<BannerResponseDTO>> getCurrentBattles() {
        List<BannerResponseDTO> banners = battleService.getCurrentBattles();
        return ResponseEntity.ok(banners);
    }

}
