package com.heendoongs.coordibattle.member.repository;

import com.heendoongs.coordibattle.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 멤버 레포지토리
 * @author 조희정
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.27  	조희정       최초 생성
 * 2024.07.27  	조희정       existsByLoginId, existsByNickname 메소드 추가
 * 2024.07.28  	조희정       findByLoginId 메소드 추가
 *
 * </pre>
 */

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 이미 존재하는 아이디인지 확인
     * @param loginId
     * @return
     */
    Boolean existsByLoginId(String loginId);

    /**
     * 이미 존재하는 닉네임인지 확인
     * @param nickname
     * @return
     */
    Boolean existsByNickname(String nickname);

    /**
     * 로그인 아이디로 검색
     * @param username
     * @return
     */
    Member findByLoginId(String username);

}
