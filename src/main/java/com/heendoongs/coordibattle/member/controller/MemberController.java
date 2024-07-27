package com.heendoongs.coordibattle.member.controller;

import com.heendoongs.coordibattle.member.domain.MemberSignUpDTO;
import com.heendoongs.coordibattle.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    private void signUp(@Valid @RequestBody MemberSignUpDTO memberSignUpDTO) throws Exception {
        memberService.signUp(memberSignUpDTO);
    }
}
