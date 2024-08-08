package com.heendoongs.coordibattle.member.service;

import com.heendoongs.coordibattle.coordi.domain.Coordi;
import com.heendoongs.coordibattle.coordi.dto.CoordiListResponseDTO;
import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.dto.MemberInfoResponseDTO;
import com.heendoongs.coordibattle.member.dto.MemberNicknameResponseDTO;
import com.heendoongs.coordibattle.member.dto.MemberSignUpRequestDTO;
import com.heendoongs.coordibattle.member.dto.MemberUpdateRequestDTO;
import com.heendoongs.coordibattle.member.exception.MemberException;
import com.heendoongs.coordibattle.member.exception.MemberExceptionType;
import com.heendoongs.coordibattle.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 멤버 서비스 구현체
 * @author 조희정
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.27  	조희정       최초 생성
 * 2024.07.27  	조희정       signUp 메소드 추가
 * 2024.07.29  	조희정       updateAccount, deleteAccount 메소드 추가
 * 2024.07.31  	조희정       getMyCoordiList, getNickname, getMyInfo 메소드 추가
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원가입
     * @param memberSignUpRequestDTO
     * @throws Exception
     */
    @Override
    public void signUp(MemberSignUpRequestDTO memberSignUpRequestDTO) throws Exception {

        String loginId = memberSignUpRequestDTO.getLoginId();
        String password = memberSignUpRequestDTO.getPassword();
        String nickname = memberSignUpRequestDTO.getNickname();

        // 아이디, 닉네임 중복 여부 확인
        if (memberRepository.existsByLoginId(loginId)) {
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_LOGIN_ID);
        }
        if (memberRepository.existsByNickname(nickname)) {
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_NICKNAME);
        }

        Member member = Member.builder()
                .loginId(loginId)
                .password(bCryptPasswordEncoder.encode(password))
                .nickname(nickname)
                .deleted('N')
                .build();

        // DB에 저장
        memberRepository.save(member);
    }


    /**
     * 회원 정보 수정
     * @param memberUpdateRequestDTO
     * @param memberId
     */
    @Override
    public void updateAccount(MemberUpdateRequestDTO memberUpdateRequestDTO, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        // 닉네임 수정
        if (memberUpdateRequestDTO.getNickname() != null && !member.getNickname().equals(memberUpdateRequestDTO.getNickname())) {
            if (memberRepository.existsByNickname(memberUpdateRequestDTO.getNickname())) {
                throw new MemberException(MemberExceptionType.ALREADY_EXIST_NICKNAME);
            }
            member.updateNickname(memberUpdateRequestDTO.getNickname());
        }

        // 비밀번호 수정
        if (memberUpdateRequestDTO.getPassword() != null) {
            String encodedPassword = bCryptPasswordEncoder.encode(memberUpdateRequestDTO.getPassword());
            member.updatePassword(encodedPassword);
        }
    }

    /**
     * 회원 삭제
     * @param memberId
     * @throws Exception
     */
    @Override
    public void deleteAccount(Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        // deleted 컬럼을 Y로 변경
        member.updateDeleted();
        // 사용자병 변경
        member.updateNickname("탈퇴한 사용자");
    }

    /**
     * 내 코디 리스트 조회
     * @param page
     * @param size
     * @param memberId
     * @return
     */
    @Override
    public Page<CoordiListResponseDTO> getMyCoordiList(int page, int size, Long memberId) {
        Page<Coordi> myCoordiList =  memberRepository.findMyCoordiByLikesDesc(PageRequest.of(page, size), memberId);
        return myCoordiList.map(coordi -> CoordiListResponseDTO.builder()
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

    /**
     * 회원 닉네임 조회
     * @param memberId
     * @return
     */
    @Override
    public MemberNicknameResponseDTO getNickname(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
        return MemberNicknameResponseDTO.builder()
                .nickname(member.getNickname())
                .build();
    }

    /**
     * 회원 정보 조회
     * @param memberId
     * @return
     */
    @Override
    public MemberInfoResponseDTO getMyInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        return MemberInfoResponseDTO.builder()
                .loginId(member.getLoginId())
                .nickname(member.getNickname())
                .build();
    }

}
