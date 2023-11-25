package com.challenger.eurekaclientlobby.exception;

import com.challenger.eurekaclientlobby.exception.business.BusinessException;

public class AccessDeniedException extends BusinessException {

    public AccessDeniedException() {
        super("Access Denied", 403);
    }
}
