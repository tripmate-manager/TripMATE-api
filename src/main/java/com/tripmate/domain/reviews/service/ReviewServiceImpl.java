package com.tripmate.domain.reviews.service;

import com.tripmate.common.exception.GuideMessageException;
import com.tripmate.domain.reviews.dao.ReviewDAO;
import com.tripmate.domain.reviews.dto.DeleteReviewDTO;
import com.tripmate.domain.reviews.dto.ReviewDTO;
import com.tripmate.domain.reviews.dto.ReviewImageDTO;
import com.tripmate.domain.reviews.vo.ReviewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        if (reviewDAO.getReviewRegistrationNoCnt(reviewDTO) != 0) {
            throw new GuideMessageException("이미 작성한 리뷰가 존재합니다.");
        }

        if (reviewDAO.insertReview(reviewDTO) != 1) {
            throw new GuideMessageException("리뷰 생성 중 오류가 발생하였습니다.");
        }

        List<ReviewImageDTO> reviewImageDTOList = reviewDTO.getReviewImageList().stream()
                .map(reviewImageDTO -> ReviewImageDTO.builder()
                        .reviewNo(reviewDTO.getReviewNo())
                        .memberNo(reviewDTO.getMemberNo())
                        .reviewImageName(reviewImageDTO.getReviewImageName())
                        .reviewImagePath(reviewImageDTO.getReviewImagePath())
                        .reviewImageVolume(reviewImageDTO.getReviewImageVolume())
                        .build())
                .collect(Collectors.toList());

        if (!reviewImageDTOList.isEmpty()) {
            if (reviewDAO.insertReviewImage(reviewImageDTOList) != reviewDTO.getReviewImageList().size()) {
                throw new GuideMessageException("리뷰 이미지 저장 처리 중 오류가 발생하였습니다.");
            }
        }

        if (reviewDAO.updateReviewAverageScoreWithReviewDTO(reviewDTO) != 1) {
            throw new GuideMessageException("리뷰 평점 업데이트 처리 중 오류가 발생하였습니다.");
        }

        if (reviewDAO.updatePlanAchieveRate(reviewDTO.getPlanNo()) != 1) {
            throw new GuideMessageException("플랜 성취율 업데이트 처리 중 오류가 발생하였습니다.");
        }

        return reviewDTO.getReviewNo();
    }

    @Override
    public List<ReviewVO> searchReviewList(String dailyPlanNo) {
        return reviewDAO.searchReviewList(dailyPlanNo);
    }

    @Override
    @Transactional
    public List<String> deleteReview(DeleteReviewDTO deleteReviewDTO) {
        int reviewImageCnt = reviewDAO.getReviewImageCntWithReviewNo(deleteReviewDTO.getReviewNo());
        List<String> reviewImageNameList = new ArrayList<>();

        if (reviewImageCnt == 0) {
            if (reviewDAO.deleteReview(deleteReviewDTO) != 1) {
                throw new GuideMessageException("리뷰 삭제 처리 중 오류가 발생하였습니다.");
            }
        } else {
            reviewImageNameList = reviewDAO.searchReviewImageNameListWithReviewNo(deleteReviewDTO.getReviewNo());
            if (reviewDAO.deleteReview(deleteReviewDTO) != reviewImageCnt + 1) {
                throw new GuideMessageException("리뷰 삭제 처리 중 오류가 발생하였습니다.");
            }
        }

        if (reviewDAO.updateReviewAverageScoreWithDeleteReviewDTO(deleteReviewDTO) != 1) {
            throw new GuideMessageException("리뷰 평점 업데이트 처리 중 오류가 발생하였습니다.");
        }

        if (reviewDAO.updatePlanAchieveRate(deleteReviewDTO.getPlanNo()) != 1) {
            throw new GuideMessageException("플랜 성취율 업데이트 처리 중 오류가 발생하였습니다.");
        }


        return reviewImageNameList;
    }
}