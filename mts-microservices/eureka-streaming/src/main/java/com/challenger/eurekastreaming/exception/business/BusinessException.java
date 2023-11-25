package com.challenger.eurekastreaming.exception.business;

public class BusinessException extends RuntimeException {

    protected Integer code;
    protected String message;

    public BusinessException(Integer code, String message){

        super(message);

        this.code = code;
        this.message = message;

    }

}
