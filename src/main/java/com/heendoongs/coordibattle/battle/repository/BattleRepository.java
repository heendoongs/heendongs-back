package com.heendoongs.coordibattle.battle.repository;

import com.heendoongs.coordibattle.battle.domain.Battle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * 배틀 레포지토리
 * @author 남진수
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.29  	남진수       최초 생성
 * 2024.07.29   남진수       findBattleIdByDate 메소드 추가
 * 2024.07.30   임원정       메소드 이름 변경(Voting, Coording), findById 메소드 추가
 * 2024.08.01   임원정       findAll 메소드 추가
 * </pre>
 */

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {

    /**
     * 투표 기간인 배틀 반환
     * @param now
     * @return battle id
     */
    @Query("SELECT b.id FROM Battle b WHERE :now between b.voteStartDate and b.voteEndDate")
    Long findVotingBattleIdByDate(LocalDate now);

    /**
     * 옷입히기 기간인 배틀 반환
     * @param now
     * @return battle id
     */
    @Query("SELECT b.id FROM Battle b WHERE :now between b.coordiStartDate and b.coordiEndDate")
    Long findCoordingBattleIdByDate(LocalDate now);

    /**
     * battle id로 배틀 반환
     * @param id
     * @return
     */
    Optional<Battle> findById(Long id);

    /**
     * 모든 배틀 리스트 반환
     * @return
     */
    @Query("SELECT b FROM Battle b ORDER BY b.coordiStartDate")
    List<Battle> findAll();
}
