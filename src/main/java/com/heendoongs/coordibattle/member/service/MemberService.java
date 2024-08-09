package com.heendoongs.coordibattle.member.service;

import com.heendoongs.coordibattle.coordi.dto.CoordiListResponseDTO;
import com.heendoongs.coordibattle.member.dto.MemberInfoResponseDTO;
import com.heendoongs.coordibattle.member.dto.MemberNicknameResponseDTO;
import com.heendoongs.coordibattle.member.dto.MemberSignUpRequestDTO;
import com.heendoongs.coordibattle.member.dto.MemberUpdateRequestDTO;
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
 * 2024.07.31  	조희정       getMyCoordiList, getNickname, getMyInfo 메소드 추가
 * </pre>
 */
public interface MemberService {

    /**
     * 회원 가입
     * @param memberSignUpRequestDto
     * @throws Exception
     */
    void signUp(MemberSignUpRequestDTO memberSignUpRequestDto) throws Exception;

    /**
     * 회원 수정
     * @param memberUpdateRequestDTO
     * @param memberId
     * @throws Exception
     */
    void updateAccount(MemberUpdateRequestDTO memberUpdateRequestDTO, Long memberId) throws Exception;

    /**
     * 회원 삭제
     * @param memberId
     * @throws Exception
     */
    void deleteAccount(Long memberId) throws Exception;

    /**
     * 코디 리스트 조회
     * @param page
     * @param size
     * @param memberId
     * @return
     */
    Page<CoordiListResponseDTO> getMyCoordiList(int page, int size, Long memberId);

    /**
     * 회원 닉네임 조회
     * @param memberId
     * @return
     */
    MemberNicknameResponseDTO getNickname(Long memberId);

    /**
     * 내 정보 조회
     * @param memberId
     * @return
     */
    MemberInfoResponseDTO getMyInfo(Long memberId);

}
