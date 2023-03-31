package com.tripmate.domain.dailyplans.service;

import com.tripmate.common.exception.GuideMessageException;
import com.tripmate.domain.dailyplans.dao.DailyPlanDAO;
import com.tripmate.domain.dailyplans.dto.DailyPlanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DailyPlanServiceImpl implements DailyPlanService {
    private final DailyPlanDAO dailyPlanDAO;

    @Autowired
    public DailyPlanServiceImpl(DailyPlanDAO dailyPlanDAO) {
        this.dailyPlanDAO = dailyPlanDAO;
    }

    @Override
    @Transactional
    public String insertDailyPlan(DailyPlanDTO dailyPlanDTO) {
        if (dailyPlanDAO.insertDailyPlan(dailyPlanDTO) != 1) {
            throw new GuideMessageException("데일리플랜(북마크) 생성 처리 중 오류가 발생하였습니다.");
        }

        if (dailyPlanDAO.updatePostMappingYn(dailyPlanDTO) != 1) {
            throw new GuideMessageException("데일리플랜(북마크) 여부 수정 처리 중 오류가 발생하였습니다.");
        }

        return dailyPlanDTO.getDailyPlanNo();
    }

    @Override
    public boolean deleteDailyPlan(DailyPlanDTO dailyPlanDTO) {
        //todo: mappingYn N 업데이트
        return dailyPlanDAO.deleteDailyPlan(dailyPlanDTO) == 1;
    }
}
