package com.heendoongs.coordibattle.battle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BattleResponseDTO {
    private Long coordiId;
    private String coordiTitle;
    private String coordiImage;
    private String nickname;
}
