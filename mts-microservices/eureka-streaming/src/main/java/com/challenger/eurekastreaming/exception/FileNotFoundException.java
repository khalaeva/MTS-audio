package com.challenger.eurekastreaming.exception;

import com.challenger.eurekastreaming.exception.business.BusinessException;

public class FileNotFoundException extends BusinessException {
    public FileNotFoundException() {
        super(404, "File is not found!");
    }
}
