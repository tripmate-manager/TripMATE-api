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
@Schema(description = "데일리플랜 조회 Response VO")
public class DailyPlanVO {
    @NotBlank
    private String dailyPlanNo;

    @NotBlank
    @Schema(description = "플랜 번호")
    private String planNo;

    @NotBlank
    @Schema(description = "게시글 번호")
    private String postNo;

    @NotBlank
    @Schema(description = "데일리플랜 그룹 번호")
    private String dayGroupNo;

    @NotBlank
    @Schema(description = "데일리플랜 일시")
    private String dailyPlanDateTime;

    @NotBlank
    @Schema(description = "알림 설정 여부")
    private String notificationYn;

    @Schema(description = "리뷰 평점")
    private String reviewAverageScore;

    @Schema(description = "게시글 타입 코드")
    private String postTypeCode;

    @NotBlank
    @Schema(description = "게시글 제목")
    private String postTitle;

    @NotBlank
    @Schema(description = "게시글 본문")
    private String postContents;

    @Schema(description = "장소 위치(주소)")
    private String spotAddress;

    @NotBlank
    @Schema(description = "게시글 작성자")
    private String postRegistrationNo;
}
