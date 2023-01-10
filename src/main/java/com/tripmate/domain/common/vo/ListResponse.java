package com.tripmate.domain.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Builder
@Getter
public class ListResponse<T> {
    @NonNull
    @Builder.Default
    @Schema(description = "api response code", example = "200")
    protected String code = "200";
    @NonNull
    @Builder.Default
    @Schema(description = "api response message", example = "success")
    protected String message = "success";
    @Schema(description = "api response data list")
    private List<T> data;
}
