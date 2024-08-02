package com.heendoongs.coordibattle.coordi.service;

import com.heendoongs.coordibattle.coordi.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 코디 서비스
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

public interface CoordiService {
    CoordiDetailsResponseDTO getCoordiDetails(Long memberId, Long coordiId);
    CoordiDetailsResponseDTO likeCoordi(Long memberId, Long coordiId);
    CoordiDetailsResponseDTO updateCoordi(Long memberId, Long coordiId, CoordiDetailsRequestDTO requestDTO);
    void deleteCoordi(Long memberId, Long coordiId);
    Page<CoordiListResponseDTO> getCoordiList(int page, int size);
    Page<CoordiListResponseDTO> getCoordiListWithFilter(CoordiFilterRequestDTO requestDTO);
    List<ClothesResponseDTO> getClothesByType(String type);
}
