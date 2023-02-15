package com.tripmate.domain.plans.service;

import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;

import java.util.List;

public interface PlanService {
    List<PlanAttributeVO> selectPlanAttributeList(String attributeType);
    List<PlanAddressVO> selectAddressList(String sidoName);
    List<PlanAddressVO> selectAddressList();
}
