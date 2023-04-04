package com.tripmate.domain.dailyplans.dao;

import com.tripmate.domain.dailyplans.dao.mapper.DailyPlanDAOMapper;
import com.tripmate.domain.dailyplans.dto.DailyPlanByDayDTO;
import com.tripmate.domain.dailyplans.dto.DailyPlanCntVO;
import com.tripmate.domain.dailyplans.dto.DailyPlanDTO;
import com.tripmate.domain.dailyplans.dto.DailyPlanVO;
import com.tripmate.domain.dailyplans.dto.DeleteDailyPlanDTO;
import com.tripmate.domain.dailyplans.dto.DeleteDailyPlanNotificationDTO;
import com.tripmate.domain.plans.dto.NotificationDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public int updatePostMappingYnWithPostNo(DailyPlanDTO dailyPlanDTO) {
        return sqlSession.getMapper(DailyPlanDAOMapper.class).updatePostMappingYnWithPostNo(dailyPlanDTO);
    }

    public int deleteDailyPlan(String dailyPlanNo) {
        return sqlSession.getMapper(DailyPlanDAOMapper.class).deleteDailyPlan(dailyPlanNo);
    }

    public int getDailyPlanCntWithDailyPlanNo(String dailyPlanNo) {
        return sqlSession.getMapper(DailyPlanDAOMapper.class).getDailyPlanCntWithDailyPlanNo(dailyPlanNo);
    }

    public int updatePostMappingYnWithDailyPlanNo(DeleteDailyPlanDTO deleteDailyPlanDTO) {
        return sqlSession.getMapper(DailyPlanDAOMapper.class).updatePostMappingYnWithDailyPlanNo(deleteDailyPlanDTO);
    }

    public List<DailyPlanCntVO> searchDailyPlanCntByDay(String planNo) {
        return sqlSession.getMapper(DailyPlanDAOMapper.class).searchDailyPlanCntByDay(planNo);
    }

    public List<DailyPlanVO> searchDailyPlanListByDay(DailyPlanByDayDTO dailyPlanByDayDTO) {
        return sqlSession.getMapper(DailyPlanDAOMapper.class).searchDailyPlanListByDay(dailyPlanByDayDTO);
    }

    public int deleteDailyPlanNotification(DeleteDailyPlanNotificationDTO deleteDailyPlanNotificationDTO) {
        return sqlSession.getMapper(DailyPlanDAOMapper.class).deleteDailyPlanNotification(deleteDailyPlanNotificationDTO);
    }

    public int updateDailyPlanNotification(NotificationDTO notificationDTO) {
        return sqlSession.getMapper(DailyPlanDAOMapper.class).updateDailyPlanNotification(notificationDTO);
    }
}
