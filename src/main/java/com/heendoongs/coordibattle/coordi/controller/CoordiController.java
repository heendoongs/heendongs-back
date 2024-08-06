package com.heendoongs.coordibattle.coordi.controller;

import com.heendoongs.coordibattle.coordi.dto.*;
import com.heendoongs.coordibattle.coordi.service.CoordiService;
import com.heendoongs.coordibattle.global.annotation.MemberId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import okhttp3.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
 * 2024.08.01   임원정       getCoordiListWithFilter 메소드 추가
 * 2024.08.02   임원정       getClothList, uploadCoordi 메소드 추가
 * 2024.08.04   임원정       MemberId 어노테이션 사용하도록 변경
 * </pre>
 */

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/coordi")
public class CoordiController {

    private final CoordiService coordiService;

    @GetMapping("/details")
    public ResponseEntity<CoordiDetailsResponseDTO> getCoordiDetails(@RequestParam Long coordiId, @MemberId Long memberId) {
        CoordiDetailsResponseDTO coordiDetailsResponseDTO = coordiService.getCoordiDetails(memberId, coordiId);
        return ResponseEntity.ok(coordiDetailsResponseDTO);
    }

    @GetMapping("/like")
    public ResponseEntity<CoordiDetailsResponseDTO> likeCoordi(@RequestParam Long coordiId, @MemberId Long memberId) {
        CoordiDetailsResponseDTO coordiDetailsResponseDTO = coordiService.likeCoordi(memberId, coordiId);
        return ResponseEntity.ok(coordiDetailsResponseDTO);
    }

    @PatchMapping("/update")
    public ResponseEntity<CoordiDetailsResponseDTO> updateCoordi(@MemberId Long memberId, @RequestParam Long coordiId, @RequestBody CoordiDetailsRequestDTO requestDTO) {
        CoordiDetailsResponseDTO coordiDetailsResponseDTO = coordiService.updateCoordi(memberId, coordiId, requestDTO);
        return ResponseEntity.ok(coordiDetailsResponseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCoordi(@MemberId Long memberId, @RequestParam Long coordiId) {
        coordiService.deleteCoordi(memberId, coordiId);
        return ResponseEntity.ok("코디가 삭제되었습니다.");
    }


    /**
     * 코디 리스트 조회(기본)
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public ResponseEntity<Page<CoordiListResponseDTO>> getCoordiList (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size
    ) throws Exception {
        Page<CoordiListResponseDTO> coordiList = coordiService.getCoordiList(page, size);
        return ResponseEntity.ok(coordiList);
    }

    /**
     * 코디 리스트 조회(필터)
     * @param requestDTO
     * @return
     * @throws Exception
     */
    @PostMapping("list/filter")
    public ResponseEntity<Page<CoordiListResponseDTO>> getCoordiListWithFilter(@RequestBody CoordiFilterRequestDTO requestDTO) throws Exception {
        Page<CoordiListResponseDTO> coordiList = coordiService.getCoordiListWithFilter(requestDTO);
        return ResponseEntity.ok(coordiList);
    }

    /**
     * 타입별 옷 리스트 가져오기
     * @param type
     * @return
     * @throws Exception
     */
    @GetMapping("clothes")
    public ResponseEntity<List<ClothesResponseDTO>> getClothList (@RequestParam String type) throws Exception {
        List<ClothesResponseDTO> clothList = coordiService.getClothesByType(type);
        return ResponseEntity.ok(clothList);
    }

    /**
     * 코디 업로드
     * @param requestDTO
     * @return
     * @throws Exception
     */
    @PostMapping
    public ResponseEntity<String> uploadCoordi (@Valid @RequestBody CoordiCreateRequestDTO requestDTO, @MemberId Long memberId) throws Exception {
        return coordiService.insertCoordi(requestDTO, memberId)
                ? new ResponseEntity<>("코디 업로드 성공", HttpStatus.OK)
				: new ResponseEntity<>("코디 업로드 실패", HttpStatus.BAD_REQUEST);
    }
}
