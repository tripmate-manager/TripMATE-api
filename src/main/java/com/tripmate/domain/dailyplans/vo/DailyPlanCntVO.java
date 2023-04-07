package com.tripmate.domain.dailyplans.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "일별 데일리플랜 개수 조회 Response VO")
public class DailyPlanCntVO {
    @Schema(description = "플랜 일자", example = "1")
    private String dayGroup;

    @Schema(description = "데일리플랜 개수", example = "1")
    private String dailyPlanCnt;
}
