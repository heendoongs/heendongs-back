package com.heendoongs.coordibattle.global.jwt;

import com.heendoongs.coordibattle.member.domain.CustomUserDetails;
import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.domain.MemberLoginRequestDTO;
import com.heendoongs.coordibattle.member.exception.MemberException;
import com.heendoongs.coordibattle.member.exception.MemberExceptionType;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT 커스텀 필터
 * @author 조희정
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	조희정       최초 생성
 * 2024.07.28  	조희정       doFilterInternal, shouldNotFilter 메소드 추가
 * </pre>
 */
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Authorization 헤더 검증
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: No Token");
            return;
        }

        String token = authorizationHeader.substring(7);

        try {
            // 토큰에서 사용자 정보 추출 및 검증
            String username = jwtUtil.getUsername(token);

            MemberLoginRequestDTO memberLoginRequestDTO = MemberLoginRequestDTO.builder()
                    .loginId(username)
                    .password(null)
                    .build();

            CustomUserDetails customUserDetails = new CustomUserDetails(memberLoginRequestDTO);

            Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

            // 임시 세션에 사용자 등록
            SecurityContextHolder.getContext().setAuthentication(authentication);


        } catch (ExpiredJwtException e) {
            // 토큰이 소멸된 경우
            throw new MemberException(MemberExceptionType.EXPIRED_TOKEN);
        } catch (Exception e) {
            // 올바르지 않은 토큰일 경우
            throw new MemberException(MemberExceptionType.INVALID_TOKEN);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 토큰 검증이 필요하지 않은 페이지
     * @param request
     * @return
     * @throws ServletException
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
//        return path.equals("/") || path.equals("/login") || path.equals("/signup");
        return true;
    }
}
