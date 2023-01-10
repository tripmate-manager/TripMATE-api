package com.tripmate.api;

import com.tripmate.domain.common.vo.ResponseWrapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.mail.MessagingException;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(MessagingException.class)
    public ResponseWrapper<String> handleMessagingException() {
        return ResponseWrapper.<String>builder()
                              .code("8888")
                              .message("messaging error")
                              .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseWrapper<String> handleException() {
        return ResponseWrapper.<String>builder()
                              .code("9999")
                              .message("unknown exception")
                              .build();
    }
}
