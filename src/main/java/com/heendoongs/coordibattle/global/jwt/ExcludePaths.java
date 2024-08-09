package com.heendoongs.coordibattle.global.jwt;

import java.util.List;

/**
 * JWT Filter와 Login Filter를 거치지 않을 경로 표시 클래스 ExcludePaths
 * @author 조희정
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.04  	조희정       최초 생성
 * </pre>
 */
public class ExcludePaths {
    public static final List<String> EXCLUDED_PATHS = List.of(
            "/", "/login", "/signup",
            "/battle/banner", "/battle/title",
            "/coordi/clothes", "/coordi/list", "/coordi/list/filter",
            "/token/reissue"
    );
}
