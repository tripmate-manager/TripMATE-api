package com.tripmate.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "result not found")
public class NoResultException extends RuntimeException {
    public NoResultException() {
    }

    public NoResultException(String message) {
        super(message);
    }

    public NoResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoResultException(Throwable cause) {
        super(cause);
    }

    public NoResultException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
