package com.project.hackathon.hackathon.exception.business;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    protected String message;
    protected Integer code;

    public BusinessException(String message, Integer code){
        super(message);

        this.message = message;
        this.code = code;
    }
}
