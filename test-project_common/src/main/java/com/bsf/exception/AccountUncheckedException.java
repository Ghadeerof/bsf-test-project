package com.bsf.exception;

public class AccountUncheckedException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final String code;

    public AccountUncheckedException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public AccountUncheckedException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}