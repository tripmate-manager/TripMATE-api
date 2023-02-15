package com.tripmate.domain.plans.service;

import com.tripmate.common.exception.GuideMessageException;
import com.tripmate.common.exception.NoResultException;
import com.tripmate.domain.common.ConstCode;
import com.tripmate.domain.plans.dao.PlanDAO;
import com.tripmate.domain.plans.dto.CreatePlanDTO;
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

    @Override
    public boolean createPlan(CreatePlanDTO createPlanDTO) {

        if (planDAO.insertPlanInfo(createPlanDTO) == 0) {
            throw new GuideMessageException("플랜 생성 처리 중 오류가 발생하였습니다.");
        }

        for (int addressNo : createPlanDTO.getPlanAddressList()) {
            PlanAddressVO planAddressVO = PlanAddressVO.builder()
                    .memberNo(createPlanDTO.getMemberNo())
                    .planNo(createPlanDTO.getPlanNo())
                    .addressNo(addressNo)
                    .build();

            if (planDAO.insertTripAddress(planAddressVO) != 1) {
                throw new GuideMessageException("플랜 생성 처리 중 오류가 발생하였습니다.");
            }
        }

        if (createPlanDTO.getPlanThemeList() != null) {
            for (int tripThemeNo : createPlanDTO.getPlanThemeList()) {
                PlanAttributeVO tripThemeVO = PlanAttributeVO.builder()
                        .memberNo(createPlanDTO.getMemberNo())
                        .planNo(createPlanDTO.getPlanNo())
                        .attributeNo(tripThemeNo)
                        .attributeTypeCode(ConstCode.ATTRIBUTE_TYPE_CODE_TRIP_THEME)
                        .build();

                if (planDAO.insertPlanAttribute(tripThemeVO) != 1) {
                    throw new GuideMessageException("플랜 생성 처리 중 오류가 발생하였습니다.");
                }
            }
        }

        if (createPlanDTO.getPlanHashtagList() != null) {
            for (String hashtag : createPlanDTO.getPlanHashtagList()) {
                PlanAttributeVO planHashtagVO = PlanAttributeVO.builder()
                        .planNo(createPlanDTO.getPlanNo())
                        .attributeName(hashtag)
                        .attributeTypeCode(ConstCode.ATTRIBUTE_TYPE_CODE_HASHTAG)
                        .build();

                int hashtagAttributeNo = planDAO.selectAttributeDuplicationCnt(planHashtagVO);
                if (hashtagAttributeNo == 0) {
                    planDAO.insertPlanAttributeMgmt(planHashtagVO);
                    hashtagAttributeNo = planHashtagVO.getAttributeNo();
                }

                PlanAttributeVO insertHashtagAttributeVO = PlanAttributeVO.builder()
                        .planNo(createPlanDTO.getPlanNo())
                        .attributeNo(hashtagAttributeNo)
                        .attributeTypeCode(ConstCode.ATTRIBUTE_TYPE_CODE_HASHTAG)
                        .build();

                if (planDAO.insertPlanAttribute(insertHashtagAttributeVO) != 1) {
                    throw new GuideMessageException("플랜 생성 처리 중 오류가 발생하였습니다.");
                }
            }
        }

        return true;
    }
}
