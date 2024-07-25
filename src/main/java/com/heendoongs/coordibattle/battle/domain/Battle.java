package com.heendoongs.coordibattle.battle.domain;

import com.heendoongs.coordibattle.coordi.domain.Coordi;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Battle {

    @Id @GeneratedValue
    @Column(name = "BATTLE_ID")
    private Long id;

    @Column(name = "BANNER_IMAGE_URL")
    private String bannerImageURL;

    @Column(name = "VOTE_START_DATE")
    private LocalDate voteStartDate;

    @Column(name = "VOTE_END_DATE")
    private LocalDate voteEndDate;

    @Column(name = "COORDI_START_DATE")
    private LocalDate coordiStartDate;

    @Column(name = "COORDI_END_DATE")
    private LocalDate coordiEndDate;

    @OneToMany(mappedBy = "battle")
    List<Coordi> coordies = new ArrayList<>();

    @OneToMany(mappedBy = "battle")
    List<BattleClothes> battleClothes = new ArrayList<>();
}
