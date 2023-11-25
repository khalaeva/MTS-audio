package com.challenger.eurekaclientlobby.exception;

import com.challenger.eurekaclientlobby.exception.business.BusinessException;

public class UserNotInRoomException extends BusinessException {
    public UserNotInRoomException() {
        super("User is not in room!", 401);
    }
}
