package com.heendoongs.coordibattle.member.controller;

import com.heendoongs.coordibattle.member.domain.MemberSignUpRequestDTO;
import com.heendoongs.coordibattle.member.domain.MemberUpdateDTO;
import com.heendoongs.coordibattle.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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
     * 회원정보수정
     */
    @PutMapping("/updateAccount")
    @ResponseStatus(HttpStatus.OK)
    public void updateAccount(@Valid @RequestBody MemberUpdateDTO memberUpdateDTO) throws Exception {
        memberService.updateAccount(memberUpdateDTO, SecurityContextHolder.getContext().getAuthentication().getName());
    }

    /**
     * 회원탈퇴
     */
    @DeleteMapping("/deleteAccount")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount() throws Exception {
        memberService.deleteAccount(SecurityContextHolder.getContext().getAuthentication().getName());
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
