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
@Schema(description = "데일리플랜 조회 Response VO")
public class DailyPlanItemVO {

    @Schema(description = "데일리플랜 번호", example = "1")
    private String dailyPlanNo;

    @Schema(description = "게시글 번호", example = "1")
    private String postNo;

    @Schema(description = "데일리플랜 일시", example = "2023-01-01 15:00:00.0")
    private String dailyPlanDateTime;

    @Schema(description = "알림 설정 여부", example = "N")
    private String notificationYn;

    @Schema(description = "리뷰 평점", example = "1.0")
    private String reviewAverageScore;

    @Schema(description = "게시글 타입 코드(10: 숙소, 20: 관광지, 30: 식당, 90: 기타)", example = "10")
    private String postTypeCode;

    @Schema(description = "게시글 제목", example = "게시글 제목")
    private String postTitle;

    @Schema(description = "게시글 본문", example = "게시글 본문")
    private String postContents;

    @Schema(description = "장소 위치(주소)", example = "서울 강서구 마곡동로 36")
    private String spotAddress;

    @Schema(description = "게시글 작성자", example = "1")
    private String registrationNo;

}
