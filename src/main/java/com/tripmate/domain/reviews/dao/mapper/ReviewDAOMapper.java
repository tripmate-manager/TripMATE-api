package com.tripmate.domain.reviews.dao.mapper;

import com.tripmate.domain.reviews.dto.DeleteReviewDTO;
import com.tripmate.domain.reviews.dto.ReviewDTO;
import com.tripmate.domain.reviews.dto.ReviewImageDTO;
import com.tripmate.domain.reviews.vo.ReviewVO;

import java.util.List;

public interface ReviewDAOMapper {
    int insertReview(ReviewDTO reviewDTO);
    int insertReviewImage(List<ReviewImageDTO> reviewImageDTOList);
    int updateReviewAverageScoreWithReviewDTO(ReviewDTO reviewDTO);
    int getReviewRegistrationNoCnt(ReviewDTO reviewDTO);
    List<ReviewVO> searchReviewList(String dailyPlanNo);
    int getReviewImageCnt(String reviewNo);
    int deleteReview(DeleteReviewDTO deleteReviewDTO);
    int updateReviewAverageScoreWithDeleteReviewDTO(DeleteReviewDTO deleteReviewDTO);
    int getDailyPlanReviewCnt(String reviewNo);
    int deleteDailyPlanReview(String dailyPlanNo);
}