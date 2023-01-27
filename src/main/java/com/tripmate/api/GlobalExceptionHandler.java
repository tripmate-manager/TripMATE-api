package com.tripmate.api;

import com.tripmate.common.exception.NoResultException;
import com.tripmate.common.exception.WrongParameterException;
import com.tripmate.domain.common.vo.ApiResultEnum;
import com.tripmate.domain.common.vo.ResponseWrapper;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.mail.MessagingException;
import javax.validation.ConstraintViolationException;

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

    @ExceptionHandler({
            WrongParameterException.class
            , MissingServletRequestParameterException.class
            , ConstraintViolationException.class
    })
    public ResponseWrapper<String> handleValidException(Exception e) {
        return ResponseWrapper.<String>builder()
                              .code(ApiResultEnum.WRONG_PARAMETER.getCode())
                              .message(ApiResultEnum.WRONG_PARAMETER.getMessage() + " [" + e.getMessage() + "]")
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
    public ResponseWrapper<String> handleException(Exception e) {
        return ResponseWrapper.<String>builder()
                              .code(ApiResultEnum.UNKNOWN.getCode())
                              .message(ApiResultEnum.UNKNOWN.getMessage())
                              .build();
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseWrapper<String> handleNoResultException(NoResultException e) {
        return ResponseWrapper.<String>builder()
                .code(ApiResultEnum.NO_RESULT.getCode())
                .message(e.getMessage())
                .build();
    }
}
