package com.heendoongs.coordibattle.coordi.repository;

import com.heendoongs.coordibattle.coordi.domain.Coordi;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 코디 레포지토리
 * @author 임원정
 * @since 2024.07.26
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.26  	임원정       최초 생성
 * </pre>
 */

public interface CoordiRepository extends JpaRepository<Coordi, Long> {
}
