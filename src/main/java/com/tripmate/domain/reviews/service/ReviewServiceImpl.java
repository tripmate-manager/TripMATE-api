package com.tripmate.domain.reviews.service;

import com.tripmate.common.exception.GuideMessageException;
import com.tripmate.domain.reviews.dao.ReviewDAO;
import com.tripmate.domain.reviews.dto.ReviewDTO;
import com.tripmate.domain.reviews.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewDAO reviewDAO;

    @Autowired
    public ReviewServiceImpl(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    @Override
    @Transactional
    public String insertReview(String dailyPlanNo, ReviewDTO reviewDTO) {
        if (reviewDAO.insertReview(reviewDTO) != 1) {
            throw new GuideMessageException("리뷰 생성 중 오류가 발생하였습니다.");
        }

        if (reviewDAO.insertReviewImage(reviewDTO) != reviewDTO.getReviewImageList().size()) {
            throw new GuideMessageException("리뷰 이미지 저장 처리 중 오류가 발생하였습니다.");
        }

        if (reviewDAO.updateDailyPlanReviewAverageScore(reviewDTO) != 1) {
            throw new GuideMessageException("리뷰 평점 업데이트 처리 중 오류가 발생하였습니다.");
        }

        return reviewDTO.getReviewNo();
    }

    @Override
    public List<ReviewVO> searchReviewList(String dailyPlanNo) {
        return reviewDAO.searchReviewList(dailyPlanNo);
    }
}