package com.challenger.eurekaclientlobby.exception;

import com.challenger.eurekaclientlobby.exception.business.BusinessException;

public class UserIsNotFoundException extends BusinessException {
    public UserIsNotFoundException() {
        super("User is not found!", 404);
    }
}
