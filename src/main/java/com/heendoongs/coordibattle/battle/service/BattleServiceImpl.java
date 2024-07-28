package com.heendoongs.coordibattle.battle.service;

import com.heendoongs.coordibattle.battle.domain.BattleResponseDto;
import com.heendoongs.coordibattle.coordi.domain.Coordi;
import com.heendoongs.coordibattle.coordi.repository.CoordiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 배틀 서비스 구현체
 * @author 남진수
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.27  	남진수       최초 생성
 * 2024.07.27   남진수       getBattleCoordies 메소드 추가
 * 2024.07.27   남진수       convertToResponseDto 메소드 추가
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class BattleServiceImpl implements BattleService{

    private final CoordiRepository coordiRepository;

    @Override
    public List<BattleResponseDto> getBattleCoordies(Long battleId, Long memberId) {

        List<Coordi> unvotedCoordies = coordiRepository.findUnvotedCoordies(battleId, memberId);
        Collections.shuffle(unvotedCoordies);

        return unvotedCoordies.stream()
                .limit(2)
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private BattleResponseDto convertToResponseDto(Coordi coordi) {
        return BattleResponseDto.builder()
                .coordiTitle(coordi.getTitle())
                .coordiImage(new String(coordi.getCoordiImage()))
                .nickname(coordi.getMember().getNickname())
                .build();
    }
}
