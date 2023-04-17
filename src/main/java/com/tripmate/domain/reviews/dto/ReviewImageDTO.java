package com.tripmate.domain.reviews.dto;

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
@Schema(description = "리뷰 이미지 조회 Response DTO")
public class ReviewImageDTO {
    @Schema(description = "리뷰 번호", example = "1")
    private String reviewNo;

    @Schema(description = "회원 번호", example = "1")
    private String memberNo;

    @Schema(description = "리뷰 이미지 번호", example = "1")
    private String reviewImageNo;

    @Schema(description = "리뷰 이미지명", example = "image")
    private String reviewImageName;

    @Schema(description = "리뷰 이미지 경로", example = "/image.jpg")
    private String reviewImagePath;

    @Schema(description = "리뷰 이미지 용량 리스트", example = "1")
    private String reviewImageVolume;
}