package com.moscow.cup.exception;

import com.moscow.cup.exception.business.BusinessException;

public class UserIsNotFoundException extends BusinessException {
    public UserIsNotFoundException() {
        super("User is not found!", 404);
    }
}
