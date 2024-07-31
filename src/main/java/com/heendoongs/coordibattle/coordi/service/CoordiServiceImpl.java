package com.heendoongs.coordibattle.coordi.service;

import com.heendoongs.coordibattle.clothes.dto.ClothDetailsResponseDTO;
import com.heendoongs.coordibattle.coordi.domain.Coordi;
import com.heendoongs.coordibattle.coordi.dto.CoordiDetailsRequestDTO;
import com.heendoongs.coordibattle.coordi.dto.CoordiDetailsResponseDTO;
import com.heendoongs.coordibattle.coordi.dto.RankingOrderCoordiListResponseDTO;
import com.heendoongs.coordibattle.coordi.repository.CoordiRepository;
import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.domain.MemberCoordiVote;
import com.heendoongs.coordibattle.member.repository.MemberCoordiVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Base64;
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
 * 2024.07.28   남진수       updateCoordi 메소드 추가
 * 2024.07.28   남진수       deleteCoordi 메소드 추가
 * 2024.07.28   남진수       isCoordiPeriod 메소드 추가
 * 2024.07.30   임원정       getCoordiList 메소드 추가
 * 2024.07.31   임원정       getCoordiList 이미지 처리 오류 수정
 * </pre>
 */

@Service
@RequiredArgsConstructor
public class CoordiServiceImpl implements CoordiService {

    private final CoordiRepository coordiRepository;
    private final MemberCoordiVoteRepository memberCoordiVoteRepository;

    public CoordiDetailsResponseDTO getCoordiDetails(Long coordiId) {

        Coordi coordi = coordiRepository.findById(coordiId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid coordiId"));

        Long memberId = coordi.getMember().getId();
        String nickname = coordi.getMember().getNickname();
        LocalDate createDate = coordi.getCreateDate();
        String coordiImage = new String(coordi.getCoordiImage());
        String coordiTitle = coordi.getTitle();

        List<ClothDetailsResponseDTO> clothesList = coordi.getCoordiClothes().stream()
                .map(coordiClothes -> new ClothDetailsResponseDTO(
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

        return CoordiDetailsResponseDTO.builder()
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
    public CoordiDetailsResponseDTO likeCoordi(Long memberId, Long coordiId) {
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

    @Transactional
    public CoordiDetailsResponseDTO updateCoordi(Long memberId, Long coordiId, CoordiDetailsRequestDTO requestDTO) {
        Coordi coordi = coordiRepository.findById(coordiId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid coordiId"));

        if (coordi.getMember().getId() != memberId) {
            throw new IllegalArgumentException("Invalid memberId");
        }

        coordi = coordi.toBuilder()
                .title(requestDTO.getCoordiTitle())
                .build();

        if (!isCoordiPeriod(coordiId)) {
            throw new IllegalArgumentException("cannot update coordi after period");
        }

        coordiRepository.save(coordi);
        return getCoordiDetails(coordiId);
    }

    @Transactional
    public void deleteCoordi(Long memberId, Long coordiId) {

        Coordi coordi = coordiRepository.findById(coordiId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid coordiId"));

        if (coordi.getMember().getId() != memberId) {
            throw new IllegalArgumentException("Invalid memberId");
        }

        if (!isCoordiPeriod(coordiId)) {
            throw new IllegalArgumentException("cannot delete coordi after period");
        }

        coordiRepository.deleteById(coordiId);
    }

    private boolean isCoordiPeriod(Long coordiId) {
        LocalDate now = LocalDate.now();
        Coordi coordi = coordiRepository.findById(coordiId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid coordiId"));
        LocalDate startDate = coordi.getBattle().getCoordiStartDate();
        LocalDate endDate = coordi.getBattle().getCoordiEndDate();
        return now.isAfter(startDate) && now.isBefore(endDate);
    }

    /**
     * 코디 리스트 조회 (랭킹순)
     */
    @Transactional(readOnly = true)
    public Page<RankingOrderCoordiListResponseDTO> getCoordiListSortedByLikes(int page, int size) {
        return coordiRepository.findAllByLikesDesc(PageRequest.of(page, size))
                .map(coordi -> RankingOrderCoordiListResponseDTO.builder()
                        .coordiId(coordi.getId())
                        .coordiTitle(coordi.getTitle())
                        .coordiImage(new String(coordi.getCoordiImage()))
                        .nickname(coordi.getMember().getNickname())
                        .voteCount(coordi.getMemberCoordiVotes() != null
                                ? coordi.getMemberCoordiVotes().stream()
                                .filter(v -> v.getLiked() == 'Y')
                                .count()
                                : 0L)
                        .build());
    }

}
