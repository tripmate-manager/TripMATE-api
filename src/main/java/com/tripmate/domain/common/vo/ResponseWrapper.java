package com.tripmate.domain.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Builder
@Getter
public class ResponseWrapper<T> {
    @NonNull
    @Builder.Default
    @Schema(description = "api response code", example = "200")
    private String code = "200";
    @NonNull
    @Builder.Default
    @Schema(description = "api response message", example = "success")
    private String message = "success";
    @Schema(description = "api response data list")
    private List<T> data;
}
