package com.challenger.eurekaclientlobby.exception;

import com.challenger.eurekaclientlobby.exception.business.BusinessException;

public class FailedDisconnectRoomException extends BusinessException {
    public FailedDisconnectRoomException() {
        super("Failed to disconnect from the room!", 500);
    }
}
