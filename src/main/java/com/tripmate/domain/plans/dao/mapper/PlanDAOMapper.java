package com.tripmate.domain.plans.dao.mapper;

import com.tripmate.domain.plans.dto.ExitPlanDTO;
import com.tripmate.domain.plans.dto.NotificationDTO;
import com.tripmate.domain.plans.dto.PlanAttributeDTO;
import com.tripmate.domain.plans.dto.PlanAuthCodeDTO;
import com.tripmate.domain.plans.dto.PlanDTO;
import com.tripmate.domain.plans.dto.MemberPlanDTO;
import com.tripmate.domain.plans.dto.PlanMateDTO;
import com.tripmate.domain.plans.dto.SearchMemberDTO;
import com.tripmate.domain.plans.dto.UpdateNotificationReadDateTimeDTO;
import com.tripmate.domain.plans.vo.InviteCodeVO;
import com.tripmate.domain.plans.vo.NotificationVO;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;
import com.tripmate.domain.plans.vo.PlanBasicInfoVO;
import com.tripmate.domain.plans.vo.PlanMateVO;
import com.tripmate.domain.plans.vo.PlanVO;
import com.tripmate.domain.plans.vo.PopularPlanVO;

import java.util.List;

public interface PlanDAOMapper {
    List<PlanAttributeVO> searchPlanAttributeList(String attributeTypeCode);
    List<PlanAddressVO> searchAddressList();
    List<PlanAddressVO> searchAddressList(String sidoName);
    int insertPlanInfo(PlanDTO planDTO);
    int insertTripAddress(List<PlanAddressVO> planAddressVOList);
    int insertPlanAttribute(List<PlanAttributeVO> planAttributeVOList);
    int getPlanAttributeNo(PlanAttributeVO planAttributeVO);
    int insertPlanAttributeMgmt(PlanAttributeVO planAttributeVO);
    int insertPlanMate(PlanMateDTO planMateDTO);
    List<PlanVO> searchPlanListWithMemberNo(String memberNo);
    List<PlanVO> getPlanInfoWithPlanNo(MemberPlanDTO memberPlanDTO);
    List<PlanMateVO> searchPlanMateListWithPlanNo(String planNo);
    int updatePlan(PlanDTO planDTO);
    int deletePlanAddressWithPlanNo(String planNo);
    int deletePlanAttributeWithPlanNo(PlanAttributeDTO planAttributeDTO);
    List<PlanMateVO> searchMemberListWithSearchKeyword(SearchMemberDTO searchMemberDTO);
    int insertInviteCode(PlanAuthCodeDTO planAuthCodeDTO);
    int insertNotification(NotificationDTO notificationDTO);
    List<NotificationVO> searchNotificationList(String memberNo);
    int getUnreadNotificationCnt(String memberNo);
    int updateNotificationReadDateTime(UpdateNotificationReadDateTimeDTO updateNotificationReadDateTimeDTO);
    String getPlanLeaderMemberNo(String planNo);
    int updatePlanLeaderYn(ExitPlanDTO exitPlanDTO);
    int deletePlanMate(ExitPlanDTO exitPlanDTO);
    int getPlanMateCnt(String planNo);
    int updatePlanUseYn(ExitPlanDTO exitPlanDTO);
    int getPlanMateCntWithMateNoAndPlanNo(ExitPlanDTO exitPlanDTO);
    InviteCodeVO getPlanInviteInfoWithInviteCodeNo(String inviteCodeNo);
    int getPlanMateCntWithMemberNoAndPlanNo(PlanMateDTO planMateDTO);
    int insertPlanLike(MemberPlanDTO memberPlanDTO);
    int deletePlanLike(MemberPlanDTO memberPlanDTO);
    List<PlanBasicInfoVO> searchMyPlanLikeList(String memberNo);
    List<PopularPlanVO> searchPopularPlanList(String memberNo);
    List<PopularPlanVO> searchPopularPlanList();
}
