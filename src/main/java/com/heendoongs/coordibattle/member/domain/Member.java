package com.heendoongs.coordibattle.member.domain;

import com.heendoongs.coordibattle.coordi.domain.Coordi;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;

    @OneToMany(mappedBy = "member")
    List<Coordi> coordies = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<MemberCoordiVote> memberCoordiVotes = new ArrayList<>();
}
