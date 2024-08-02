package com.heendoongs.coordibattle.member.service;

import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.dto.MemberInfoResponseDTO;
import com.heendoongs.coordibattle.member.dto.MemberMyClosetResponseDTO;
import com.heendoongs.coordibattle.member.dto.MemberSignUpRequestDTO;
import com.heendoongs.coordibattle.member.dto.MemberUpdateDTO;
import com.heendoongs.coordibattle.member.exception.MemberException;
import com.heendoongs.coordibattle.member.exception.MemberExceptionType;
import com.heendoongs.coordibattle.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 멤버 서비스 구현체
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
@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원가입
     * @param memberSignUpRequestDTO
     * @throws Exception
     */
    @Override
    public void signUp(MemberSignUpRequestDTO memberSignUpRequestDTO) throws Exception {

        String loginId = memberSignUpRequestDTO.getLoginId();
        String password = memberSignUpRequestDTO.getPassword();
        String nickname = memberSignUpRequestDTO.getNickname();

        // 아이디, 닉네임 중복 여부 확인
        if (memberRepository.existsByLoginId(loginId)) {
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_LOGIN_ID);
        }
        if (memberRepository.existsByNickname(nickname)) {
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_NICKNAME);
        }

        Member member = Member.builder()
                .loginId(loginId)
                .password(bCryptPasswordEncoder.encode(password))
                .nickname(nickname)
                .build();

        // DB에 저장
        memberRepository.save(member);
    }

    @Override
    public void updateAccount(MemberUpdateDTO memberUpdateDTO) throws Exception {
        Member member = memberRepository.findById(memberUpdateDTO.getMemberId())
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        // 닉네임 수정
        if (memberUpdateDTO.getNickname() != null && !member.getNickname().equals(memberUpdateDTO.getNickname())) {
            if (memberRepository.existsByNickname(memberUpdateDTO.getNickname())) {
                throw new MemberException(MemberExceptionType.ALREADY_EXIST_NICKNAME);
            }
            member.updateNickname(memberUpdateDTO.getNickname());
        }

        // 비밀번호 수정
        if (memberUpdateDTO.getPassword() != null) {
            String encodedPassword = bCryptPasswordEncoder.encode(memberUpdateDTO.getPassword());
            member.updatePassword(encodedPassword);
        }
    }

    @Override
    public void deleteAccount(Long memberId) throws Exception {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        memberRepository.delete(member);
    }

    @Override
    public MemberMyClosetResponseDTO getMyCloset(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));


        return MemberMyClosetResponseDTO.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
    }

    @Override
    public MemberInfoResponseDTO getMyInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        return MemberInfoResponseDTO.builder()
                .loginId(member.getLoginId())
                .nickname(member.getNickname())
                .build();
    }

    @Override
    public Member getByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
}
