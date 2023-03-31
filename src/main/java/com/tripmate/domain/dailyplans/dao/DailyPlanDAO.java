package com.tripmate.domain.dailyplans.dao;

import com.tripmate.domain.dailyplans.dao.mapper.DailyPlanDAOMapper;
import com.tripmate.domain.dailyplans.dto.DailyPlanDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DailyPlanDAO {
    private final SqlSession sqlSession;

    @Autowired
    public DailyPlanDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int insertDailyPlan(DailyPlanDTO dailyPlanDTO) {
        return sqlSession.getMapper(DailyPlanDAOMapper.class).insertDailyPlan(dailyPlanDTO);
    }

    public int updatePostMappingYn(DailyPlanDTO dailyPlanDTO) {
        return sqlSession.getMapper(DailyPlanDAOMapper.class).updatePostMappingYn(dailyPlanDTO);
    }

    public int deleteDailyPlan(DailyPlanDTO dailyPlanDTO) {
        return sqlSession.getMapper(DailyPlanDAOMapper.class).deleteDailyPlan(dailyPlanDTO);
    }
}
