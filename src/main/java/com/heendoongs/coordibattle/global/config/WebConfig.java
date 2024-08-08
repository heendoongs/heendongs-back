package com.heendoongs.coordibattle.global.config;

import com.heendoongs.coordibattle.global.resolver.CurrentMemberIdArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

/**
 * MemberId를 받아오는 WebConfig
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
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CurrentMemberIdArgumentResolver currentMemberIdArgumentResolver;

    public WebConfig(CurrentMemberIdArgumentResolver currentMemberIdArgumentResolver) {
        this.currentMemberIdArgumentResolver = currentMemberIdArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentMemberIdArgumentResolver);
    }
}