package com.bsf.enums;

import com.bsf.constant.AccountConstant;

public enum AccountError {
    ERROR_CREATE(AccountConstant.CREATE_ERROR_CODE, AccountConstant.CREATE_ERROR),
    ERROR_UPDATE(AccountConstant.UPDATE_ERROR_CODE, AccountConstant.UPDATE_ERROR),
    ERROR_DELETE(AccountConstant.DELETE_ERROR_CODE, AccountConstant.DELETE_ERROR),


    ERROR_NOT_EXIST(AccountConstant.RECORD_DOES_NOT_EXIST_CODE, AccountConstant.RECORD_DOES_NOT_EXIST),
    ERROR_EXIST(AccountConstant.RECORD_ALREADY_EXISTS_CODE, AccountConstant.RECORD_ALREADY_EXISTS),
    ERROR_NO_RECORD(AccountConstant.NO_RECORDS_FOUND_CODE, AccountConstant.NO_RECORDS_FOUND),
    ERROR_INVALID_REQ(AccountConstant.INVALID_REQUEST_CODE, AccountConstant.INVALID_REQUEST),
    ERROR_GENERAL_MSG(AccountConstant.GENERAL_ERROR_CODE, AccountConstant.GENERAL_ERROR);

    private final String code;
    private final String description;

    private AccountError(String code, String description) {
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
