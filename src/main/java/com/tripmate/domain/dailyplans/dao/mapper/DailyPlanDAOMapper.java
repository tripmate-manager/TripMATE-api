package com.tripmate.domain.dailyplans.dao.mapper;

import com.tripmate.domain.dailyplans.dto.DailyPlanDTO;

public interface DailyPlanDAOMapper {
    int insertDailyPlan(DailyPlanDTO dailyPlanDTO);
    int updatePostMappingYn(DailyPlanDTO dailyPlanDTO);
    int deleteDailyPlan(DailyPlanDTO dailyPlanDTO);
}
