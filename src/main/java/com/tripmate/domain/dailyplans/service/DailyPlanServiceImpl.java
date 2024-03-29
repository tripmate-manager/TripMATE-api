package com.tripmate.domain.dailyplans.service;

import com.tripmate.common.exception.GuideMessageException;
import com.tripmate.common.exception.WrongParameterException;
import com.tripmate.domain.accountbook.dao.AccountBookDAO;
import com.tripmate.domain.dailyplans.dao.DailyPlanDAO;
import com.tripmate.domain.dailyplans.dto.DailyPlanByDayDTO;
import com.tripmate.domain.dailyplans.dto.DailyPlanDTO;
import com.tripmate.domain.dailyplans.dto.DeleteDailyPlanDTO;
import com.tripmate.domain.dailyplans.dto.DeleteDailyPlanNotificationDTO;
import com.tripmate.domain.dailyplans.vo.DailyPlanCntVO;
import com.tripmate.domain.dailyplans.vo.DailyPlanVO;
import com.tripmate.domain.plans.dto.NotificationDTO;
import com.tripmate.domain.reviews.dao.ReviewDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyPlanServiceImpl implements DailyPlanService {
    private final DailyPlanDAO dailyPlanDAO;
    private final ReviewDAO reviewDAO;
    private final AccountBookDAO accountBookDAO;

    @Override
    @Transactional
    public boolean insertDailyPlan(DailyPlanDTO dailyPlanDTO) {
        if (dailyPlanDAO.insertDailyPlan(dailyPlanDTO) != 1) {
            throw new GuideMessageException("데일리플랜(북마크) 생성 처리 중 오류가 발생하였습니다.");
        }

        if (dailyPlanDAO.updatePostMappingYnWithPostNo(dailyPlanDTO) != 1) {
            throw new GuideMessageException("데일리플랜(북마크) 여부 수정 처리 중 오류가 발생하였습니다.");
        }

        if (accountBookDAO.insertAccountWithDailyPlanDTO(dailyPlanDTO) != 1) {
            throw new GuideMessageException("데일리플랜(북마크) 여부 수정 처리 중 오류가 발생하였습니다.");
        }

        return true;
    }

    @Override
    @Transactional
    public List<String> deleteDailyPlan(String dailyPlanNo, DeleteDailyPlanDTO deleteDailyPlanDTO) {
        if (dailyPlanDAO.getDailyPlanCntWithDailyPlanNo(dailyPlanNo) == 1) {
            if (dailyPlanDAO.updatePostMappingYnWithDailyPlanNo(deleteDailyPlanDTO) != 1) {
                throw new GuideMessageException("데일리플랜(북마크) 여부 수정 처리 중 오류가 발생하였습니다.");
            }
        }

        if (dailyPlanDAO.deleteDailyPlan(dailyPlanNo) != 1) {
            throw new GuideMessageException("데일리플랜(북마크) 삭제 처리 중 오류가 발생하였습니다.");
        }

        if (accountBookDAO.deleteAccountWithDailyPlanNo(dailyPlanNo) != 1) {
            throw new GuideMessageException("데일리플랜(북마크) 삭제 처리 중 오류가 발생하였습니다.");
        }

        int reviewCnt = reviewDAO.getDailyPlanReviewCnt(dailyPlanNo);
        int reviewImageCnt = reviewDAO.getReviewImageCntWithDailyPlanNo(dailyPlanNo);
        List<String> reviewImageNameList = reviewDAO.searchReviewImageNameListWithDailyPlanNo(dailyPlanNo);

        if (reviewCnt > 0) {
            if (reviewImageCnt == 0) {
                if (reviewDAO.deleteDailyPlanReview(dailyPlanNo) != 1) {
                    throw new GuideMessageException("데일리플랜 리뷰 삭제 처리 중 오류가 발생하였습니다.");
                }
            } else {
                if (reviewDAO.deleteDailyPlanReview(dailyPlanNo) != reviewCnt + 1) {
                    throw new GuideMessageException("데일리플랜 리뷰 삭제 처리 중 오류가 발생하였습니다.");
                }
            }
        }

        return reviewImageNameList;
    }

    @Override
    public List<DailyPlanCntVO> searchDailyPlanCntByDay(String planNo) {
        return dailyPlanDAO.searchDailyPlanCntByDay(planNo);
    }

    @Override
    public DailyPlanVO searchDailyPlanListByDay(DailyPlanByDayDTO dailyPlanByDayDTO) {
        return DailyPlanVO.builder()
                .planNo(dailyPlanByDayDTO.getPlanNo())
                .memberNo(dailyPlanByDayDTO.getMemberNo())
                .dayGroup(dailyPlanByDayDTO.getDayGroup())
                .achieveRate(String.valueOf(dailyPlanDAO.getPlanAchieveRate(dailyPlanByDayDTO)))
                .dailyPlanItemList(dailyPlanDAO.searchDailyPlanListByDay(dailyPlanByDayDTO))
                .build();
    }

    @Override
    public boolean deleteDailyPlanNotification(DeleteDailyPlanNotificationDTO deleteDailyPlanNotificationDTO) {
        return dailyPlanDAO.deleteDailyPlanNotification(deleteDailyPlanNotificationDTO) == 1;
    }

    @Override
    public boolean updateDailyPlanNotification(String dailyPlanNo, NotificationDTO notificationDTO) {
        if (!dailyPlanNo.equals(notificationDTO.getDailyPlanNo())) {
            throw new WrongParameterException("데일리플랜 수정 처리 중 오류가 발생하였습니다.");
        }
        return dailyPlanDAO.updateDailyPlanNotification(notificationDTO) == 1;
    }
}
