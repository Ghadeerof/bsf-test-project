package com.bsf.constant;

public class TransactionConstant {

    public static final String TRANSACTION_FIELD = "Transaction";
    public static final String TRANSACTION_ID = "TransactionId";
    public static final String CREATE_ERROR_CODE = "ERR_BSF_TRAN_01";
    public static final String RECORD_DOES_NOT_EXIST_CODE = "ERR_BSF_TRAN_02";
    public static final String RECORD_ALREADY_EXISTS_CODE = "ERR_BSF_TRAN_03";
    public static final String NO_RECORDS_FOUND_CODE = "ERR_BSF_TRAN_04";
    public static final String INVALID_REQUEST_CODE = "ERR_BSF_TRAN_05";
    public static final String GENERAL_ERROR_CODE = "ERR_BSF_TRAN_06";

    public static final String NOT_ENOUGH_BALANCE_ERROR_CODE = "ERR_BSF_TRAN_07";

    public static final String ERROR_OCCURRED = "Error occurred while ";
    public static final String PLACE_HOLDER = "%s";
    public static final String CREATE_ERROR = ERROR_OCCURRED + "adding " + PLACE_HOLDER;
    public static final String READ_ERROR = ERROR_OCCURRED + "finding " + PLACE_HOLDER;
    public static final String RECORD_DOES_NOT_EXIST = "Record does not exist";
    public static final String RECORD_ALREADY_EXISTS = "Record already exists";
    public static final String NO_RECORDS_FOUND = "No records found";
    public static final String INVALID_REQUEST = "Invalid request";
    public static final String GENERAL_ERROR = "Something went wrong. Please try again.";

    public static final String NOT_ENOUGH_BALANCE_ERROR = "Not Enough Balance to initiate transaction";

    public static final String SUCCESS_CODE = "200";
    public static final String SUCCESS_CODE_DESC = "Successfully";


}
