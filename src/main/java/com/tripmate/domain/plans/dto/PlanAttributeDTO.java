package com.tripmate.domain.plans.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "플랜 속성 삭제 Request DTO")
public class PlanAttributeDTO {
    @Hidden
    private int planNo;

    @Pattern(regexp = "^[12]0$", message = "속성타입코드는 10, 20만 입력 가능합니다.")
    @Schema(description = "속성타입코드(10: 해시태그, 20: 여행테마)")
    private String attributeTypeCode;
}