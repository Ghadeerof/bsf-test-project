package com.bsf.constant;

public class AccountConstant {

    public static final String ACCOUNT_FIELD = "Account";
    public static final String ACCOUNT_ID = "AccountId";

    public static final String CREATE_ERROR_CODE = "ERR_BSF_ACC_01";
    public static final String UPDATE_ERROR_CODE = "ERR_BSF_ACC_03";

    public static final String DELETE_ERROR_CODE = "ERR_BSF_ACC_04";

    public static final String RECORD_DOES_NOT_EXIST_CODE = "ERR_BSF_ACC_05";
    public static final String RECORD_ALREADY_EXISTS_CODE = "ERR_BSF_ACC_06";
    public static final String NO_RECORDS_FOUND_CODE = "ERR_BSF_ACC_07";
    public static final String INVALID_REQUEST_CODE = "ERR_BSF_ACC_08";
    public static final String GENERAL_ERROR_CODE = "ERR_BSF_ACC_09";


    public static final String ERROR_OCCURRED = "Error occurred while ";
    public static final String PLACE_HOLDER = "%s";
    public static final String CREATE_ERROR = ERROR_OCCURRED + "adding " + PLACE_HOLDER;
    public static final String UPDATE_ERROR = ERROR_OCCURRED + "updating " + PLACE_HOLDER;

    public static final String DELETE_ERROR = ERROR_OCCURRED + "removing " + PLACE_HOLDER;


    public static final String READ_ERROR = ERROR_OCCURRED + "finding " + PLACE_HOLDER;
    public static final String RECORD_DOES_NOT_EXIST = "Record does not exist";
    public static final String RECORD_ALREADY_EXISTS = "Record already exists";
    public static final String NO_RECORDS_FOUND = "No records found";
    public static final String INVALID_REQUEST = "Invalid request";

    public static final String GENERAL_ERROR = "Something went wrong. Please try again.";


}
