package com.tripmate.api;

import com.tripmate.domain.common.vo.ApiResultEnum;
import com.tripmate.domain.common.vo.ResponseWrapper;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.mail.MessagingException;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseWrapper<String> handleValidException(MethodArgumentNotValidException e) {
        return ResponseWrapper.<String>builder()
                              .code(ApiResultEnum.VALIDATION.getCode())
                              .message(ApiResultEnum.VALIDATION.getMessage() + " [" + e.getAllErrors()
                                                                                       .get(0)
                                                                                       .getDefaultMessage() + "]")
                              .build();
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseWrapper<String> handleMessagingException() {
        return ResponseWrapper.<String>builder()
                              .code(ApiResultEnum.MESSAGING.getCode())
                              .message(ApiResultEnum.MESSAGING.getMessage())
                              .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseWrapper<String> handleException() {
        return ResponseWrapper.<String>builder()
                              .code(ApiResultEnum.UNKNOWN.getCode())
                              .message(ApiResultEnum.UNKNOWN.getMessage())
                              .build();
    }
}
