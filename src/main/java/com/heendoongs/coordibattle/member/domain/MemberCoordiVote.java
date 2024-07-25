package com.heendoongs.coordibattle.member.domain;

import com.heendoongs.coordibattle.coordi.domain.Coordi;
import jakarta.persistence.*;

@Entity
public class MemberCoordiVote {

    @Id @GeneratedValue
    @Column(name = "MEMBER_COORDI_VOTE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COORDI_ID")
    private Coordi coordi;

    @Column(name = "LIKE")
    private Boolean like;
}
