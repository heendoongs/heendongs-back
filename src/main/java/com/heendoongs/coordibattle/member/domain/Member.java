package com.heendoongs.coordibattle.member.domain;

import com.heendoongs.coordibattle.coordi.domain.Coordi;
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
@Table(name = "member", schema = "COORDIBATTLE")
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "create_date")
    private LocalDate createDate;

    @OneToMany(mappedBy = "member")
    List<Coordi> coordies = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<MemberCoordiVote> memberCoordiVotes = new ArrayList<>();

    public Member(Long memberId) {
        this.id = memberId;
    }
}
