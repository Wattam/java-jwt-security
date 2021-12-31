package com.wattam.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String exception) {
        super(exception);
    }
}
