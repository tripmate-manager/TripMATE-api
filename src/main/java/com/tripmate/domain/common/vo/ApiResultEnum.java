package com.tripmate.domain.common.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApiResultEnum {
    VALIDATION("8000", "Method Argument Type Mismatch"),
    MESSAGING("8888", "messaging error"),
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
