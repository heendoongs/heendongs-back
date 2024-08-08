package com.heendoongs.coordibattle.global.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.net.BindException;
import java.util.HashMap;
import java.util.Map;

/**
 * 예외 처리를위한 ExceptionAdvice 클래스
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
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    /**
     * BaseException 타입 예외 처리
     * @param exception
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionDto> handleBaseEx(BaseException exception) {
        log.error("BaseException errorMessage(): {}", exception.getExceptionType().getErrorMessage());
        log.error("BaseException errorCode(): {}", exception.getExceptionType().getErrorCode());

        return new ResponseEntity<>(new ExceptionDto(exception.getExceptionType().getErrorCode(), exception.getExceptionType().getErrorMessage()), exception.getExceptionType().getHttpStatus());
    }

    /**
     * BindException  타입 예외 처리
     * @param exception
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionDto> handleValidEx(BindException exception) {
        log.error("@ValidException 발생! {}", exception.getMessage());
        return new ResponseEntity<>(new ExceptionDto(2000, "유효성 검사 실패"), HttpStatus.BAD_REQUEST);
    }

    /**
     * HttpMessageNotReadableException 타입 예외 처리
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDto> httpMessageNotReadableExceptionEx(HttpMessageNotReadableException exception) {
        log.error("Json을 파싱하는 과정에서 예외 발생! {}", exception.getMessage());
        return new ResponseEntity<>(new ExceptionDto(3000, "JSON 파싱 오류"), HttpStatus.BAD_REQUEST);
    }

    /**
     * 일반 예외 처리
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGenericException(Exception exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(new ExceptionDto(5000, "서버 오류"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * MethodArgumentNotValidException 예외 처리
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class ExceptionDto {
        @JsonProperty("code")
        private Integer errorCode;
        @JsonProperty("message")
        private String errorMessage;
    }

}