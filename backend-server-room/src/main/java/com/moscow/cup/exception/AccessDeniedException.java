package com.moscow.cup.exception;

import com.moscow.cup.exception.business.BusinessException;

public class AccessDeniedException extends BusinessException {

    private final String message = "Access Denied";
    private final Integer code = 403;

    public AccessDeniedException(String message, Integer code) {
        super(message, code);
    }
}
