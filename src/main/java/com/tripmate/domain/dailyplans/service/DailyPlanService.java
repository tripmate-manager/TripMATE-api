package com.tripmate.domain.dailyplans.service;

import com.tripmate.domain.dailyplans.dto.DailyPlanByDayDTO;
import com.tripmate.domain.dailyplans.dto.DailyPlanCntVO;
import com.tripmate.domain.dailyplans.dto.DailyPlanDTO;
import com.tripmate.domain.dailyplans.dto.DailyPlanVO;
import com.tripmate.domain.dailyplans.dto.DeleteDailyPlanDTO;

import java.util.List;

public interface DailyPlanService {
    boolean insertDailyPlan(DailyPlanDTO dailyPlanDTO);
    boolean deleteDailyPlan(String dailyPlanNo, DeleteDailyPlanDTO deleteDailyPlanDTO);
    List<DailyPlanCntVO> searchDailyPlanCntByDay(String planNo);
    List<DailyPlanVO> searchDailyPlanListByDay(DailyPlanByDayDTO dailyPlanByDayDTO);
}
