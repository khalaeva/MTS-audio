package com.moscow.cup.exception.business;

import com.moscow.cup.dto.response.ErrorMessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<?> handlerBusinessException(BusinessException ex){
        return ResponseEntity
                .status(ex.getCode())
                .body(
                        ErrorMessageResponse.builder()
                                .code(ex.getCode())
                                .message(ex.getMessage())
                                .build()
                );
    }

}
