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
@Schema(description = "인기 플랜 조회 Response VO")
public class PopularPlanVO {

    @Schema(description = "플랜 번호", example = "1")
    private int planNo;

    @Schema(description = "플랜 제목", example = "플랜제목")
    private String planTitle;

    @Schema(description = "플랜 리더 이름", example = "Y")
    private String leaderNickName;

    @Schema(description = "플랜 찜 여부(카운트)", example = "1")
    private int planLikeCnt;

}
