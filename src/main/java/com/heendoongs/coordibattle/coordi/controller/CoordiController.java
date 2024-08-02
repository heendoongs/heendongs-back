package com.heendoongs.coordibattle.coordi.controller;

import com.heendoongs.coordibattle.coordi.dto.CoordiDetailsRequestDTO;
import com.heendoongs.coordibattle.coordi.dto.CoordiDetailsResponseDTO;
import com.heendoongs.coordibattle.coordi.dto.CoordiFilterRequestDTO;
import com.heendoongs.coordibattle.coordi.dto.CoordiListResponseDTO;
import com.heendoongs.coordibattle.coordi.service.CoordiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
 * 2024.07.30   임원정       getCoordiList 메소드 추가
 * 2024.08.01   남진수       getCoordiDetails 파라미터 추가
 * </pre>
 */

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/coordi")
public class CoordiController {

    private final CoordiService coordiService;

    @GetMapping("/details")
    public ResponseEntity<CoordiDetailsResponseDTO> getCoordiDetails(@RequestParam Long memberId, @RequestParam Long coordiId) {
        CoordiDetailsResponseDTO coordiDetailsResponseDTO = coordiService.getCoordiDetails(memberId, coordiId);
        return ResponseEntity.ok(coordiDetailsResponseDTO);
    }

    @GetMapping("/like")
    public ResponseEntity<CoordiDetailsResponseDTO> likeCoordi(@RequestParam Long memberId, @RequestParam Long coordiId) {
        CoordiDetailsResponseDTO coordiDetailsResponseDTO = coordiService.likeCoordi(memberId, coordiId);
        return ResponseEntity.ok(coordiDetailsResponseDTO);
    }

    @PatchMapping("/update")
    public ResponseEntity<CoordiDetailsResponseDTO> updateCoordi(@RequestParam Long memberId, @RequestParam Long coordiId, @RequestBody CoordiDetailsRequestDTO requestDTO) {
        CoordiDetailsResponseDTO coordiDetailsResponseDTO = coordiService.updateCoordi(memberId, coordiId, requestDTO);
        return ResponseEntity.ok(coordiDetailsResponseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCoordi(@RequestParam Long memberId, @RequestParam Long coordiId) {
        coordiService.deleteCoordi(memberId, coordiId);
        return ResponseEntity.ok("코디가 삭제되었습니다.");
    }

    /**
     * 코디 리스트 조회(기본)
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Page<CoordiListResponseDTO>> getCoordiList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        Page<CoordiListResponseDTO> coordiList = coordiService.getCoordiList(page, size);
        return ResponseEntity.ok(coordiList);
    }

    /**
     * 코디 리스트 조회(필터)
     * @param requestDTO
     * @return
     */
    @PostMapping("list/filter")
    public ResponseEntity<Page<CoordiListResponseDTO>> getCoordiListWithFilter(@RequestBody CoordiFilterRequestDTO requestDTO) {
        Page<CoordiListResponseDTO> coordiList = coordiService.getCoordiListWithFilter(requestDTO);
        return ResponseEntity.ok(coordiList);
    }
}
