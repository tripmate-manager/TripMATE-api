package com.tripmate.domain.dailyplans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "일별 데일리플랜 카운트 수 조회 Response VO")
public class DailyPlanCntVO {
    @NotBlank
    private String dayGroup;

    @NotBlank
    private String dailyPlanCnt;
}
