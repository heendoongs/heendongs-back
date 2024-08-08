package com.heendoongs.coordibattle.member.repository;

import com.heendoongs.coordibattle.member.domain.MemberCoordiVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * 멤버코디투표 레포지토리
 * @author 남진스
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	남진수       최초 생성
 * 2024.07.28   남진수       findByMemberIdAndCoordiId 메소드 추가
 * 2024.08.02   남진수       deleteAllByCoordiId 메소드 추가
 * </pre>
 */

@Repository
public interface MemberCoordiVoteRepository extends JpaRepository<MemberCoordiVote, Long>{
    Optional<MemberCoordiVote> findByMemberIdAndCoordiId(Long memberId, Long coordiId);
    void deleteAllByCoordiId(Long coordiId);
}
