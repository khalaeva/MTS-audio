package com.challenger.eurekaclientlobby.exception;

import com.challenger.eurekaclientlobby.exception.business.BusinessException;

public class RoomIsNotFoundException extends BusinessException {
    public RoomIsNotFoundException() {
        super("This room is not found!", 404);
    }
}
