package com.tripmate.domain.reviews.dao;

import com.tripmate.domain.reviews.dao.mapper.ReviewDAOMapper;
import com.tripmate.domain.reviews.dto.ReviewDTO;
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

    public int insertReviewImage(ReviewDTO reviewDTO) {
        return sqlSession.getMapper(ReviewDAOMapper.class).insertReviewImage(reviewDTO);
    }

    public int updateDailyPlanReviewAverageScore(ReviewDTO reviewDTO) {
        return sqlSession.getMapper(ReviewDAOMapper.class).updateDailyPlanReviewAverageScore(reviewDTO);
    }

    public List<ReviewVO> searchReviewList(String dailyPlanNo) {
        return sqlSession.getMapper(ReviewDAOMapper.class).searchReviewList(dailyPlanNo);
    }
}
