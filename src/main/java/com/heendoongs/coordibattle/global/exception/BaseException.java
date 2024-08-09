package com.heendoongs.coordibattle.global.exception;

/**
 * RuntimeException 커스텀 예외 처리 추상 클래스
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
public abstract class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }

    public abstract BaseExceptionType getExceptionType();
}
