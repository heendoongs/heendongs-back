package com.heendoongs.coordibattle.coordi.service;

import com.heendoongs.coordibattle.clothes.domain.ClothDetailsResponseDto;
import com.heendoongs.coordibattle.coordi.domain.Coordi;
import com.heendoongs.coordibattle.coordi.domain.CoordiDetailsResponseDto;
import com.heendoongs.coordibattle.coordi.repository.CoordiRepository;
import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.domain.MemberCoordiVote;
import com.heendoongs.coordibattle.member.repository.MemberCoordiVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 코디 서비스 구현체
 * @author 임원정
 * @since 2024.07.26
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.26  	임원정       최초 생성
 * 2024.07.28   남진수       getCoordiDetails 메소드 추가
 * 2024.07.28   남진수       likeCoordi 메소드 추가
 * </pre>
 */

@Service
@RequiredArgsConstructor
public class CoordiServiceImpl implements CoordiService {

    private final CoordiRepository coordiRepository;
    private final MemberCoordiVoteRepository memberCoordiVoteRepository;

    public CoordiDetailsResponseDto getCoordiDetails(Long coordiId) {

        Coordi coordi = coordiRepository.findById(coordiId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid coordiId"));

        Long memberId = coordi.getMember().getId();
        String nickname = coordi.getMember().getNickname();
        LocalDate createDate = coordi.getCreateDate();
        String coordiImage = new String(coordi.getCoordiImage());
        String coordiTitle = coordi.getTitle();

        List<ClothDetailsResponseDto> clothesList = coordi.getCoordiClothes().stream()
                .map(coordiClothes -> new ClothDetailsResponseDto(
                        coordiClothes.getClothes().getId(),
                        coordiClothes.getClothes().getBrand(),
                        coordiClothes.getClothes().getProductName(),
                        coordiClothes.getClothes().getPrice(),
                        coordiClothes.getClothes().getClothImageURL(),
                        coordiClothes.getClothes().getProductURL()
                )).collect(Collectors.toList());

        int voteCount = (int) coordi.getMemberCoordiVotes().stream()
                .filter(vote -> vote.getLiked() != null && vote.getLiked() == 'Y')
                .count();

        return CoordiDetailsResponseDto.builder()
                .memberId(memberId)
                .nickname(nickname)
                .createDate(createDate)
                .coordiImage(coordiImage)
                .coordiTitle(coordiTitle)
                .clothesList(clothesList)
                .voteCount(voteCount)
                .build();
    }

    @Transactional
    public CoordiDetailsResponseDto likeCoordi(Long memberId, Long coordiId) {
        Optional<MemberCoordiVote> existingVote = memberCoordiVoteRepository.findByMemberIdAndCoordiId(memberId, coordiId);

        MemberCoordiVote memberCoordiVote;
        if (existingVote.isPresent()) {
            memberCoordiVote = existingVote.get();
            memberCoordiVote = memberCoordiVote.toBuilder()
                    .liked(memberCoordiVote.getLiked() == 'Y' ? 'N' : 'Y')
                    .build();
        } else {
            memberCoordiVote = MemberCoordiVote.builder()
                    .member(new Member(memberId))
                    .coordi(new Coordi(coordiId))
                    .liked('Y')
                    .build();
        }

        memberCoordiVoteRepository.save(memberCoordiVote);
        return getCoordiDetails(coordiId);
    }
}
