package com.heendoongs.coordibattle.clothes.domain;

import com.heendoongs.coordibattle.battle.domain.BattleClothes;
import com.heendoongs.coordibattle.coordi.domain.CoordiClothes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

/**
 * ClothesVO
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
@Table(name = "clothes", schema = "COORDIBATTLE")
public class Clothes {

    @Id @GeneratedValue
    @Column(name = "cloth_id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "brand")
    private String brand;

    @Column(name = "price")
    private Integer price;

    @Column(name = "cloth_image_url")
    private String clothImageURL;

    @Column(name = "product_url")
    private String productURL;

    @OneToMany(mappedBy = "clothes")
    List<CoordiClothes> coordiClothes = new ArrayList<>();

    @OneToMany(mappedBy = "clothes")
    List<BattleClothes> battleClothes = new ArrayList<>();

    public Clothes(Long clothesId) {
        this.id = clothesId;
    }
}
