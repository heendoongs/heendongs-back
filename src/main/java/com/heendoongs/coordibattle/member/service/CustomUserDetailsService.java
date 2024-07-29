package com.heendoongs.coordibattle.member.service;

import com.heendoongs.coordibattle.member.domain.CustomUserDetails;
import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.domain.MemberLoginRequestDTO;
import com.heendoongs.coordibattle.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        // DB에서 멤버 조회
        Member memberData = memberRepository.findByLoginId(loginId);

        // 멤버 정보를 찾을 수 없으면 UsernameNotFoundException 예외 발생
        if (memberData == null) {
            throw new UsernameNotFoundException("회원 정보가 없습니다: " + loginId);
        }

        MemberLoginRequestDTO memberLoginRequestDTO = MemberLoginRequestDTO.builder()
                .loginId(memberData.getLoginId())
                .password(memberData.getPassword())
                .build();

        // 멤버 정보 반환
        return new CustomUserDetails(memberLoginRequestDTO);
    }
}