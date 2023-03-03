package com.tripmate.domain.plans.service;

import com.tripmate.domain.plans.dto.PlanDTO;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;
import com.tripmate.domain.plans.vo.PlanMateVO;
import com.tripmate.domain.plans.vo.PlanVO;

import java.util.List;

public interface PlanService {
    List<PlanAttributeVO> searchPlanAttributeList(String attributeType);
    List<PlanAddressVO> searchAddressList(String sidoName);
    List<PlanAddressVO> searchAddressList();
    int createPlan(PlanDTO planDTO);
    List<PlanVO> searchMemberPlanList(String memberNo);
    PlanVO getPlanInfo(String planNo);
    List<PlanMateVO> searchPlanMateList(String planNo);
    boolean updatePlan(String planNo, PlanDTO planDTO);
    List<PlanMateVO> searchMemberList(String searchDiviCode, String searchKeyword);
    String createInviteAuthCode(String planNo, String inviteTypeCode);
}
