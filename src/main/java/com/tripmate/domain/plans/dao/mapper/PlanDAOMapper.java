package com.tripmate.domain.plans.dao.mapper;

import com.tripmate.domain.plans.dto.PlanDTO;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;
import com.tripmate.domain.plans.vo.PlanMateVO;
import com.tripmate.domain.plans.vo.PlanVO;

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
    int insertPlanMate(PlanMateVO planMateVO);
    List<PlanVO> searchPlanListWithMemberNo(String memberNo);
    List<PlanVO> getPlanInfoWithPlanNo(String planNo);
    List<PlanMateVO> searchPlanMateListWithPlanNo(String planNo);
}
