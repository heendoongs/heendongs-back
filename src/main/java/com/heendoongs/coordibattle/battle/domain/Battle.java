package com.heendoongs.coordibattle.battle.domain;

import com.heendoongs.coordibattle.coordi.domain.Coordi;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "battle", schema = "COORDIBATTLE")
public class Battle {

    @Id @GeneratedValue
    @Column(name = "battle_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "banner_image_url")
    private String bannerImageURL;

    @Column(name = "vote_start_date")
    private LocalDate voteStartDate;

    @Column(name = "vote_end_date")
    private LocalDate voteEndDate;

    @Column(name = "coordi_start_date")
    private LocalDate coordiStartDate;

    @Column(name = "coordi_end_date")
    private LocalDate coordiEndDate;

    @OneToMany(mappedBy = "battle")
    List<Coordi> coordies = new ArrayList<>();

    @OneToMany(mappedBy = "battle")
    List<BattleClothes> battleClothes = new ArrayList<>();
}
