package com.bsf.exception;

public class NotEnoughTransactionBalance extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final String code;

    public NotEnoughTransactionBalance(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public NotEnoughTransactionBalance(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
