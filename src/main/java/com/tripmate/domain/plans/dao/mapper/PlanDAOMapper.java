package com.tripmate.domain.plans.dao.mapper;

import com.tripmate.domain.plans.dto.CreatePlanDTO;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;

import java.util.List;

public interface PlanDAOMapper {
    List<PlanAttributeVO> selectPlanAttributeList(String attributeTypeCode);
    List<PlanAddressVO> selectAddressList();
    List<PlanAddressVO> selectAddressList(String sidoName);
    int insertPlanInfo(CreatePlanDTO createPlanDTO);
    int insertTripAddress(PlanAddressVO planAddressVO);
    int insertPlanAttribute(PlanAttributeVO planAttributeVO);
    int selectPlanAttributeDuplicationCnt(PlanAttributeVO planAttributeVO);
    int insertPlanAttributeMgmt(PlanAttributeVO planAttributeVO);
}
