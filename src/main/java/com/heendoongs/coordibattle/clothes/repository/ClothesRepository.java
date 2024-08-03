package com.heendoongs.coordibattle.clothes.repository;

import com.heendoongs.coordibattle.clothes.domain.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {
}
