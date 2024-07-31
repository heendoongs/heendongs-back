package com.heendoongs.coordibattle.coordi.repository;

import com.heendoongs.coordibattle.coordi.domain.Coordi;
import com.heendoongs.coordibattle.coordi.dto.RankingOrderCoordiListResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
 * 2024.07.27   남진수       findUnvotedCoordies 메소드 추가
 * 2024.07.30   임원정       findAllByLikesDesc 메소드 추가
 * </pre>
 */

@Repository
public interface CoordiRepository extends JpaRepository<Coordi, Long> {
    @Query("SELECT c FROM Coordi c WHERE c.battle.id = :battleId AND c.id NOT IN (SELECT v.coordi.id FROM MemberCoordiVote v WHERE v.member.id = :memberId)")
    List<Coordi> findUnvotedCoordies(Long battleId, Long memberId);

    // 코디 리스트 조회(모든 배틀, 랭킹순)
    @Query("SELECT c FROM Coordi c LEFT JOIN FETCH c.member m ORDER BY (SELECT COUNT(v) FROM MemberCoordiVote v WHERE v.coordi = c AND v.liked = 'Y') DESC, c.createDate DESC")
    Page<Coordi> findAllByLikesDesc(Pageable pageable);

}
