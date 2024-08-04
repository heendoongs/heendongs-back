package com.heendoongs.coordibattle.member.domain;

import com.heendoongs.coordibattle.member.dto.MemberLoginRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * UserDetails 커스텀
 * @author 조희정
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	조희정       최초 생성
 * </pre>
 */
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final MemberLoginRequestDTO memberLoginRequestDTO;
    private Long memberId;

    public CustomUserDetails(MemberLoginRequestDTO memberLoginRequestDTO, Long memberId) {
        this.memberLoginRequestDTO = memberLoginRequestDTO;
        this.memberId = memberId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    public Long getMemberId() {
        return memberId;
    }

    @Override
    public String getPassword() {
        return memberLoginRequestDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return memberLoginRequestDTO.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}