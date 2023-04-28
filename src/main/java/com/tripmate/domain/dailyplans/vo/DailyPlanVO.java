package com.tripmate.domain.dailyplans.vo;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "데일리플랜 조회 Response VO")
public class DailyPlanVO {

    @Hidden
    private String memberNo;

    @Schema(description = "플랜 번호", example = "1")
    private String planNo;

    @Schema(description = "데일리플랜 그룹 번호", example = "1")
    private String dayGroup;

    @Schema(description = "데일리플랜 성취율", example = "1")
    private String achieveRate;

    @Schema(description = "데일리플랜 항목 리스트")
    private List<DailyPlanItemVO> dailyPlanItemList;

}