package com.tripmate.domain.reviews.dao.mapper;

import com.tripmate.domain.reviews.dto.ReviewDTO;
import com.tripmate.domain.reviews.vo.ReviewVO;

import java.util.List;

public interface ReviewDAOMapper {
    int insertReview(ReviewDTO reviewDTO);
    int insertReviewImage(ReviewDTO reviewDTO);
    int updateDailyPlanReviewAverageScore(ReviewDTO reviewDTO);
    List<ReviewVO> searchReviewList(String dailyPlanNo);
}