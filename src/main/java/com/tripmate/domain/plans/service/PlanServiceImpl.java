package com.tripmate.domain.plans.service;

import com.tripmate.common.exception.NoResultException;
import com.tripmate.domain.plans.dao.PlanDAO;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    private final PlanDAO planDAO;

    @Autowired
    public PlanServiceImpl(PlanDAO planDAO) {
        this.planDAO = planDAO;
    }

    @Override
    public List<PlanAttributeVO> selectPlanAttributeList(String attributeTypeCode) {
        List<PlanAttributeVO> planAttributeVOList = planDAO.selectPlanAttributeList(attributeTypeCode);
        if (planAttributeVOList == null) {
            throw new NoResultException("처리 중 오류가 발생하였습니다.");
        }
        return planAttributeVOList;
    }

    @Override
    public List<PlanAddressVO> selectAddressList(String sidoName) {
        return planDAO.selectAddressList(sidoName);
    }

    @Override
    public List<PlanAddressVO> selectAddressList() {
        return planDAO.selectAddressList();
    }
}
