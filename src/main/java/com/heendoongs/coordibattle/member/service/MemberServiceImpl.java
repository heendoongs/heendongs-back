package com.heendoongs.coordibattle.member.service;

import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.domain.MemberSignUpRequestDTO;
import com.heendoongs.coordibattle.member.domain.MemberUpdateDTO;
import com.heendoongs.coordibattle.member.exception.MemberException;
import com.heendoongs.coordibattle.member.exception.MemberExceptionType;
import com.heendoongs.coordibattle.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public void updateAccount(MemberUpdateDTO memberUpdateDTO, String loginId) throws Exception {
        Member member = memberRepository.findByLoginId(loginId);

        if (member == null) {
            throw new MemberException(MemberExceptionType.NOT_FOUND_MEMBER);
        }

        if (memberUpdateDTO.getPassword() != null) {
            String encodedPassword = bCryptPasswordEncoder.encode(memberUpdateDTO.getPassword());
            member.updatePassword(encodedPassword);
        }
        if (memberUpdateDTO.getNickname() != null) {
            member.updateNickname(memberUpdateDTO.getNickname());
        }
    }

    @Override
    public void deleteAccount(String loginId) throws Exception {

        Member member = memberRepository.findByLoginId(loginId);

        if (member == null) {
            throw new MemberException(MemberExceptionType.NOT_FOUND_MEMBER);
        }

        memberRepository.delete(member);
    }
}
