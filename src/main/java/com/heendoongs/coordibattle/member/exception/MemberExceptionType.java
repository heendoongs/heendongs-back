package com.heendoongs.coordibattle.member.exception;

import com.heendoongs.coordibattle.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

/**
 * 멤버 예외처리 타입
 * @author 조희정
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.27  	조희정       최초 생성
 * 2024.07.27  	조희정       회원가입 에러 추가
 * 2024.07.27  	조희정       로그인, 토큰 에러 추가
 *
 * </pre>
 */
public enum MemberExceptionType implements BaseExceptionType {

    // 로그인 에러,
    NOT_FOUND_MEMBER(600, HttpStatus.NOT_FOUND, "회원 정보가 없습니다."),

    // 회원가입 에러
    ALREADY_EXIST_LOGIN_ID(601, HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    ALREADY_EXIST_NICKNAME(602, HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다."),

    // 토큰 에러
    EXPIRED_TOKEN(700, HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    INVALID_TOKEN(701, HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),

    // refresh 토큰 에러
    NO_REFRESH_TOKEN(800, HttpStatus.BAD_REQUEST, "refresh 토큰이 없습니다."),
    EXPIRED_REFRESH_TOKEN(800, HttpStatus.BAD_REQUEST, "refresh 토큰이 만료되었습니다."),
    INVALID_REFRESH_TOKEN(800, HttpStatus.BAD_REQUEST, "유효하지 않은 refresh 토큰입니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    MemberExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
