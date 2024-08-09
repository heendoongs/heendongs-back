package com.heendoongs.coordibattle.member.domain;

import com.heendoongs.coordibattle.coordi.domain.Coordi;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * MemberCoordiVoteVO
 * @author 남진수
 * @since 2024.07.25
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.25  	남진수       최초 생성
 * 2024.07.25   남진수       VO 속성 추가 및 연관관계 설정
 * </pre>
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
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
    private Character liked;
}
