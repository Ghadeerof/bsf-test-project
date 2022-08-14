package com.bsf.enums;

public enum Domain {
    ACCOUNT("ACCOUNT", "Account"),
    TRANSACTION("TRANSACTION", "Transaction");

    private final String code;
    private final String description;

    private Domain(String code, String description) {
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