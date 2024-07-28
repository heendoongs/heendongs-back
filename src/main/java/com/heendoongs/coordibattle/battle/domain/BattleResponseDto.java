package com.heendoongs.coordibattle.battle.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BattleResponseDto {
    private String coordiTitle;
    private String coordiImage;
    private String nickname;
}
