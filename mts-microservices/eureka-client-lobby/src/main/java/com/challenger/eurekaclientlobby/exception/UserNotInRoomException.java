package com.challenger.eurekaclientlobby.exception;

import com.moscow.cup.exception.business.BusinessException;

public class UserNotInRoomException extends BusinessException {
    public UserNotInRoomException() {
        super("User is not in room!", 401);
    }
}
