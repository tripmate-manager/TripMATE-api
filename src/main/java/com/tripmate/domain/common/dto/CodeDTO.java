package com.tripmate.domain.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CodeDTO {
    @NonNull
    @NotBlank(message = "공통코드를 입력하세요")
    @Schema(description = "공통 코드", example = "MB001")
    private String commonCode;
    @NonNull
    @NotBlank(message = "공통상세코드를 입력하세요")
    @Schema(description = "공통 상세 코드", example = "10")
    private String commonDetailCode;
}