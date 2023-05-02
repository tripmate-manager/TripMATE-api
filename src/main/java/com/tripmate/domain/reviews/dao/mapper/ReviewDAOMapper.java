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
    int getReviewImageCntWithReviewNo(String reviewNo);
    int getReviewImageCntWithDailyPlanNo(String dailyPlanNo);
    int deleteReview(DeleteReviewDTO deleteReviewDTO);
    int updateReviewAverageScoreWithDeleteReviewDTO(DeleteReviewDTO deleteReviewDTO);
    int getDailyPlanReviewCnt(String dailyPlanNo);
    int deleteDailyPlanReview(String dailyPlanNo);
    List<String> searchReviewImageNameListWithDailyPlanNo(String dailyPlanNo);
    List<String> searchReviewImageNameListWithReviewNo(String reviewNo);
    int updatePlanAchieveRate(String planNo);
}