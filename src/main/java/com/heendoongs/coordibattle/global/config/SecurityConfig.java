package com.heendoongs.coordibattle.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 스프링 시큐리티 설정
 * @author 조희정
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.27  	조희정       최초 생성
 * </pre>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 비밀번호 암호화
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    /**
     * Security 필터
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // csrf disable
        http.csrf((auth) -> auth.disable());

        // Form 로그인 방식 disable
        http.formLogin((auth) -> auth.disable());

        // http basic 인증 방식 disable
        http.httpBasic((auth) -> auth.disable());

        // 경로별 인가 작업
        http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/signup").permitAll()
                        .anyRequest().authenticated());

        // 세션 설정
        http.sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
