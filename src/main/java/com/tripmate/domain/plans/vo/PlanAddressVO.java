package com.tripmate.domain.plans.vo;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "지역 정보 조회 Response VO")
public class PlanAddressVO {
    @Hidden
    private int memberNo;

    @Hidden
    private int planNo;

    @Hidden
    private int addressNo;

    @Schema(description = "여행지 시도명")
    private String sidoName;

    @Schema(description = "여행지 시군구명")
    private String sigunguName;
}
