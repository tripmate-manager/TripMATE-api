package com.tripmate.domain.dailyplans.dao.mapper;

import com.tripmate.domain.dailyplans.dto.DailyPlanByDayDTO;
import com.tripmate.domain.dailyplans.dto.DailyPlanCntVO;
import com.tripmate.domain.dailyplans.dto.DailyPlanDTO;
import com.tripmate.domain.dailyplans.dto.DailyPlanVO;
import com.tripmate.domain.dailyplans.dto.DeleteDailyPlanDTO;

import java.util.List;

public interface DailyPlanDAOMapper {
    int insertDailyPlan(DailyPlanDTO dailyPlanDTO);
    int updatePostMappingYnWithPostNo(DailyPlanDTO dailyPlanDTO);
    int deleteDailyPlan(String dailyPlanNo);
    int updatePostMappingYnWithDailyPlanNo(DeleteDailyPlanDTO deleteDailyPlanDTO);
    List<DailyPlanCntVO> searchDailyPlanCntByDay(String planNo);
    List<DailyPlanVO> searchDailyPlanListByDay(DailyPlanByDayDTO dailyPlanByDayDTO);
}
