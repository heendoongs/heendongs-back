package com.heendoongs.coordibattle.member.service;

import com.heendoongs.coordibattle.member.domain.MemberSignUpRequestDTO;
import com.heendoongs.coordibattle.member.domain.MemberUpdateDTO;

/**
 * 멤버 서비스 인터페이스
 * @author 조희정
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.27  	조희정       최초 생성
 * 2024.07.27  	조희정       signUp 메소드 추가
 * </pre>
 */

public interface MemberService {

    /**
     * 회원가입
     * @param memberSignUpRequestDto
     * @throws Exception
     */
    void signUp(MemberSignUpRequestDTO memberSignUpRequestDto) throws Exception;

    void updateAccount(MemberUpdateDTO memberUpdateDTO, String loginId) throws Exception;

    void deleteAccount(String loginId) throws Exception;
}