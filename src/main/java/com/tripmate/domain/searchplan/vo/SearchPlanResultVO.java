package com.tripmate.domain.searchplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "플랜 검색 Response VO")
public class SearchPlanResultVO {
    @Schema(description = "플랜 번호", example = "1")
    private int planNo;

    @Schema(description = "플랜제목", example = "플랜제목")
    private String planTitle;

    @Schema(description = "플랜설명", pattern = "yyyy.MM.dd", example = "2023.01.01")
    private String planDescription;

    @Schema(description = "여행 시작 일자", pattern = "yyyy.MM.dd", example = "2023.01.01")
    private String tripStartDate;

    @Schema(description = "여행 종료 일자", pattern = "yyyy.MM.dd", example = "2023.01.01")
    private String tripEndDate;

    @Schema(description = "여행 기간", example = "1")
    private int tripTerm;

    @Schema(description = "좋아요 등록 수", example = "1")
    private int likeRegistrationCnt;

    @Schema(description = "플랜 성취율", example = "15")
    private int achieveRate;

    @Schema(description = "플랜 조회수", example = "1")
    private int views;

    @Schema(description = "플랜 리뷰 평점", example = "1.0")
    private double reviewAverageScore;

    @Schema(description = "여행지 시도명", example = "서울시")
    private String sidoName;

    @Schema(description = "여행지 시군구명", example = "강남구")
    private String sigunguName;

    @Schema(description = "플랜 리더 이름", example = "Y")
    private String leaderNickName;

    @Schema(description = "등록일시", example = "2023-01-01 15:00:00")
    private String registrationDateTime;
}
