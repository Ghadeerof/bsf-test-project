package com.bsf.utils;

import com.bsf.constant.MasterConstant;
import com.bsf.dto.RequestWrapperDTO;
import com.bsf.dto.StatusResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonUtils {

    public static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private CommonUtils(){
    }


    public static RequestWrapperDTO mapToWrapper(Object request, Object response) {
        RequestWrapperDTO dto = new RequestWrapperDTO();
        dto.setRequest(request);
        dto.setResponse(response);
        dto.setStatus(Stream.of(new StatusResponse(MasterConstant.SUCCESS_CODE, MasterConstant.SUCCESS_CODE_DESC))
                .collect(Collectors.toList()));
        dto.setTimeStamp(LocalDateTime.now());
        return dto;
    }

    public static Map<String, Object> buildForObjectMapper(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
