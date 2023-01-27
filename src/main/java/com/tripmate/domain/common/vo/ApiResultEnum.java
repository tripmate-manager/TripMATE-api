package com.tripmate.domain.common.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApiResultEnum {
    SUCCESS("0000", "success"),
    MESSAGING("8888", "messaging error"),
    VALIDATION("9000", "Method Argument Type Mismatch"),
    WRONG_PARAMETER("9001", "wrong parameter value"),
    NO_RESULT("9002", "result not found"),

    UNKNOWN("9999", "unknown exception");

    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
