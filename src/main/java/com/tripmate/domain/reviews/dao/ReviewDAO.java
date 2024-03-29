package com.tripmate.domain.reviews.dao;

import com.tripmate.domain.reviews.dao.mapper.ReviewDAOMapper;
import com.tripmate.domain.reviews.dto.DeleteReviewDTO;
import com.tripmate.domain.reviews.dto.ReviewDTO;
import com.tripmate.domain.reviews.dto.ReviewImageDTO;
import com.tripmate.domain.reviews.vo.ReviewVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDAO {
    private final SqlSession sqlSession;

    @Autowired
    public ReviewDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int insertReview(ReviewDTO reviewDTO) {
        return sqlSession.getMapper(ReviewDAOMapper.class).insertReview(reviewDTO);
    }

    public int insertReviewImage(List<ReviewImageDTO> reviewImageDTOList) {
        return sqlSession.getMapper(ReviewDAOMapper.class).insertReviewImage(reviewImageDTOList);
    }

    public int updateReviewAverageScoreWithReviewDTO(ReviewDTO reviewDTO) {
        return sqlSession.getMapper(ReviewDAOMapper.class).updateReviewAverageScoreWithReviewDTO(reviewDTO);
    }

    public int getReviewRegistrationNoCnt(ReviewDTO reviewDTO) {
        return sqlSession.getMapper(ReviewDAOMapper.class).getReviewRegistrationNoCnt(reviewDTO);
    }

    public List<ReviewVO> searchReviewList(String dailyPlanNo) {
        return sqlSession.getMapper(ReviewDAOMapper.class).searchReviewList(dailyPlanNo);
    }

    public int getReviewImageCntWithReviewNo(String reviewNo) {
        return sqlSession.getMapper(ReviewDAOMapper.class).getReviewImageCntWithReviewNo(reviewNo);
    }

    public int getReviewImageCntWithDailyPlanNo(String dailyPlanNo) {
        return sqlSession.getMapper(ReviewDAOMapper.class).getReviewImageCntWithDailyPlanNo(dailyPlanNo);
    }

    public int deleteReview(DeleteReviewDTO deleteReviewDTO) {
        return sqlSession.getMapper(ReviewDAOMapper.class).deleteReview(deleteReviewDTO);
    }

    public int updateReviewAverageScoreWithDeleteReviewDTO(DeleteReviewDTO deleteReviewDTO) {
        return sqlSession.getMapper(ReviewDAOMapper.class).updateReviewAverageScoreWithDeleteReviewDTO(deleteReviewDTO);
    }

    public int getDailyPlanReviewCnt(String dailyPlanNo) {
        return sqlSession.getMapper(ReviewDAOMapper.class).getDailyPlanReviewCnt(dailyPlanNo);
    }

    public int deleteDailyPlanReview(String dailyPlanNo) {
        return sqlSession.getMapper(ReviewDAOMapper.class).deleteDailyPlanReview(dailyPlanNo);
    }

    public List<String> searchReviewImageNameListWithDailyPlanNo(String dailyPlanNo) {
        return sqlSession.getMapper(ReviewDAOMapper.class).searchReviewImageNameListWithDailyPlanNo(dailyPlanNo);
    }

    public List<String> searchReviewImageNameListWithReviewNo(String reviewNo) {
        return sqlSession.getMapper(ReviewDAOMapper.class).searchReviewImageNameListWithReviewNo(reviewNo);
    }

    public int updatePlanAchieveRate(String planNo) {
        return sqlSession.getMapper(ReviewDAOMapper.class).updatePlanAchieveRate(planNo);
    }
}
