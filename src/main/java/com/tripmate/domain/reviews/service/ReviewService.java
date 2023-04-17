package com.tripmate.domain.reviews.service;

import com.tripmate.domain.reviews.dto.DeleteReviewDTO;
import com.tripmate.domain.reviews.dto.ReviewDTO;
import com.tripmate.domain.reviews.vo.ReviewVO;

import java.util.List;

public interface ReviewService {
    String insertReview(String dailyPlanNo, ReviewDTO reviewDTO);
    List<ReviewVO> searchReviewList(String dailyPlanNo);
    List<String> deleteReview(DeleteReviewDTO deleteReviewDTO);
}