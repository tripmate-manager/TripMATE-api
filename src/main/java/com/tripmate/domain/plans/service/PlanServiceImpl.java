package com.tripmate.domain.plans.service;

import com.tripmate.common.exception.GuideMessageException;
import com.tripmate.common.exception.NoResultException;
import com.tripmate.domain.common.Const;
import com.tripmate.domain.common.ConstCode;
import com.tripmate.domain.common.Encrypt;
import com.tripmate.domain.members.dao.MemberDAO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.plans.dao.PlanDAO;
import com.tripmate.domain.plans.dto.NotificationDTO;
import com.tripmate.domain.plans.dto.PlanAttributeDTO;
import com.tripmate.domain.plans.dto.PlanAuthCodeDTO;
import com.tripmate.domain.plans.dto.PlanDTO;
import com.tripmate.domain.plans.dto.SearchMemberDTO;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;
import com.tripmate.domain.plans.vo.PlanMateVO;
import com.tripmate.domain.plans.vo.PlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<PlanAttributeVO> searchPlanAttributeList(String attributeTypeCode) {
        List<PlanAttributeVO> planAttributeVOList = planDAO.searchPlanAttributeList(attributeTypeCode);
        if (planAttributeVOList == null) {
            throw new NoResultException("처리 중 오류가 발생하였습니다.");
        }
        return planAttributeVOList;
    }

    @Override
    public List<PlanAddressVO> searchAddressList(String sidoName) {
        List<PlanAddressVO> planAddressVOList = planDAO.searchAddressList(sidoName);
        if (planAddressVOList == null) {
            throw new NoResultException("처리 중 오류가 발생하였습니다.");
        }
        return planDAO.searchAddressList(sidoName);
    }

    @Override
    public List<PlanAddressVO> searchAddressList() {
        List<PlanAddressVO> planAddressVOList = planDAO.searchAddressList();
        if (planAddressVOList == null) {
            throw new NoResultException("처리 중 오류가 발생하였습니다.");
        }
        return planDAO.searchAddressList();
    }

    @Override
    @Transactional
    public int createPlan(PlanDTO planDTO) {
        MemberDTO memberNoExistCheckDTO = memberDAO.getMemberInfoWithMemberNo(planDTO.getMemberNo());
        int planNo;

        if (memberNoExistCheckDTO == null) {
            throw new NoResultException("회원 ID에 해당하는 회원 정보가 존재하지 않습니다.");
        }

        planNo = planDAO.insertPlanInfo(planDTO);
        if (planNo == 0) {
            throw new GuideMessageException("플랜 생성 처리 중 오류가 발생하였습니다.");
        }

        PlanMateVO planMateVO = PlanMateVO.builder()
                .planNo(planDTO.getPlanNo())
                .memberNo(planDTO.getMemberNo())
                .leadYn(Const.Y)
                .build();
        if (planDAO.insertPlanMate(planMateVO) != 1) {
            throw new GuideMessageException("플랜 생성 처리 중 오류가 발생하였습니다.");
        }

        insertPlanAddress(planDTO);

        if (planDTO.getPlanThemeList() != null) {
            insertPlanTheme(planDTO);
        }

        if (planDTO.getPlanHashtagList() != null) {
            insertHashtag(planDTO);
        }

        return planNo;
    }

    @Override
    public List<PlanVO> searchMemberPlanList(String memberNo) {
        return planDAO.searchPlanListWithMemberNo(memberNo);
    }

    @Override
    public PlanVO getPlanInfo(String planNo) {
        return planDAO.getPlanInfoWithPlanNo(planNo);
    }

    @Override
    public List<PlanMateVO> searchPlanMateList(String planNo) {
        return planDAO.searchPlanMateListWithPlanNo(planNo);
    }

    @Override
    @Transactional
    public boolean updatePlan(String planNo, PlanDTO planDTO) {
        PlanVO planVO = planDAO.getPlanInfoWithPlanNo(planNo);

        if (planVO == null) {
            throw new NoResultException("플랜 번호에 해당하는 플랜 정보가 존재하지 않습니다.");
        }

        if (planDTO.getPlanAddressList() != null) {
            planDAO.deletePlanAddressWithPlanNo(planNo);

            insertPlanAddress(planDTO);
        }

        if (planDTO.getPlanThemeList() != null) {
            PlanAttributeDTO deleteAddressDTO = PlanAttributeDTO.builder()
                    .planNo(planDTO.getPlanNo())
                    .attributeTypeCode(ConstCode.ATTRIBUTE_TYPE_CODE_TRIP_THEME)
                    .build();
            planDAO.deletePlanAttributeWithPlanNo(deleteAddressDTO);

            insertPlanTheme(planDTO);
        }

        if (planDTO.getPlanHashtagList() != null) {
            PlanAttributeDTO deleteHashtagDTO = PlanAttributeDTO.builder()
                    .planNo(planDTO.getPlanNo())
                    .attributeTypeCode(ConstCode.ATTRIBUTE_TYPE_CODE_HASHTAG)
                    .build();
            planDAO.deletePlanAttributeWithPlanNo(deleteHashtagDTO);

            insertHashtag(planDTO);
        }

        return planDAO.updatePlan(planDTO) == 1;
    }

    @Override
    public List<PlanMateVO> searchMemberList(String searchDiviCode, String searchKeyword) {
        SearchMemberDTO searchMemberDTO = SearchMemberDTO.builder()
                .searchMemberDiviCode(searchDiviCode)
                .searchKeyword(searchKeyword)
                .build();

        return planDAO.searchMemberListWithSearchKeyword(searchMemberDTO);
    }

    @Override
    public String createInviteAuthCode(String planNo, String inviteTypeCode) {
        Encrypt encrypt = new Encrypt();
        String encryptString = encrypt.getEncrypt(encrypt.getSalt(), Const.SERVICE_NAME);

        PlanAuthCodeDTO planAuthCodeDTO = PlanAuthCodeDTO.builder()
                .planNo(planNo)
                .inviteTypeCode(inviteTypeCode)
                .inviteCode(encryptString.substring(0, 6))
                .build();

        if (planDAO.insertInviteCode(planAuthCodeDTO) == 0) {
            throw new GuideMessageException("초대 인증 코드 생성 처리 중 오류가 발생하였습니다.");
        }

        return planAuthCodeDTO.getInviteCode();
    }

    @Override
    public boolean createNotification(NotificationDTO notificationDTO) {
        if (planDAO.insertNotification(notificationDTO) != 1) {
            throw new GuideMessageException("초대 인증 코드 생성 처리 중 오류가 발생하였습니다.");
        }

        return true;
    }

    private void insertPlanAddress(PlanDTO planDTO) {
        List<PlanAddressVO> planAddressVOList = new ArrayList<>();
        for (int addressNo : planDTO.getPlanAddressList()) {
            PlanAddressVO planAddressVO = PlanAddressVO.builder()
                    .memberNo(planDTO.getMemberNo())
                    .planNo(planDTO.getPlanNo())
                    .addressNo(addressNo)
                    .build();

            planAddressVOList.add(planAddressVO);
        }
        if (planDAO.insertTripAddress(planAddressVOList) < 1) {
            throw new GuideMessageException("플랜 생성 처리 중 오류가 발생하였습니다.");
        }
    }

    private void insertHashtag(PlanDTO planDTO) {
        List<PlanAttributeVO> planAttributeVOList = new ArrayList<>();
        for (String hashtag : planDTO.getPlanHashtagList()) {
            PlanAttributeVO planHashtagVO = PlanAttributeVO.builder()
                    .memberNo(planDTO.getMemberNo())
                    .planNo(planDTO.getPlanNo())
                    .attributeName(hashtag)
                    .attributeTypeCode(ConstCode.ATTRIBUTE_TYPE_CODE_HASHTAG)
                    .build();

            int hashtagAttributeNo = planDAO.selectPlanAttributeNo(planHashtagVO);
            if (hashtagAttributeNo == 0) {
                planDAO.insertPlanAttributeMgmt(planHashtagVO);
                hashtagAttributeNo = planHashtagVO.getAttributeNo();
            }

            PlanAttributeVO insertHashtagAttributeVO = PlanAttributeVO.builder()
                    .memberNo(planDTO.getMemberNo())
                    .planNo(planDTO.getPlanNo())
                    .attributeNo(hashtagAttributeNo)
                    .attributeTypeCode(ConstCode.ATTRIBUTE_TYPE_CODE_HASHTAG)
                    .build();

            planAttributeVOList.add(insertHashtagAttributeVO);
        }
        if (planDAO.insertPlanAttribute(planAttributeVOList) < 1) {
            throw new GuideMessageException("플랜 생성 처리 중 오류가 발생하였습니다.");
        }
    }

    private void insertPlanTheme(PlanDTO planDTO) {
        List<PlanAttributeVO> planAttributeVOList = new ArrayList<>();
        for (int tripThemeNo : planDTO.getPlanThemeList()) {
            PlanAttributeVO tripThemeVO = PlanAttributeVO.builder()
                    .memberNo(planDTO.getMemberNo())
                    .planNo(planDTO.getPlanNo())
                    .attributeNo(tripThemeNo)
                    .attributeTypeCode(ConstCode.ATTRIBUTE_TYPE_CODE_TRIP_THEME)
                    .build();

            planAttributeVOList.add(tripThemeVO);
        }
        if (planDAO.insertPlanAttribute(planAttributeVOList) < 1) {
            throw new GuideMessageException("플랜 생성 처리 중 오류가 발생하였습니다.");
        }
    }
}
