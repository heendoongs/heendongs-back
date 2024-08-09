package com.heendoongs.coordibattle.member.domain;

import com.heendoongs.coordibattle.coordi.domain.Coordi;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 멤버 VO
 * @author 조희정
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.26  	남진수       최초 생성
 * 2024.07.28  	조희정       updatePassword, updateNickname 추가
 * 2024.08.06  	조희정       deleted 필드 추가 (멤버 삭제 시 사용)
 * </pre>
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member", schema = "COORDIBATTLE")
@EntityListeners(AuditingEntityListener.class)
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
    @CreatedDate
    private LocalDate createDate;

    @Column(name = "deleted")
    @ColumnDefault("N")
    private Character deleted;

    @OneToMany(mappedBy = "member")
    List<Coordi> coordies = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<MemberCoordiVote> memberCoordiVotes = new ArrayList<>();

    // 회원 비밀번호 수정
    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    // 회원 닉네임 수정
    public void updateNickname(String newNickname) {
        this.nickname = newNickname;
    }

    // 회원 삭제
    public void updateDeleted() {
        this.deleted = 'Y';
    }

    public Member(Long memberId) {
        this.id = memberId;
    }
}
