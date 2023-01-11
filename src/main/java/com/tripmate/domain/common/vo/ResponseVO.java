package com.tripmate.domain.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "Response VO")
public class ResponseVO<T> {
    private T data;

    private int code;
    private String message;

    public ResponseVO(T body) {
        this.data = body;
    }
}