package com.bsf.exception;

public class InvalidTransactionException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String code;

    public InvalidTransactionException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
