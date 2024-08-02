package com.heendoongs.coordibattle.member.controller;

import com.heendoongs.coordibattle.member.domain.Member;
import com.heendoongs.coordibattle.member.dto.MemberInfoResponseDTO;
import com.heendoongs.coordibattle.member.dto.MemberMyClosetResponseDTO;
import com.heendoongs.coordibattle.member.dto.MemberSignUpRequestDTO;
import com.heendoongs.coordibattle.member.dto.MemberUpdateDTO;
import com.heendoongs.coordibattle.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public void updateAccount(@Valid @RequestBody MemberUpdateDTO memberUpdateDTO) throws Exception {
        memberService.updateAccount(memberUpdateDTO);
    }

    /**
     * 회원 탈퇴
     * @throws Exception
     */
    @DeleteMapping("/deleteAccount")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@RequestParam Long memberId) throws Exception {
        memberService.deleteAccount(memberId);
    }

    @GetMapping("/mycloset")
    public ResponseEntity<MemberMyClosetResponseDTO> getMyCloset(@RequestParam Long memberId) {
        // 마이페이지 데이터 조회
        MemberMyClosetResponseDTO memberMyClosetResponseDTO = memberService.getMyCloset(memberId);
        return ResponseEntity.ok(memberMyClosetResponseDTO);
    }

    @GetMapping("/myinfo")
    public ResponseEntity<MemberInfoResponseDTO> getMyInfo(Authentication auth) {
        Long loginUser = memberService.getByLoginId(auth.getName()).getId();
//
//        return String.format("loginId : %s\nnickname : %s\nrole : %s",
//                loginUser.getLoginId(), loginUser.getNickname(), loginUser.getRole().name());

        MemberInfoResponseDTO memberInfoResponseDTO = memberService.getMyInfo(loginUser);
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
