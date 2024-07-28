package com.heendoongs.coordibattle.coordi.service;

import com.heendoongs.coordibattle.coordi.domain.CoordiDetailsRequestDto;
import com.heendoongs.coordibattle.coordi.domain.CoordiDetailsResponseDto;

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
 * </pre>
 */

public interface CoordiService {
    CoordiDetailsResponseDto getCoordiDetails(Long coordiId);
    CoordiDetailsResponseDto likeCoordi(Long memberId, Long coordiId);
    CoordiDetailsResponseDto updateCoordi(Long memberId, Long coordiId, CoordiDetailsRequestDto requestDto);
}
