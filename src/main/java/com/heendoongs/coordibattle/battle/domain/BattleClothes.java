package com.heendoongs.coordibattle.battle.domain;

import com.heendoongs.coordibattle.clothes.domain.Clothes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BattleClothes {

    @Id @GeneratedValue
    @Column(name = "battle_clothes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "battle_id")
    private Battle battle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;
}
