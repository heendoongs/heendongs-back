package com.heendoongs.coordibattle.coordi.domain;

import com.heendoongs.coordibattle.battle.domain.Battle;
import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.domain.MemberCoordiVote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "coordi", schema = "COORDIBATTLE")
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

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "coordi")
    List<MemberCoordiVote> memberCoordiVotes = new ArrayList<>();

    @OneToMany(mappedBy = "coordi")
    List<CoordiClothes> coordiClothes = new ArrayList<>();

    public Coordi(Long coordiId) {
        this.id = coordiId;
    }
}
