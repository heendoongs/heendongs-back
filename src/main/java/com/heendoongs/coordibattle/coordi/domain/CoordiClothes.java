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
@Table(name = "coordi_clothes", schema = "COORDIBATTLE")
public class CoordiClothes {

    @Id @GeneratedValue
    @Column(name = "coordi_clothes_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coordi_id")
    private Coordi coordi;

    @ManyToOne
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;
}
