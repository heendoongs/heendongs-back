package com.heendoongs.coordibattle.battle.service;

import com.heendoongs.coordibattle.battle.domain.BattleResponseDto;
import com.heendoongs.coordibattle.coordi.domain.Coordi;
import com.heendoongs.coordibattle.coordi.repository.CoordiRepository;
import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.domain.MemberCoordiVote;
import com.heendoongs.coordibattle.member.domain.MemberCoordiVoteRequestDto;
import com.heendoongs.coordibattle.member.domain.MemberCoordiVoteResponseDto;
import com.heendoongs.coordibattle.member.repository.MemberCoordiVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
 * 2024.07.28   남진수       postBattleResult 메소드 추가
 * 2024.07.28   남진수       saveMemberCoordiVote 메소드 추가
 * </pre>
 */

@Service
@RequiredArgsConstructor
public class BattleServiceImpl implements BattleService{

    private final CoordiRepository coordiRepository;
    private final MemberCoordiVoteRepository memberCoordiVoteRepository;

    @Override
    public List<BattleResponseDto> getBattleCoordies(Long battleId, Long memberId) {

        List<Coordi> unvotedCoordies = coordiRepository.findUnvotedCoordies(battleId, memberId);
        Collections.shuffle(unvotedCoordies);

        return unvotedCoordies.stream()
                .limit(2)
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BattleResponseDto postBattleResult(MemberCoordiVoteRequestDto memberCoordiVoteRequestDto) {

        Long memberId = memberCoordiVoteRequestDto.getMemberId();
        Long winnerCoordiId = memberCoordiVoteRequestDto.getWinnerCoordiId();
        Long loserCoordiId = memberCoordiVoteRequestDto.getLoserCoordiId();

        MemberCoordiVoteResponseDto winnerVoteDto = MemberCoordiVoteResponseDto.builder()
                .memberId(memberId)
                .coordiId(winnerCoordiId)
                .liked('Y')
                .build();
        saveMemberCoordiVote(winnerVoteDto);

        MemberCoordiVoteResponseDto loserVoteDto = MemberCoordiVoteResponseDto.builder()
                .memberId(memberId)
                .coordiId(loserCoordiId)
                .liked('N')
                .build();
        saveMemberCoordiVote(loserVoteDto);

        Coordi winnerCoordi = coordiRepository.findById(winnerCoordiId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid winnerCoordiId"));

        return BattleResponseDto.builder()
                .coordiId(winnerCoordi.getId())
                .coordiTitle(winnerCoordi.getTitle())
                .coordiImage(new String(winnerCoordi.getCoordiImage()))
                .nickname(winnerCoordi.getMember().getNickname())
                .build();
    }

    private void saveMemberCoordiVote(MemberCoordiVoteResponseDto dto) {
        MemberCoordiVote memberCoordiVote = MemberCoordiVote.builder()
                .member(new Member(dto.getMemberId()))
                .coordi(new Coordi(dto.getCoordiId()))
                .liked(dto.getLiked())
                .build();
        memberCoordiVoteRepository.save(memberCoordiVote);
    }

    private BattleResponseDto convertToResponseDto(Coordi coordi) {
        return BattleResponseDto.builder()
                .coordiId(coordi.getId())
                .coordiTitle(coordi.getTitle())
                .coordiImage(new String(coordi.getCoordiImage()))
                .nickname(coordi.getMember().getNickname())
                .build();
    }
}
