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
    private String Type;

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


}
