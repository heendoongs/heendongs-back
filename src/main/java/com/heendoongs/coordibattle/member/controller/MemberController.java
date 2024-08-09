package com.heendoongs.coordibattle.member.controller;

import com.heendoongs.coordibattle.coordi.dto.CoordiListResponseDTO;
import com.heendoongs.coordibattle.global.annotation.MemberId;
import com.heendoongs.coordibattle.member.dto.MemberInfoResponseDTO;
import com.heendoongs.coordibattle.member.dto.MemberNicknameResponseDTO;
import com.heendoongs.coordibattle.member.dto.MemberSignUpRequestDTO;
import com.heendoongs.coordibattle.member.dto.MemberUpdateRequestDTO;
import com.heendoongs.coordibattle.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 멤버 컨트롤러
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
 * 2024.07.31  	조희정       getMyCloset, getNickname, getMyInfo 메소드 추가
 * </pre>
 */
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입
     * @param memberSignUpRequestDTO
     * @throws Exception
     */
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    private void signUp(@Valid @RequestBody MemberSignUpRequestDTO memberSignUpRequestDTO) throws Exception {
        memberService.signUp(memberSignUpRequestDTO);
    }

    /**
     * 회원 정보 수정
     * @param memberUpdateRequestDTO
     * @param memberId
     * @throws Exception
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateAccount(@Valid @RequestBody MemberUpdateRequestDTO memberUpdateRequestDTO, @MemberId Long memberId) throws Exception {
        memberService.updateAccount(memberUpdateRequestDTO, memberId);
    }

    /**
     * 회원 탈퇴
     * @param memberId
     * @throws Exception
     */
    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@MemberId Long memberId) throws Exception {
        memberService.deleteAccount(memberId);
    }

    /**
     * 내 옷장 리스트 조회
     * @param page
     * @param size
     * @param memberId
     * @return
     */
    @GetMapping("/mycloset/list")
    public ResponseEntity<Page<CoordiListResponseDTO>> getMyCloset(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @MemberId Long memberId) {
        Page<CoordiListResponseDTO> myCoordiList = memberService.getMyCoordiList(page, size, memberId);
        return ResponseEntity.ok(myCoordiList);
    }

    /**
     * 내 옷장 닉네임 조회
     * @param memberId
     * @return
     */
    @GetMapping("/mycloset/nickname")
    public ResponseEntity<MemberNicknameResponseDTO> getNickname(@MemberId Long memberId) {
        MemberNicknameResponseDTO memberNicknameResponseDTO = memberService.getNickname(memberId);
        return ResponseEntity.ok(memberNicknameResponseDTO);
    }

    /**
     * 내 정보 조회
     * @param memberId
     * @return
     * @throws Exception
     */
    @GetMapping("/myinfo")
    public ResponseEntity<MemberInfoResponseDTO> getMyInfo(@MemberId Long memberId) {
        MemberInfoResponseDTO memberInfoResponseDTO = memberService.getMyInfo(memberId);
        return ResponseEntity.ok(memberInfoResponseDTO);
    }

}
