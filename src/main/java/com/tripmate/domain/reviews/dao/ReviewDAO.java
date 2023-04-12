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

    public List<ReviewVO> searchReviewList(String dailyPlanNo) {
        return sqlSession.getMapper(ReviewDAOMapper.class).searchReviewList(dailyPlanNo);
    }

    public int deleteReview(DeleteReviewDTO deleteReviewDTO) {
        return sqlSession.getMapper(ReviewDAOMapper.class).deleteReview(deleteReviewDTO);
    }

    public int updateReviewAverageScoreWithDeleteReviewDTO(DeleteReviewDTO deleteReviewDTO) {
        return sqlSession.getMapper(ReviewDAOMapper.class).updateReviewAverageScoreWithDeleteReviewDTO(deleteReviewDTO);
    }
}
