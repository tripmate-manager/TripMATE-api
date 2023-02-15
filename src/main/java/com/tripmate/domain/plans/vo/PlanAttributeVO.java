package com.tripmate.domain.plans.vo;

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
@Schema(description = "플랜 속성 정보 조회 Response VO")
public class PlanAttributeVO {
    @Hidden
    private int memberNo;

    @Hidden
    private int planNo;

    @Hidden
    private int attributeNo;

    @Schema(description = "플랜 속성명 (여행테마 또는 해시태그명)")
    private String attributeName;

    @Pattern(regexp = "^[12]0$", message = "속성타입코드는 10, 20만 입력 가능합니다.")
    @Schema(description = "속성타입코드(10: 해시태그, 20: 여행테마)")
    private String attributeTypeCode;
}
