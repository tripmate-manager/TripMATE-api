package com.tripmate.domain.plans.dao.mapper;

import com.tripmate.domain.plans.dto.CreatePlanDTO;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;
import com.tripmate.domain.plans.vo.PlanMateVO;

import java.util.List;

public interface PlanDAOMapper {
    List<PlanAttributeVO> selectPlanAttributeList(String attributeTypeCode);
    List<PlanAddressVO> selectAddressList();
    List<PlanAddressVO> selectAddressList(String sidoName);
    int insertPlanInfo(CreatePlanDTO createPlanDTO);
    int insertTripAddress(List<PlanAddressVO> planAddressVOList);
    int insertPlanAttribute(List<PlanAttributeVO> planAttributeVOList);
    int selectPlanAttributeNo(PlanAttributeVO planAttributeVO);
    int insertPlanAttributeMgmt(PlanAttributeVO planAttributeVO);
    int insertPlanMate(PlanMateVO planMateVO);
}
