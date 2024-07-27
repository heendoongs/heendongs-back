package com.heendoongs.coordibattle.member.domain;

import com.heendoongs.coordibattle.coordi.domain.Coordi;
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
@Table(name = "member_coordi_vote", schema = "COORDIBATTLE")
public class MemberCoordiVote {

    @Id @GeneratedValue
    @Column(name = "member_coordi_vote_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordi_id")
    private Coordi coordi;

    @Column(name = "liked")
    private Boolean liked;
}
