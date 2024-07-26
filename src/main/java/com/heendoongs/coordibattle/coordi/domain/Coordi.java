package com.heendoongs.coordibattle.coordi.domain;

import com.heendoongs.coordibattle.battle.domain.Battle;
import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.domain.MemberCoordiVote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coordi {

    @Id @GeneratedValue
    @Column(name = "coordi_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "battle_id")
    private Battle battle;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "coordi_image")
    private byte[] coordiImage;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @OneToMany(mappedBy = "coordi")
    List<MemberCoordiVote> memberCoordiVotes = new ArrayList<>();

    @OneToMany(mappedBy = "coordi")
    List<CoordiClothes> coordiClothes = new ArrayList<>();
}
