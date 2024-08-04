package com.heendoongs.coordibattle.global.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/token")
@RequiredArgsConstructor
public class JWTController {

    private final JWTService jwtService;

    @PostMapping("/reissue")
    @ResponseStatus(HttpStatus.OK)
    public void reissue(HttpServletRequest request, HttpServletResponse response) {
        jwtService.reissueToken(request, response);
    }
}
