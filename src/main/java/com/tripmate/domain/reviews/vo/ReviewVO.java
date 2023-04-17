package com.tripmate.domain.reviews.vo;

import com.tripmate.domain.reviews.dto.ReviewImageDTO;
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
@Schema(description = "리뷰 조회 Response VO")
public class ReviewVO {
    @Schema(description = "리뷰 번호", example = "1")
    private String reviewNo;

    @Schema(description = "데일리플랜 번호", example = "1")
    private String dailyPlanNo;

    @Schema(description = "게시글 타입코드(10: 숙소, 20: 관광지, 30: 식당, 90: 기타", example = "10")
    private String postTypeCode;

    @Schema(description = "리뷰 '위치' 항목 점수", example = "1")
    private double scoreLocation;

    @Schema(description = "리뷰 '금액' 항목 점수", example = "1")
    private double scoreAmount;

    @Schema(description = "리뷰 '전체' 항목 점수", example = "1")
    private double scoreAll;

    @Schema(description = "리뷰 '편의 시설' 항목 점수", example = "1")
    private double scoreFacility;

    @Schema(description = "리뷰 '위생' 항목 점수", example = "1")
    private double scoreSanitary;

    @Schema(description = "리뷰 '서비스' 항목 점수", example = "1")
    private double scoreService;

    @Schema(description = "리뷰 '음식' 항목 점수", example = "1")
    private double scoreFood;

    @Schema(description = "리뷰 본문", example = "1")
    private String reviewContents;

    @Schema(description = "리뷰 평점", example = "1.0")
    private double reviewAverageScore;

    @Schema(description = "리뷰 작성자 번호", example = "1")
    private String registrationNo;

    @Schema(description = "리뷰 작성자 닉네임", example = "1")
    private String nickName;

    @Schema(description = "리뷰 이미지 리스트", example = "")
    private List<ReviewImageDTO> reviewImageList;
}
