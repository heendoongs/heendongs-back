package com.heendoongs.coordibattle.global.exception;

import org.springframework.http.HttpStatus;

/**
 * 예외 유형 정의를 위한 BaseExceptionType 인터페이스
 * @author 조희정
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	조희정       최초 생성
 * </pre>
 */
public interface BaseExceptionType {

    // 에러 코드
    int getErrorCode();

    // 에러 상태
    HttpStatus getHttpStatus();

    // 에러 메시지
    String getErrorMessage();
}