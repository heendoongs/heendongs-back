package com.heendoongs.coordibattle.global.jwt;

import java.util.List;

public class ExcludePaths {
    public static final List<String> EXCLUDED_PATHS = List.of(
            "/", "/login", "/signup",
            "/battle/banner", "/battle/title",
            "/coordi/clothes", "/coordi/list", "/coordi/list/filter",
            "/token/reissue"
    );
}
