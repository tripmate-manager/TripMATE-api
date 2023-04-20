package com.tripmate.domain.plans.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "플랜 속성 정보 조회 Response VO")
public class PlanAttributeVO {
    @Schema(description = "회원 번호", example = "1")
    private int memberNo;

    @Schema(description = "플랜 번호", example = "1")
    private int planNo;

    @Schema(description = "속성 번호", example = "1")
    private int attributeNo;

    @Schema(description = "플랜 속성명 (여행테마 또는 해시태그명)")
    private String attributeName;

    @Schema(description = "속성타입코드(10: 해시태그, 20: 여행테마)")
    private String attributeTypeCode;
}
