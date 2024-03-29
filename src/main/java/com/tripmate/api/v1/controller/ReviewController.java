package com.tripmate.api.v1.controller;

import com.tripmate.common.config.ValidationSequence;
import com.tripmate.domain.common.vo.ResponseWrapper;
import com.tripmate.domain.reviews.dto.DeleteReviewDTO;
import com.tripmate.domain.reviews.dto.ReviewDTO;
import com.tripmate.domain.reviews.service.ReviewService;
import com.tripmate.domain.reviews.vo.ReviewVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Collections;

@Slf4j
@RestController
@Tag(name = "Review", description = "리뷰 API")
@RequestMapping("v1/reviews")
@Validated
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "데일리플랜 리뷰 생성", description = "데일리플랜 리뷰를 생성합니다.")
    @PostMapping("/{dailyPlanNo}")
    public ResponseWrapper<String> insertReview(@PathVariable(value = "dailyPlanNo") @NotBlank @Schema(description = "데일리플랜 번호", example = "1") String dailyPlanNo,
                                                @Validated(ValidationSequence.class) @RequestBody ReviewDTO reviewDTO) {
        return ResponseWrapper.<String>builder()
                .data(Collections.singletonList(reviewService.insertReview(dailyPlanNo, reviewDTO)))
                .build();
    }

    @Operation(summary = "데일리플랜 리뷰 조회", description = "데일리플랜 리뷰 목록을 조회합니다.")
    @GetMapping("/{dailyPlanNo}")
    public ResponseWrapper<ReviewVO> searchReviewList(@PathVariable(value = "dailyPlanNo") @NotBlank @Schema(description = "데일리플랜 번호", example = "1") String dailyPlanNo) {
        return ResponseWrapper.<ReviewVO>builder()
                .data(reviewService.searchReviewList(dailyPlanNo))
                .build();
    }

    @Operation(summary = "데일리플랜 리뷰 삭제", description = "데일리플랜 리뷰를 삭제합니다.")
    @PostMapping("/delete-review")
    public ResponseWrapper<String> deleteReview(@Valid @RequestBody DeleteReviewDTO deleteReviewDTO) {
        return ResponseWrapper.<String>builder()
                .data(reviewService.deleteReview(deleteReviewDTO))
                .build();
    }
}
