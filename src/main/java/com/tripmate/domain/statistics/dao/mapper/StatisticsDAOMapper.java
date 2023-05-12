package com.tripmate.domain.statistics.dao.mapper;

public interface StatisticsDAOMapper {
    int insertPlanLikeStatistics(String statisticsTermCode);
    int insertSearchKeywordStatistics(String statisticsTermCode);
    int insertPlanAttributeStatistics(String statisticsTermCode);
}
