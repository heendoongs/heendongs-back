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
 * 2024.07.30   임원정       메소드 이름 변겅(Voting, Coording), findById 메소드 추가
 * </pre>
 */

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {
    @Query("SELECT b.id FROM Battle b WHERE :now between b.voteStartDate and b.voteEndDate")
    Long findVotingBattleIdByDate(LocalDate now);

    @Query("SELECT b.id FROM Battle b WHERE :now between b.coordiStartDate and b.coordiEndDate")
    Long findCoordingBattleIdByDate(LocalDate now);

    Optional<Battle> findById(Long id);
}
