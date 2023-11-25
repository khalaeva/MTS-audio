package com.moscow.cup.exception;

import com.moscow.cup.exception.business.BusinessException;

public class RoomIsNotFoundException extends BusinessException {
    public RoomIsNotFoundException() {
        super("This room is not found!", 404);
    }
}
