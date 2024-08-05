package com.heendoongs.coordibattle.member.controller;

import com.heendoongs.coordibattle.coordi.dto.CoordiFilterRequestDTO;
import com.heendoongs.coordibattle.coordi.dto.CoordiListResponseDTO;
import com.heendoongs.coordibattle.global.annotation.MemberId;
import com.heendoongs.coordibattle.member.domain.CustomUserDetails;

import com.heendoongs.coordibattle.member.dto.*;
import com.heendoongs.coordibattle.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
     * @param memberUpdateDTO
     * @throws Exception
     */
    @PutMapping("/updateAccount")
    @ResponseStatus(HttpStatus.OK)
    public void updateAccount(@Valid @RequestBody MemberUpdateDTO memberUpdateDTO, @MemberId Long memberId) throws Exception {
        memberService.updateAccount(memberUpdateDTO, memberId);
    }

    /**
     * 회원 탈퇴
     * @throws Exception
     */
    @DeleteMapping("/deleteAccount")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@MemberId Long memberId) throws Exception {
        memberService.deleteAccount(memberId);
    }

    @GetMapping("/mycloset/list")
    public ResponseEntity<Page<CoordiListResponseDTO>> getMyCloset(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @MemberId Long memberId) {
        Page<CoordiListResponseDTO> myCoordiList = memberService.getMyCoordiList(page, size, memberId);
        return ResponseEntity.ok(myCoordiList);
    }

    @GetMapping("/mycloset/nickname")
    public ResponseEntity<MemberNicknameResponseDTO> getNickname(@MemberId Long memberId) {
        MemberNicknameResponseDTO memberNicknameResponseDTO = memberService.getNickname(memberId);
        return ResponseEntity.ok(memberNicknameResponseDTO);
    }

    @GetMapping("/myinfo")
    public ResponseEntity<MemberInfoResponseDTO> getMyInfo(@MemberId Long memberId) throws Exception {
        MemberInfoResponseDTO memberInfoResponseDTO = memberService.getMyInfo(memberId);
        return ResponseEntity.ok(memberInfoResponseDTO);
    }


    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    private String testPage () {

        return "test page입니다";
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    private String homePage () {
        return "기본 페이지 입니다";
    }
}
