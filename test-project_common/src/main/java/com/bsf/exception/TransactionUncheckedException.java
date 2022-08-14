package com.bsf.exception;

public class TransactionUncheckedException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final String code;

    public TransactionUncheckedException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public TransactionUncheckedException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

