package com.heendoongs.coordibattle.global.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 토큰 재발급 컨트롤러 JWTController
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
@RestController("/token")
@RequiredArgsConstructor
public class JWTController {

    private final JWTService jwtService;

    /**
     * refresh 토큰을 이용해 access 토큰 재발급
     * @param request
     * @param response
     */
    @PostMapping("/reissue")
    @ResponseStatus(HttpStatus.OK)
    public void reissue(HttpServletRequest request, HttpServletResponse response) {
        jwtService.reissueToken(request, response);
    }
}
