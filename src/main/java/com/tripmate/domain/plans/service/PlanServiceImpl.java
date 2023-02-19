package com.tripmate.domain.plans.service;

import com.tripmate.common.exception.GuideMessageException;
import com.tripmate.common.exception.NoResultException;
import com.tripmate.domain.common.Const;
import com.tripmate.domain.common.ConstCode;
import com.tripmate.domain.members.dao.MemberDAO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.plans.dao.PlanDAO;
import com.tripmate.domain.plans.dto.CreatePlanDTO;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;
import com.tripmate.domain.plans.vo.PlanMateVO;
import com.tripmate.domain.plans.vo.PlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    private final PlanDAO planDAO;
    private final MemberDAO memberDAO;

    @Autowired
    public PlanServiceImpl(PlanDAO planDAO, MemberDAO memberDAO) {
        this.planDAO = planDAO;
        this.memberDAO = memberDAO;
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
        List<PlanAddressVO> planAddressVOList = planDAO.selectAddressList(sidoName);
        if (planAddressVOList == null) {
            throw new NoResultException("처리 중 오류가 발생하였습니다.");
        }
        return planDAO.selectAddressList(sidoName);
    }

    @Override
    public List<PlanAddressVO> selectAddressList() {
        List<PlanAddressVO> planAddressVOList = planDAO.selectAddressList();
        if (planAddressVOList == null) {
            throw new NoResultException("처리 중 오류가 발생하였습니다.");
        }
        return planDAO.selectAddressList();
    }

    @Override
    public boolean createPlan(CreatePlanDTO createPlanDTO) {
        MemberDTO memberNoExistCheckDTO = memberDAO.selectMemberInfoWithMemberNo(createPlanDTO.getMemberNo());

        if (memberNoExistCheckDTO == null) {
            throw new NoResultException("회원 ID에 해당하는 회원 정보가 존재하지 않습니다.");
        }

        if (planDAO.insertPlanInfo(createPlanDTO) == 0) {
            throw new GuideMessageException("플랜 생성 처리 중 오류가 발생하였습니다.");
        }

        PlanMateVO planMateVO = PlanMateVO.builder()
                .planNo(createPlanDTO.getPlanNo())
                .memberNo(createPlanDTO.getMemberNo())
                .leadYn(Const.Y)
                .build();
        if (planDAO.insertPlanMate(planMateVO) != 1) {
            throw new GuideMessageException("플랜 생성 처리 중 오류가 발생하였습니다.");
        }

        List<PlanAddressVO> planAddressVOList = new ArrayList<>();
        for (int addressNo : createPlanDTO.getPlanAddressList()) {
            PlanAddressVO planAddressVO = PlanAddressVO.builder()
                    .memberNo(createPlanDTO.getMemberNo())
                    .planNo(createPlanDTO.getPlanNo())
                    .addressNo(addressNo)
                    .build();

            planAddressVOList.add(planAddressVO);
        }
        if (planDAO.insertTripAddress(planAddressVOList) < 1) {
            throw new GuideMessageException("플랜 생성 처리 중 오류가 발생하였습니다.");
        }

        if (createPlanDTO.getPlanThemeList() != null) {
            List<PlanAttributeVO> planAttributeVOList = new ArrayList<>();
            for (int tripThemeNo : createPlanDTO.getPlanThemeList()) {
                PlanAttributeVO tripThemeVO = PlanAttributeVO.builder()
                        .memberNo(createPlanDTO.getMemberNo())
                        .planNo(createPlanDTO.getPlanNo())
                        .attributeNo(tripThemeNo)
                        .attributeTypeCode(ConstCode.ATTRIBUTE_TYPE_CODE_TRIP_THEME)
                        .build();

                planAttributeVOList.add(tripThemeVO);
            }
            if (planDAO.insertPlanAttribute(planAttributeVOList) < 1) {
                throw new GuideMessageException("플랜 생성 처리 중 오류가 발생하였습니다.");
            }
        }

        if (createPlanDTO.getPlanHashtagList() != null) {
            List<PlanAttributeVO> planAttributeVOList = new ArrayList<>();
            for (String hashtag : createPlanDTO.getPlanHashtagList()) {
                PlanAttributeVO planHashtagVO = PlanAttributeVO.builder()
                        .memberNo(createPlanDTO.getMemberNo())
                        .planNo(createPlanDTO.getPlanNo())
                        .attributeName(hashtag)
                        .attributeTypeCode(ConstCode.ATTRIBUTE_TYPE_CODE_HASHTAG)
                        .build();

                int hashtagAttributeNo = planDAO.selectPlanAttributeNo(planHashtagVO);
                if (hashtagAttributeNo == 0) {
                    planDAO.insertPlanAttributeMgmt(planHashtagVO);
                    hashtagAttributeNo = planHashtagVO.getAttributeNo();
                }

                PlanAttributeVO insertHashtagAttributeVO = PlanAttributeVO.builder()
                        .memberNo(createPlanDTO.getMemberNo())
                        .planNo(createPlanDTO.getPlanNo())
                        .attributeNo(hashtagAttributeNo)
                        .attributeTypeCode(ConstCode.ATTRIBUTE_TYPE_CODE_HASHTAG)
                        .build();

                planAttributeVOList.add(insertHashtagAttributeVO);
            }
            if (planDAO.insertPlanAttribute(planAttributeVOList) < 1) {
                throw new GuideMessageException("플랜 생성 처리 중 오류가 발생하였습니다.");
            }
        }

        return true;
    }

    @Override
    public List<PlanVO> selectPlanList(String memberNo) {
        List<Integer> planNoList = planDAO.selectPlanNoListWithMbrNo(memberNo);
        List<PlanVO> planVOList = new ArrayList<>();

        if (planNoList != null) {
            for (int planNo : planNoList) {
                PlanVO planVO = planDAO.selectPlanInfoWithMbrNo(planNo);

                planVOList.add(planVO);
            }
        }

        return planVOList;
    }
}
