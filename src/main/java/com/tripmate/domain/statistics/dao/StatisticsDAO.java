package com.tripmate.domain.statistics.dao;

import com.tripmate.domain.statistics.dao.mapper.StatisticsDAOMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StatisticsDAO {
    private final SqlSession sqlSession;

    public int insertPlanLikeStatistics(String statisticsTermCode) {
        return sqlSession.getMapper(StatisticsDAOMapper.class).insertPlanLikeStatistics(statisticsTermCode);
    }

    public int insertSearchKeywordStatistics(String statisticsTermCode) {
        return sqlSession.getMapper(StatisticsDAOMapper.class).insertSearchKeywordStatistics(statisticsTermCode);
    }

    public int insertPlanAttributeStatistics(String statisticsTermCode) {
        return sqlSession.getMapper(StatisticsDAOMapper.class).insertPlanAttributeStatistics(statisticsTermCode);
    }

}
