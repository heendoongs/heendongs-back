package com.heendoongs.coordibattle.global.jwt;

import com.heendoongs.coordibattle.member.exception.MemberException;
import com.heendoongs.coordibattle.member.exception.MemberExceptionType;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JWTService {

    private final JWTUtil jwtUtil;

    public void reissueToken(HttpServletRequest request, HttpServletResponse response) {

        // refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refresh")) {
                    refresh = cookie.getValue();
                }
            }
        }

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

        String newAccess = jwtUtil.createJwt("access", username, memberId, role, 180000L);
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
