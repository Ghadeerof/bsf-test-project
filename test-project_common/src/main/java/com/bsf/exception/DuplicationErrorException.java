package com.bsf.exception;

public class DuplicationErrorException extends RuntimeException {

    private String code;
    private String message;
    public DuplicationErrorException(String message) {
        super(message);
    }
    public DuplicationErrorException(String code, String message){
        this.code=code;
        this.message=message;
    }
    public String getCode() {
        return code;
    }
}