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
@Schema(description = "플랜 일자별 데일리플랜 목록 조회 Request DTO")
public class DailyPlanByDayDTO {
    @NotBlank(message = "플랜 번호를 입력해주세요.")
    @Schema(description = "플랜 번호")
    private String planNo;

    @NotBlank(message = "플랜 일자를 입력해주세요.")
    @Schema(description = "플랜 일자")
    private String dayGroup;
}
