package com.heendoongs.coordibattle.member.service;

import com.heendoongs.coordibattle.coordi.dto.CoordiListResponseDTO;
import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.dto.MemberInfoResponseDTO;
import com.heendoongs.coordibattle.member.dto.MemberMyClosetResponseDTO;
import com.heendoongs.coordibattle.member.dto.MemberSignUpRequestDTO;
import com.heendoongs.coordibattle.member.dto.MemberUpdateDTO;
import org.springframework.data.domain.Page;

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
 * 2024.07.29  	조희정       updateAccount, deleteAccount 메소드 추가
 * </pre>
 */

public interface MemberService {

    /**
     * 회원가입
     * @param memberSignUpRequestDto
     * @throws Exception
     */
    void signUp(MemberSignUpRequestDTO memberSignUpRequestDto) throws Exception;

    /**
     * 회원 수정
     * @param memberUpdateDTO
     * @throws Exception
     */
    void updateAccount(MemberUpdateDTO memberUpdateDTO) throws Exception;

    void deleteAccount(Long memberId) throws Exception;

    Page<CoordiListResponseDTO> getMyCoordiList(int page, int size, Long memberId);

    String getNickname(Long memberId);

    MemberInfoResponseDTO getMyInfo(Long memberId);

}
