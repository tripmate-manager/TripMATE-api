package com.tripmate.domain.plans.service;

import com.tripmate.domain.plans.dto.PlanDTO;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;
import com.tripmate.domain.plans.vo.PlanVO;

import java.util.List;

public interface PlanService {
    List<PlanAttributeVO> searchPlanAttributeList(String attributeType);
    List<PlanAddressVO> searchAddressList(String sidoName);
    List<PlanAddressVO> searchAddressList();
    boolean createPlan(PlanDTO planDTO);
    List<PlanVO> searchMemberPlanList(String memberNo);
    PlanVO getPlanInfo(String planNo);
}
