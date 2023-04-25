package com.tripmate.domain.plans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "플랜메이트 추가 Request DTO")
public class PlanMateDTO {
    @Schema(description = "플랜 번호", example = "1")
    private String planNo;

    @NotBlank
    @Schema(description = "회원 번호", example = "1")
    private String memberNo;

    @Pattern(regexp = "^[YN]$", message = "플랜 리더(생성자) 여부는 Y, N만 입력 가능합니다.")
    @Schema(description = "플랜 리더 여부", example = "Y")
    private String leadYn;
}
