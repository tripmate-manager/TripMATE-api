package com.tripmate.domain.plans.service;

import com.tripmate.domain.plans.dto.CreatePlanDTO;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;
import com.tripmate.domain.plans.vo.PlanVO;

import java.util.List;

public interface PlanService {
    List<PlanAttributeVO> searchPlanAttributeList(String attributeType);
    List<PlanAddressVO> searchAddressList(String sidoName);
    List<PlanAddressVO> searchAddressList();
    boolean createPlan(CreatePlanDTO createPlanDTO);
    List<PlanVO> searchMemberPlanList(String memberNo);
}
