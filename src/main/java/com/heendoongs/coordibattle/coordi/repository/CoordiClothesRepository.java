package com.heendoongs.coordibattle.coordi.repository;

import com.heendoongs.coordibattle.coordi.domain.CoordiClothes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 코디옷 레포지토리
 * @author 남진스
 * @since 2024.08.02
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.02  	남진수       최초 생성
 * 2024.08.02   남진수       deleteAllByCoordiId 메소드 추가
 * </pre>
 */

public interface CoordiClothesRepository extends JpaRepository<CoordiClothes, Long> {
    void deleteAllByCoordiId(Long coordiId);
}
