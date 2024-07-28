package com.heendoongs.coordibattle.coordi.controller;

import com.heendoongs.coordibattle.coordi.domain.CoordiDetailsRequestDto;
import com.heendoongs.coordibattle.coordi.domain.CoordiDetailsResponseDto;
import com.heendoongs.coordibattle.coordi.service.CoordiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 코디 컨트롤러
 * @author 임원정
 * @since 2024.07.26
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.26  	임원정       최초 생성
 * 2024.07.28   남진수       getCoordiDetails 메소드 추가
 * 2024.07.28   남진수       likeCoordi 메소드 추가
 * 2024.07.28   남진수       updateCoordi 메소드 추가
 * 2024.07.28   남진수       deleteCoordi 메소드 추가
 * </pre>
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/coordi")
public class CoordiController {

    private final CoordiService coordiService;

    @GetMapping("/details")
    public ResponseEntity<CoordiDetailsResponseDto> getCoordiDetails(@RequestParam Long coordiId) {
        CoordiDetailsResponseDto coordiDetailsResponseDto = coordiService.getCoordiDetails(coordiId);
        return ResponseEntity.ok(coordiDetailsResponseDto);
    }

    @GetMapping("/like")
    public ResponseEntity<CoordiDetailsResponseDto> likeCoordi(@RequestParam Long memberId, @RequestParam Long coordiId) {
        CoordiDetailsResponseDto coordiDetailsResponseDto = coordiService.likeCoordi(memberId, coordiId);
        return ResponseEntity.ok(coordiDetailsResponseDto);
    }

    @PatchMapping("/update")
    public ResponseEntity<CoordiDetailsResponseDto> updateCoordi(@RequestParam Long memberId, @RequestParam Long coordiId, @RequestBody CoordiDetailsRequestDto requestDto) {
        CoordiDetailsResponseDto coordiDetailsResponseDto = coordiService.updateCoordi(memberId, coordiId, requestDto);
        return ResponseEntity.ok(coordiDetailsResponseDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCoordi(@RequestParam Long memberId, @RequestParam Long coordiId) {
        coordiService.deleteCoordi(memberId, coordiId);
        return ResponseEntity.ok("코디가 삭제되었습니다.");
    }
}
