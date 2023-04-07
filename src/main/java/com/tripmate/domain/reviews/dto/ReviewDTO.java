package com.tripmate.domain.reviews.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "리뷰 등록 Request DTO")
public class ReviewDTO {
    @Hidden
    private String reviewNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    @Schema(description = "회원 번호", example = "1")
    private String memberNo;

    @NotBlank(message = "데일리플랜 번호를 입력해주세요.")
    @Schema(description = "데일리플랜 번호", example = "1")
    private String dailyPlanNo;

    @NotBlank(message = "게시글 타입을 입력해주세요.")
    @Pattern(regexp = "^[1239]0$", message = "게시글 타입코드는 10, 20, 30, 90만 입력 가능합니다.")
    @Schema(description = "게시글 타입코드(10: 숙소, 20: 관광지, 30: 식당, 90: 기타", example = "10")
    private String postTypeCode;

    @Min(0) @Max(5)
    @Schema(description = "리뷰 '위치' 항목 점수", example = "1")
    private double scoreLocation;

    @Min(0) @Max(5)
    @Schema(description = "리뷰 '금액' 항목 점수", example = "1")
    private double scoreAmount;

    @Min(0) @Max(5)
    @Schema(description = "리뷰 '전체' 항목 점수", example = "1")
    private double scoreAll;

    @Min(0) @Max(5)
    @Schema(description = "리뷰 '편의 시설' 항목 점수", example = "1")
    private double scoreFacility;

    @Min(0) @Max(5)
    @Schema(description = "리뷰 '위생' 항목 점수", example = "1")
    private double scoreSanitary;

    @Min(0) @Max(5)
    @Schema(description = "리뷰 '서비스' 항목 점수", example = "1")
    private double scoreService;

    @Min(0) @Max(5)
    @Schema(description = "리뷰 '음식' 항목 점수", example = "1", defaultValue = "0")
    private double scoreFood;

    @Size(max = 500, message = "리뷰 본문은 최대 500자까지 입력 가능합니다.")
    @Schema(description = "리뷰 본문", example = "1")
    private String reviewContents;

    @Schema(description = "리뷰 평점", example = "1.0")
    private double reviewAverageScore;

    @Schema(description = "리뷰 이미지 리스트")
    private List<ReviewImageDTO> reviewImageList;
}