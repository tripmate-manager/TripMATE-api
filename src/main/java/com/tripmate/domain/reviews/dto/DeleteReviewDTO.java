package com.tripmate.domain.reviews.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "리뷰 삭제 Request DTO")
public class DeleteReviewDTO {
    @NotBlank(message = "리뷰 번호를 입력해주세요.")
    private String reviewNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    @Schema(description = "회원 번호", example = "1")
    private String memberNo;

    @NotBlank(message = "데일리플랜 번호를 입력해주세요.")
    @Schema(description = "데일리플랜 번호", example = "1")
    private String dailyPlanNo;
}