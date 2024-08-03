package com.heendoongs.coordibattle.coordi.service;

import com.heendoongs.coordibattle.battle.domain.Battle;
import com.heendoongs.coordibattle.battle.repository.BattleRepository;
import com.heendoongs.coordibattle.battle.service.BattleService;
import com.heendoongs.coordibattle.battle.service.BattleServiceImpl;
import com.heendoongs.coordibattle.clothes.domain.Clothes;
import com.heendoongs.coordibattle.clothes.dto.ClothDetailsResponseDTO;
import com.heendoongs.coordibattle.clothes.repository.ClothesRepository;
import com.heendoongs.coordibattle.coordi.domain.Coordi;
import com.heendoongs.coordibattle.coordi.domain.CoordiClothes;
import com.heendoongs.coordibattle.coordi.dto.*;
import com.heendoongs.coordibattle.coordi.repository.CoordiClothesRepository;
import com.heendoongs.coordibattle.coordi.repository.CoordiRepository;
import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.domain.MemberCoordiVote;
import com.heendoongs.coordibattle.member.repository.MemberCoordiVoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
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
 * 2024.07.31   남진수       getCoordiDetails 메소드 수정(투표 유무, 기간 추가)
 * 2024.08.01   남진수       getCoordiDetails 파라미터 추가
 * 2024.08.01   임원정       코디 리스트 필터 적용 및 DTO 변환 메소드 추가
 * 2024.08.02   임원정       getClothesByType 메소드 추가
 * </pre>
 */

@Log4j2
@Service
@RequiredArgsConstructor
public class CoordiServiceImpl implements CoordiService {

    private final CoordiRepository coordiRepository;
    private final MemberCoordiVoteRepository memberCoordiVoteRepository;
    private final CoordiClothesRepository coordiClothesRepository;
    private final ClothesRepository clothesRepository;
    private final BattleService battleService;

    public CoordiDetailsResponseDTO getCoordiDetails(Long memberId, Long coordiId) {

        Coordi coordi = coordiRepository.findById(coordiId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid coordiId"));

        Long memberIdOfCoordi = coordi.getMember().getId();
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

        boolean isVotingPeriod = isVotingPeriod(coordiId);
        boolean isCoordiPeriod = isCoordiPeriod(coordiId);
        boolean isVoted = memberCoordiVoteRepository.findByMemberIdAndCoordiId(memberId, coordiId)
                .map(vote -> vote.getLiked() == 'Y')
                .orElse(false);

        return CoordiDetailsResponseDTO.builder()
                .memberId(memberIdOfCoordi)
                .nickname(nickname)
                .createDate(createDate)
                .coordiImage(coordiImage)
                .coordiTitle(coordiTitle)
                .clothesList(clothesList)
                .voteCount(voteCount)
                .isVotingPeriod(isVotingPeriod)
                .isCoordiPeriod(isCoordiPeriod)
                .isVoted(isVoted)
                .build();
    }

    @Transactional
    public CoordiDetailsResponseDTO likeCoordi(Long memberId, Long coordiId) {

        if (!isVotingPeriod(coordiId)) {
            throw new IllegalArgumentException("cannot vote after period");
        } else {
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
            return getCoordiDetails(memberId, coordiId);
        }
    }

    @Transactional
    public CoordiDetailsResponseDTO updateCoordi(Long memberId, Long coordiId, CoordiDetailsRequestDTO requestDTO) {
        Coordi coordi = coordiRepository.findById(coordiId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid coordiId"));

        if (!Objects.equals(coordi.getMember().getId(), memberId)) {
            throw new IllegalArgumentException("Invalid memberId");
        }

        coordi = coordi.toBuilder()
                .title(requestDTO.getCoordiTitle())
                .build();

        if (!isCoordiPeriod(coordiId)) {
            throw new IllegalArgumentException("cannot update coordi after period");
        }

        coordiRepository.save(coordi);
        return getCoordiDetails(memberId, coordiId);
    }

    @Transactional
    public void deleteCoordi(Long memberId, Long coordiId) {

        Coordi coordi = coordiRepository.findById(coordiId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid coordiId"));

        if (!Objects.equals(coordi.getMember().getId(), memberId)) {
            throw new IllegalArgumentException("Invalid memberId");
        }

        if (!isCoordiPeriod(coordiId)) {
            throw new IllegalArgumentException("cannot delete coordi after period");
        }
        coordiClothesRepository.deleteAllByCoordiId(coordiId);
        memberCoordiVoteRepository.deleteAllByCoordiId(coordiId);
        coordiRepository.deleteById(coordiId);
    }

    public boolean isCoordiPeriod(Long coordiId) {
        LocalDate now = LocalDate.now();
        Coordi coordi = coordiRepository.findById(coordiId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid coordiId"));
        LocalDate startDate = coordi.getBattle().getCoordiStartDate();
        LocalDate endDate = coordi.getBattle().getCoordiEndDate();
        return now.isAfter(startDate) && now.isBefore(endDate);
    }

    public boolean isVotingPeriod(Long coordiId) {
        LocalDate now = LocalDate.now();
        Coordi coordi = coordiRepository.findById(coordiId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid coordiId"));
        LocalDate startDate = coordi.getBattle().getVoteStartDate();
        LocalDate endDate = coordi.getBattle().getVoteEndDate();
        return now.isAfter(startDate) && now.isBefore(endDate);
    }

    /**
     * 코디 리스트 조회 (기본 - 랭킹순)
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = true)
    public Page<CoordiListResponseDTO> getCoordiList(int page, int size) throws Exception {
        return coordiRepository.findAllByLikesDesc(PageRequest.of(page, size))
                .map(this::convertToCoordiListRespoonseDTO);
    }

    /**
     * 코디 리스트 조회 (필터 적용)
     * @param requestDTO
     * @return
     * @throws Exception
     */
    @Transactional
    public Page<CoordiListResponseDTO> getCoordiListWithFilter(CoordiFilterRequestDTO requestDTO) throws Exception {
        Pageable pageable = PageRequest.of(requestDTO.getPage(), requestDTO.getSize());
        return coordiRepository.findAllWithFilterAndOrder(requestDTO.getBattleId(), requestDTO.getOrder(), pageable)
                .map(this::convertToCoordiListRespoonseDTO);
    }

    /**
     * 타입별 옷 리스트 반환
     * @param type
     * @return
     * @throws Exception
     */
    @Transactional
    public List<ClothesResponseDTO> getClothesByType(String type) throws Exception {
        Long battleId = battleService.getCoordingBattleId();
        List<Clothes> clothes = coordiRepository.findClothesWithBattleAndType(type, battleId);
        return clothes.stream()
                .map(cloth -> ClothesResponseDTO.builder()
                        .clothId(cloth.getId())
                        .type(cloth.getType())
                        .clothImageURL(cloth.getClothImageURL())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 코디 추가
     * @param requestDTO
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean insertCoordi(CoordiCreateRequestDTO requestDTO) throws Exception {
        // byte로 변환
        byte[] decodedImage = requestDTO.getCoordiImage().getBytes();
        System.out.println("decodedImage: "+decodedImage);

        Long battleId = battleService.getCoordingBattleId();

        Coordi coordi = Coordi.builder()
                .member(new Member(requestDTO.getMemberId()))
                .battle(new Battle(battleId))
                .title(requestDTO.getTitle())
                .coordiImage(decodedImage)
                .createDate(LocalDate.now())
                .build();

        coordiRepository.save(coordi);

        // 저장된 코디에 옷 정보 추가
        List<CoordiClothes> coordiClothList = requestDTO.getClothIds().stream()
                .map(clothId -> CoordiClothes.builder()
                        .coordi(coordi)
                        .clothes(new Clothes(clothId))
                        .build())
                .collect(Collectors.toList());

        coordiClothesRepository.saveAll(coordiClothList);

        return true;
    }


    /**
     * 코디 리스트 Response DTO 변환 함수
     * @param coordi
     * @return
     */
    private CoordiListResponseDTO convertToCoordiListRespoonseDTO(Coordi coordi) {
        return CoordiListResponseDTO.builder()
                .coordiId(coordi.getId())
                .coordiTitle(coordi.getTitle())
                .coordiImage(new String(coordi.getCoordiImage()))
                .nickname(coordi.getMember().getNickname())
                .voteCount(coordi.getMemberCoordiVotes() != null
                        ? coordi.getMemberCoordiVotes().stream()
                        .filter(v -> v.getLiked() == 'Y')
                        .count()
                        : 0L)
                .build();
    }
}
