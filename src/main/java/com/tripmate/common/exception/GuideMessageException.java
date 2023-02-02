package com.tripmate.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "result message")
public class GuideMessageException extends RuntimeException {
    public GuideMessageException() {
    }

    public GuideMessageException(String message) {
        super(message);
    }

    public GuideMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public GuideMessageException(Throwable cause) {
        super(cause);
    }

    public GuideMessageException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
