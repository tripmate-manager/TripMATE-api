package com.tripmate.domain.dailyplans.dto;

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
@Schema(description = "플랜 일자별 데일리플랜 목록 조회 Request DTO")
public class DailyPlanByDayDTO {
    @Schema(description = "플랜 번호", example = "test")
    private String planNo;

    @Schema(description = "플랜 일자")
    private String dayGroup;
}
