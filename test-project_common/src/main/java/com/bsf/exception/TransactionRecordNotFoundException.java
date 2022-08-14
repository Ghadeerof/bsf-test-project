package com.bsf.exception;

import com.bsf.enums.TransactionError;

public class TransactionRecordNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private final String code;

    public TransactionRecordNotFoundException()
    {
        super(TransactionError.ERROR_NO_RECORD.getDescription());
        this.code = TransactionError.ERROR_NO_RECORD.getCode();
    }

    public String getCode() {
        return code;
    }
}