package com.bsf.exception;

public class AccountCheckedException extends Exception {

    private static final long serialVersionUID = 1L;
    private final String code;

    public AccountCheckedException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public AccountCheckedException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}