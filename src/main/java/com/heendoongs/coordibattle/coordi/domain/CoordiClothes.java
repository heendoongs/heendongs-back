package com.heendoongs.coordibattle.coordi.domain;

import com.heendoongs.coordibattle.clothes.domain.Clothes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * CoordiClothesVO
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
@Builder
@Table(name = "coordi_clothes", schema = "COORDIBATTLE")
public class CoordiClothes {

    @Id @GeneratedValue
    @Column(name = "coordi_clothes_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coordi_id")
    private Coordi coordi;

    @ManyToOne
    @JoinColumn(name = "cloth_id")
    private Clothes clothes;
}
