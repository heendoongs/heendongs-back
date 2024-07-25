package com.heendoongs.coordibattle.clothes.domain;

import com.heendoongs.coordibattle.battle.domain.BattleClothes;
import com.heendoongs.coordibattle.coordi.domain.CoordiClothes;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Clothes {

    @Id @GeneratedValue
    @Column(name = "CLOTH_ID")
    private Long id;

    @Column(name = "TYPE")
    private String Type;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "CLOTH_IMAGE_URL")
    private String clothImageURL;

    @Column(name = "PRODUCT_URL")
    private String productURL;

    @OneToMany(mappedBy = "clothes")
    List<CoordiClothes> coordiClothes = new ArrayList<>();

    @OneToMany(mappedBy = "clothes")
    List<BattleClothes> battleClothes = new ArrayList<>();


}
