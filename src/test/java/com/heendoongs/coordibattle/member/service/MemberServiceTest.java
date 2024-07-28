package com.heendoongs.coordibattle.member.service;

import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.domain.MemberSignUpRequestDTO;
import com.heendoongs.coordibattle.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    String PASSWORD = "password";

    private void clear(){
        em.flush();
        em.clear();
    }

    private MemberSignUpRequestDTO makeMemberSignUpDTO() {
        return MemberSignUpRequestDTO.builder()
                .loginId("memberId")
                .password(PASSWORD)
                .nickname("name")
                .build();
    }

    @AfterEach
    public void removeMember(){
        SecurityContextHolder.createEmptyContext().setAuthentication(null);
    }
    
    /**
     * 회원가입
     *    회원가입 시 아이디, 비밀번호, 닉네임을 입력하지 않으면 오류
     *    이미 존재하는 아이디가 있으면 오류
     *    이미 존재하는 닉네임이 있으면 오류
     */
    @Test
    public void 회원가입_성공() throws Exception {
        //given
        MemberSignUpRequestDTO memberSignUpRequestDTO = makeMemberSignUpDTO();

        //when
        memberService.signUp(memberSignUpRequestDTO);
        clear();

        //then  TODO : 여기 MEMBEREXCEPTION으로 고치기
        Member member = memberRepository.findById(104L).orElseThrow(() -> new Exception("회원이 없습니다"));
        assertThat(member.getId()).isNotNull();
        assertThat(member.getLoginId()).isEqualTo(memberSignUpRequestDTO.getLoginId());
        assertThat(member.getNickname()).isEqualTo(memberSignUpRequestDTO.getNickname());
    }

    @Test
    public void 회원가입_실패_원인_아이디_닉네임_중복() throws Exception {
        //given
        MemberSignUpRequestDTO MemberSignUpRequestDTO = makeMemberSignUpDTO();
        memberService.signUp(MemberSignUpRequestDTO);
        clear();

        //when, then TODO : MemberException으로 고쳐야 함
        assertThat(assertThrows(Exception.class, () -> memberService.signUp(MemberSignUpRequestDTO)).getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
        assertThat(assertThrows(Exception.class, () -> memberService.signUp(MemberSignUpRequestDTO)).getMessage()).isEqualTo("이미 존재하는 닉네임입니다.");
    }


    @Test
    public void 회원가입_실패_입력하지않은_필드가있으면_오류() throws Exception {
        //given
        MemberSignUpRequestDTO memberSignUpRequestDTO1 = MemberSignUpRequestDTO.builder()
                .loginId(null)
                .password(PASSWORD)
                .nickname("name")
                .build();
        MemberSignUpRequestDTO memberSignUpRequestDTO2 = MemberSignUpRequestDTO.builder()
                .loginId("memberId")
                .password(null)
                .nickname("name")
                .build();
        MemberSignUpRequestDTO memberSignUpRequestDTO3 = MemberSignUpRequestDTO.builder()
                .loginId("memberId")
                .password(PASSWORD)
                .nickname(null)
                .build();

        //when, then
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpRequestDTO1));
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpRequestDTO2));
        assertThrows(Exception.class, () -> memberService.signUp(memberSignUpRequestDTO3));
    }
}