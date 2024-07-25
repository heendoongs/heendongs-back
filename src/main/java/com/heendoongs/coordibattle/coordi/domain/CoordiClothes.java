package com.heendoongs.coordibattle.coordi.domain;

import com.heendoongs.coordibattle.clothes.domain.Clothes;
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
public class CoordiClothes {

    @Id @GeneratedValue
    @Column(name = "COORDI_CLOTHES_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COORDI_ID")
    private Coordi coordi;

    @ManyToOne
    @JoinColumn(name = "CLOTHES_ID")
    private Clothes clothes;
}
