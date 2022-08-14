package com.bsf.enums;

import com.bsf.constant.TransactionConstant;

public enum TransactionError {
    ERROR_CREATE(TransactionConstant.CREATE_ERROR_CODE, TransactionConstant.CREATE_ERROR),
    ERROR_NOT_EXIST(TransactionConstant.RECORD_DOES_NOT_EXIST_CODE, TransactionConstant.RECORD_DOES_NOT_EXIST),
    ERROR_EXIST(TransactionConstant.RECORD_ALREADY_EXISTS_CODE, TransactionConstant.RECORD_ALREADY_EXISTS),
    ERROR_NO_RECORD(TransactionConstant.NO_RECORDS_FOUND_CODE, TransactionConstant.NO_RECORDS_FOUND),
    ERROR_INVALID_REQ(TransactionConstant.INVALID_REQUEST_CODE, TransactionConstant.INVALID_REQUEST),
    ERROR_GENERAL_MSG(TransactionConstant.GENERAL_ERROR_CODE, TransactionConstant.GENERAL_ERROR),
    ERROR_NOT_ENOUGH_BALANCE(TransactionConstant.NOT_ENOUGH_BALANCE_ERROR_CODE, TransactionConstant.NOT_ENOUGH_BALANCE_ERROR),
    ;

    private final String code;
    private final String description;

    private TransactionError(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}

