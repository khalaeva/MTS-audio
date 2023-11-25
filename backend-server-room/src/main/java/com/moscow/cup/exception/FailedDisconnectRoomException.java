package com.moscow.cup.exception;

import com.moscow.cup.exception.business.BusinessException;

public class FailedDisconnectRoomException extends BusinessException {
    public FailedDisconnectRoomException() {
        super("Failed to disconnect from the room!", 500);
    }
}
