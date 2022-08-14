package com.bsf.advice;

import com.bsf.constant.AccountConstant;
import com.bsf.constant.TransactionConstant;
import com.bsf.dto.RequestWrapperDTO;
import com.bsf.dto.StatusResponse;
import com.bsf.enums.TransactionError;
import com.bsf.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class MasterControllerAdvice extends ResponseEntityExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(MasterControllerAdvice.class);

    @ExceptionHandler(AccountCheckedException.class)
    public ResponseEntity<RequestWrapperDTO> handleAccountCheckedException(AccountCheckedException ex) {
        RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
        List<StatusResponse> errors = Stream.of(new StatusResponse(ex.getCode(), ex.getMessage()))
                .collect(Collectors.toList());
        reqWDTO.setErrors(errors);
        reqWDTO.setTimeStamp(LocalDateTime.now());
        reqWDTO.setStatus(Stream.of(new StatusResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())).collect(Collectors.toList()));

        if (log.isErrorEnabled())
            log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(reqWDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccountUncheckedException.class)
    public ResponseEntity<RequestWrapperDTO> handleAccountUncheckedException(AccountUncheckedException ex) {
        RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
        reqWDTO.setErrors(Stream.of(new StatusResponse(ex.getCode(), ex.getMessage()))
                .collect(Collectors.toList()));
        reqWDTO.setTimeStamp(LocalDateTime.now());

        reqWDTO.setStatus(Stream.of(new StatusResponse(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase()))
                .collect(Collectors.toList()));

        return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
    }

    @ExceptionHandler(AccountRecordNotFoundException.class)
    public ResponseEntity<RequestWrapperDTO> handleAccountRecordNotFoundException(AccountRecordNotFoundException ex) {
        RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
        reqWDTO.setErrors(Stream.of(new StatusResponse(ex.getCode(), ex.getMessage()))
                .collect(Collectors.toList()));
        reqWDTO.setTimeStamp(LocalDateTime.now());

        reqWDTO.setStatus(Stream.of(new StatusResponse(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase()))
                .collect(Collectors.toList()));

        return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
    }

    @ExceptionHandler(DuplicationErrorException.class)
    public ResponseEntity<RequestWrapperDTO> handleDuplicateNotFoundException(DuplicationErrorException ex) {
        RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
        List<StatusResponse> errors = Stream.of(new StatusResponse(ex.getCode(), ex.getMessage()))
                .collect(Collectors.toList());
        reqWDTO.setErrors(errors);
        reqWDTO.setTimeStamp(LocalDateTime.now());

        HttpStatus code = getHttpStatus(ex.getCode());
        reqWDTO.setStatus(Stream.of(new StatusResponse(String.valueOf(code.value()), code.getReasonPhrase()))
                .collect(Collectors.toList()));

        return new ResponseEntity<>(reqWDTO, code);
    }

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<RequestWrapperDTO> handleInvalidTransactionException(
            InvalidTransactionException ex) {
        RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
        List<StatusResponse> errors = Stream.of(new StatusResponse(ex.getCode(), ex.getMessage()))
                .collect(Collectors.toList());
        reqWDTO.setErrors(errors);
        reqWDTO.setTimeStamp(LocalDateTime.now());

        reqWDTO.setStatus(Stream.of(new StatusResponse(TransactionConstant.SUCCESS_CODE, TransactionConstant.SUCCESS_CODE_DESC))
                .collect(Collectors.toList()));

        if (log.isErrorEnabled())
            log.error(String.valueOf(ex.getStackTrace()));

        return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
    }

    @ExceptionHandler(NotEnoughTransactionBalance.class)
    public ResponseEntity<RequestWrapperDTO> handleINotEnoughTransactionBalanceException(
            NotEnoughTransactionBalance ex) {
        RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
        List<StatusResponse> errors = Stream.of(new StatusResponse(ex.getCode(), ex.getMessage()))
                .collect(Collectors.toList());
        reqWDTO.setErrors(errors);
        reqWDTO.setTimeStamp(LocalDateTime.now());

        reqWDTO.setStatus(Stream.of(new StatusResponse(TransactionError.ERROR_NOT_ENOUGH_BALANCE.getCode(),
                        TransactionError.ERROR_NOT_ENOUGH_BALANCE.getDescription()))
                .collect(Collectors.toList()));

        if (log.isErrorEnabled())
            log.error(String.valueOf(ex.getStackTrace()));

        return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
    }

    @ExceptionHandler(TransactionCheckedException.class)
    public ResponseEntity<RequestWrapperDTO> handleTransactionCheckedException(TransactionCheckedException ex) {
        RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
        List<StatusResponse> errors = Stream.of(new StatusResponse(ex.getCode(), ex.getMessage()))
                .collect(Collectors.toList());
        reqWDTO.setErrors(errors);
        reqWDTO.setTimeStamp(LocalDateTime.now());
        reqWDTO.setStatus(Stream.of(new StatusResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())).collect(Collectors.toList()));

        if (log.isErrorEnabled())
            log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(reqWDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TransactionUncheckedException.class)
    public ResponseEntity<RequestWrapperDTO> handleTransactionUncheckedException(TransactionUncheckedException ex) {
        RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
        reqWDTO.setErrors(Stream.of(new StatusResponse(ex.getCode(), ex.getMessage()))
                .collect(Collectors.toList()));
        reqWDTO.setTimeStamp(LocalDateTime.now());

        reqWDTO.setStatus(Stream.of(new StatusResponse(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase()))
                .collect(Collectors.toList()));

        return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
    }

    @ExceptionHandler(TransactionRecordNotFoundException.class)
    public ResponseEntity<RequestWrapperDTO> handleTransactionRecordNotFoundException(TransactionRecordNotFoundException ex) {
        RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
        reqWDTO.setErrors(Stream.of(new StatusResponse(ex.getCode(), ex.getMessage()))
                .collect(Collectors.toList()));
        reqWDTO.setTimeStamp(LocalDateTime.now());

        reqWDTO.setStatus(Stream.of(new StatusResponse(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase()))
                .collect(Collectors.toList()));

        return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
    }

    private HttpStatus getHttpStatus(String code) {
        if (code.equals(AccountConstant.CREATE_ERROR_CODE) || code.equals(AccountConstant.DELETE_ERROR_CODE)
                || code.equals(AccountConstant.INVALID_REQUEST_CODE)) {
            return HttpStatus.BAD_REQUEST;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
