package com.tripmate.domain.dailyplans.dao.mapper;

import com.tripmate.domain.dailyplans.dto.DailyPlanByDayDTO;
import com.tripmate.domain.dailyplans.vo.DailyPlanCntVO;
import com.tripmate.domain.dailyplans.dto.DailyPlanDTO;
import com.tripmate.domain.dailyplans.vo.DailyPlanVO;
import com.tripmate.domain.dailyplans.dto.DeleteDailyPlanDTO;
import com.tripmate.domain.dailyplans.dto.DeleteDailyPlanNotificationDTO;
import com.tripmate.domain.plans.dto.NotificationDTO;

import java.util.List;

public interface DailyPlanDAOMapper {
    int insertDailyPlan(DailyPlanDTO dailyPlanDTO);
    int updatePostMappingYnWithPostNo(DailyPlanDTO dailyPlanDTO);
    int deleteDailyPlan(String dailyPlanNo);
    int getDailyPlanCntWithDailyPlanNo(String dailyPlanNo);
    int updatePostMappingYnWithDailyPlanNo(DeleteDailyPlanDTO deleteDailyPlanDTO);
    List<DailyPlanCntVO> searchDailyPlanCntByDay(String planNo);
    DailyPlanVO searchDailyPlanListByDay(DailyPlanByDayDTO dailyPlanByDayDTO);
    int deleteDailyPlanNotification(DeleteDailyPlanNotificationDTO deleteDailyPlanNotificationDTO);
    int updateDailyPlanNotification(NotificationDTO notificationDTO);
}
