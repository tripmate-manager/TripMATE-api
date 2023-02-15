package com.tripmate.domain.plans.dao.mapper;

import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;

import java.util.List;

public interface PlanDAOMapper {
    List<PlanAttributeVO> selectPlanAttributeList(String attributeTypeCode);
    List<PlanAddressVO> selectAddressList();
    List<PlanAddressVO> selectAddressList(String sidoName);
}
