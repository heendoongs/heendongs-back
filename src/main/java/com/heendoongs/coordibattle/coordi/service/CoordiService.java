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
 * 2024.08.01   임원정       getCoordiListWithFilter 메소드 추가
 * 2024.08.02   임원정       getClothesByType 메소드 추가
 * 2024.08.03   임원정       insertCoordi 메소드 추가
 * 2024.08.04   암원정       insertCoordi 메소드 파라미터 변경
 * </pre>
 */

public interface CoordiService {

    /**
     * 코디 상세 조회
     * @param memberId
     * @param coordiId
     * @return CoordiDetailsResponseDTO
     */
    CoordiDetailsResponseDTO getCoordiDetails(Long memberId, Long coordiId);

    /**
     * 코디 좋아요
     * @param memberId
     * @param coordiId
     * @return CoordiDetailsResponseDTO
     */
    CoordiDetailsResponseDTO likeCoordi(Long memberId, Long coordiId);

    /**
     * 코디 수정
     * @param memberId
     * @param coordiId
     * @param requestDTO
     * @return CoordiDetailsResponseDTO
     */
    CoordiDetailsResponseDTO updateCoordi(Long memberId, Long coordiId, CoordiDetailsRequestDTO requestDTO);

    /**
     * 코디 삭제
     * @param memberId
     * @param coordiId
     * @throws Exception
     */
    void deleteCoordi(Long memberId, Long coordiId);

    /**
     * 코디 리스트 (기본)
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    Page<CoordiListResponseDTO> getCoordiList(int page, int size) throws Exception;

    /**
     * 코디 리스트 (필터)
     * @param requestDTO
     * @return
     * @throws Exception
     */
    Page<CoordiListResponseDTO> getCoordiListWithFilter(CoordiFilterRequestDTO requestDTO) throws Exception;

    /**
     * 타입별 옷 리스트
     * @param type
     * @return
     * @throws Exception
     */
    List<ClothesResponseDTO> getClothesByType(String type) throws Exception;

    /**
     * 코디 추가
     * @param requestDTO
     * @param memberId
     * @return
     * @throws Exception
     */
    boolean insertCoordi(CoordiCreateRequestDTO requestDTO, Long memberId) throws Exception;
}
