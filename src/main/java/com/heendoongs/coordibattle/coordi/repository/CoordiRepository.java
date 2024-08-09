package com.heendoongs.coordibattle.coordi.repository;

import com.heendoongs.coordibattle.clothes.domain.Clothes;
import com.heendoongs.coordibattle.coordi.domain.Coordi;
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
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.26  	임원정       최초 생성
 * 2024.07.27   남진수       findUnvotedCoordies 메소드 추가
 * 2024.07.30   임원정       findAllByLikesDesc 메소드 추가
 * 2024.08.01   임원정       findAllWithFilterAndOrder 메소드 추가
 * 2024.08.02   임원정       findClothesWithBattleAndType 메소드 추가
 * </pre>
 */

@Repository
public interface CoordiRepository extends JpaRepository<Coordi, Long> {

    /**
     * 투표하지 않은 코디 리스트 조회
     * @param battleId
     * @param memberId
     * @return List<Coordi>
     */
    @Query("SELECT c FROM Coordi c WHERE c.battle.id = :battleId AND c.id NOT IN (SELECT v.coordi.id FROM MemberCoordiVote v WHERE v.member.id = :memberId)")
    List<Coordi> findUnvotedCoordies(Long battleId, Long memberId);

    /**
     * 코디 리스트 조회(기본-랭킹순)
     * @param pageable
     * @return
     */
    @Query("SELECT c " +
            "FROM Coordi c " +
            "LEFT JOIN FETCH c.member m " +
            "ORDER BY (SELECT COUNT(v) FROM MemberCoordiVote v WHERE v.coordi = c AND v.liked = 'Y') DESC, c.createDate DESC")
    Page<Coordi> findAllByLikesDesc(Pageable pageable);

    /**
     * 코디 리스트 필터 적용(배틀별, 최신순, 랭킹순)
     * @param battleId
     * @param order
     * @param pageable
     * @return
     */
    @Query("SELECT c " +
            "FROM Coordi c " +
            "WHERE (:battleId IS NULL OR c.battle.id = :battleId)" +
            "ORDER BY " +
            "CASE WHEN :order = 'RECENT' THEN c.createDate END DESC, " +
            "CASE WHEN :order = 'RANKING' THEN (SELECT COUNT(v) FROM MemberCoordiVote v WHERE v.coordi = c AND v.liked = 'Y') END DESC")
    Page<Coordi> findAllWithFilterAndOrder(Long battleId, String order, Pageable pageable);

    /**
     * 옷 가져오기 (현재 옷 입히기 진행중인 배틀, 종류별)
     * @param type
     * @param battleId
     * @return
     */
    @Query("SELECT cl FROM Clothes cl " +
            "JOIN BattleClothes bcl ON cl.id = bcl.clothes.id " +
            "WHERE bcl.battle.id = :battleId " +
            "AND cl.type = :type")
    List<Clothes> findClothesWithBattleAndType(String type, Long battleId);
}
