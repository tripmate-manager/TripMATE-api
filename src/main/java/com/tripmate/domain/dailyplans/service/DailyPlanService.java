package com.tripmate.domain.dailyplans.service;

import com.tripmate.domain.dailyplans.dto.DailyPlanDTO;

public interface DailyPlanService {
    String insertDailyPlan(DailyPlanDTO dailyPlanDTO);
    boolean deleteDailyPlan(DailyPlanDTO dailyPlanDTO);
}
