package com.bsf.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RequestWrapperDTO {
    private Object request;
    private Object response;
    private List<StatusResponse> errors;
    private List<StatusResponse> status;
    private LocalDateTime timeStamp;

}