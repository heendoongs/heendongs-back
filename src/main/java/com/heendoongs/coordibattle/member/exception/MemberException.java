package com.heendoongs.coordibattle.member.exception;

import com.heendoongs.coordibattle.global.exception.BaseException;
import com.heendoongs.coordibattle.global.exception.BaseExceptionType;

/**
 * 멤버 예외처리
 * @author 조희정
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.27  	조희정       최초 생성
 * </pre>
 */
public class MemberException extends BaseException {
    private BaseExceptionType exceptionType;

    public MemberException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
