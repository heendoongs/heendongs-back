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

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionDto> handleBaseEx(BaseException exception) {
        log.error("BaseException errorMessage(): {}", exception.getExceptionType().getErrorMessage());
        log.error("BaseException errorCode(): {}", exception.getExceptionType().getErrorCode());

        return new ResponseEntity<>(new ExceptionDto(exception.getExceptionType().getErrorCode(), exception.getExceptionType().getErrorMessage()), exception.getExceptionType().getHttpStatus());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionDto> handleValidEx(BindException exception) {
        log.error("@ValidException 발생! {}", exception.getMessage());
        return new ResponseEntity<>(new ExceptionDto(2000, "유효성 검사 실패"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDto> httpMessageNotReadableExceptionEx(HttpMessageNotReadableException exception) {
        log.error("Json을 파싱하는 과정에서 예외 발생! {}", exception.getMessage());
        return new ResponseEntity<>(new ExceptionDto(3000, "JSON 파싱 오류"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGenericException(Exception exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(new ExceptionDto(5000, "서버 오류"), HttpStatus.INTERNAL_SERVER_ERROR);
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