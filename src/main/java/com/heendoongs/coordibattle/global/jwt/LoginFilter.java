package com.heendoongs.coordibattle.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heendoongs.coordibattle.member.domain.CustomUserDetails;
import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.domain.MemberLoginRequestDTO;
import com.heendoongs.coordibattle.member.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;

/**
 * UsernamePasswordAuthentication 커스텀 필터
 * @author 조희정
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	조희정       최초 생성
 * 2024.07.28  	조희정       attemptAuthentication, successfulAuthentication, unsuccessfulAuthentication 메소드 추가
 * </pre>
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    // username 대신 loginId로 받기
    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.setUsernameParameter("loginId");
    }

    /**
     * 로그인 정보 검증
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        MemberLoginRequestDTO memberLoginRequestDTO;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

            memberLoginRequestDTO = objectMapper.readValue(messageBody, MemberLoginRequestDTO.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(memberLoginRequestDTO.getLoginId());

        String username = memberLoginRequestDTO.getLoginId();
        String password = memberLoginRequestDTO.getPassword();

        // token에 username, password, role 담기
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        // token 검증하기
        return authenticationManager.authenticate(authToken);
    }

    /**
     * 로그인 성공 시 실행 (JWT 발급)
     * @param request
     * @param response
     * @param chain
     * @param authentication
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        // 사용자 로그인 ID
        String username = customUserDetails.getUsername();

        // 사용자 권한 정보
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority grantedAuthority = iterator.next();

        String role = grantedAuthority.getAuthority();
        String token = jwtUtil.createJwt(username, role, 1 * 60 * 60 * 1000L);

//        response.addHeader("Authorization", "Bearer " + token);
        // JSON 문자열 생성
        String jsonResponse = String.format("{\"token\":\"%s\"}", token);

        // 응답 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

    /**
     * 로그인 실패 시 실행
     * @param request
     * @param response
     * @param failed
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
