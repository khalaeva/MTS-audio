package com.moscow.cup.exception;

import com.moscow.cup.exception.business.BusinessException;

public class AccessDeniedException extends BusinessException {

    public AccessDeniedException() {
        super("Access Denied", 403);
    }
}
