package com.heendoongs.coordibattle.battle.domain;

import com.heendoongs.coordibattle.clothes.domain.Clothes;
import jakarta.persistence.*;

@Entity
public class BattleClothes {

    @Id @GeneratedValue
    @Column(name = "BATTLE_CLOTHES_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BATTLE_ID")
    private Battle battle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLOTHES_ID")
    private Clothes clothes;
}
