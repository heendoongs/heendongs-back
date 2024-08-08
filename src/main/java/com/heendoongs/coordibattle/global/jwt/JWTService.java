package com.heendoongs.coordibattle.global.jwt;

import com.heendoongs.coordibattle.member.exception.MemberException;
import com.heendoongs.coordibattle.member.exception.MemberExceptionType;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * JWT관련 Service 클래스
 * @author 조희정
 * @since 2024.08.04
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.04  	조희정       최초 생성
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class JWTService {

    private final JWTUtil jwtUtil;

    /**
     * Refresh 토큰을 이용해 Access 토큰 재발급
     * @param request
     * @param response
     */
    public void reissueToken(HttpServletRequest request, HttpServletResponse response) {

        // 쿠키에서 refresh token 받아오기
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refresh")) {
                    refresh = cookie.getValue();
                }
            }
        }

        // Refresh 토큰 존재 여부 확인
        if (refresh == null) {
            throw new MemberException(MemberExceptionType.NO_REFRESH_TOKEN);
        }

        // 토큰 만료여부 체크
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            throw new MemberException(MemberExceptionType.EXPIRED_REFRESH_TOKEN);
        }

        // 토큰이 refresh인지 확인
        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {
            throw new MemberException(MemberExceptionType.INVALID_REFRESH_TOKEN);
        }

        // Access 토큰 재생성
        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);
        Long memberId = jwtUtil.getMemberId(refresh);

        String newAccess = jwtUtil.createJwt("access", username, memberId, role, 86400000L);
        String newRefresh = jwtUtil.createJwt("refresh", username, null, role, 86400000L);

        // 헤더 설정
        response.setHeader("Authorization", newAccess);
        response.addCookie(createCookie("refresh", newRefresh));
    }

    /**
     * 쿠키 생성
     * @param key
     * @param value
     * @return
     */
    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        cookie.setHttpOnly(true);

        return cookie;
    }
}
