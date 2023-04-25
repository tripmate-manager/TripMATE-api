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
@Schema(description = "지역 정보 조회 Response VO")
public class PlanAddressVO {
    @Schema(description = "회원 번호", example = "1")
    private int memberNo;

    @Schema(description = "플랜 번호", example = "1")
    private int planNo;

    @Schema(description = "주소 번호", example = "1")
    private int addressNo;

    @Schema(description = "여행지 시도명", example = "서울시")
    private String sidoName;

    @Schema(description = "여행지 시군구명", example = "강남구")
    private String sigunguName;
}
