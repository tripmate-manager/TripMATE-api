package com.tripmate.domain.plans.service;

import com.tripmate.common.exception.GuideMessageException;
import com.tripmate.common.exception.NoResultException;
import com.tripmate.domain.common.Const;
import com.tripmate.domain.common.ConstCode;
import com.tripmate.domain.common.Encrypt;
import com.tripmate.domain.members.dao.MemberDAO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.plans.dao.PlanDAO;
import com.tripmate.domain.plans.dto.ExitPlanDTO;
import com.tripmate.domain.plans.dto.NotificationDTO;
import com.tripmate.domain.plans.dto.PlanAttributeDTO;
import com.tripmate.domain.plans.dto.PlanAuthCodeDTO;
import com.tripmate.domain.plans.dto.PlanDTO;
import com.tripmate.domain.plans.dto.MemberPlanDTO;
import com.tripmate.domain.plans.dto.PlanMateDTO;
import com.tripmate.domain.plans.dto.SearchMemberDTO;
import com.tripmate.domain.plans.dto.UpdateNotificationReadDateTimeDTO;
import com.tripmate.domain.plans.vo.InviteCodeVO;
import com.tripmate.domain.plans.vo.NotificationVO;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;
import com.tripmate.domain.plans.vo.PlanBasicInfoVO;
import com.tripmate.domain.plans.vo.PlanMateVO;
import com.tripmate.domain.plans.vo.PlanVO;
import com.tripmate.domain.plans.vo.PopularPlanVO;
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

        PlanMateDTO planMateDTO = PlanMateDTO.builder()
                .planNo(String.valueOf(planDTO.getPlanNo()))
                .memberNo(String.valueOf(planDTO.getMemberNo()))
                .leadYn(Const.Y)
                .build();
        if (planDAO.insertPlanMate(planMateDTO) != 1) {
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
    public PlanVO getPlanInfo(MemberPlanDTO memberPlanDTO) {
        List<PlanVO> planVO = planDAO.getPlanInfoWithPlanNo(memberPlanDTO);

        if (planVO.isEmpty()) {
            throw new NoResultException("해당 플랜 정보가 존재하지 않습니다.");
        }

        return planDAO.getPlanInfoWithPlanNo(memberPlanDTO).get(0);
    }

    @Override
    public List<PlanMateVO> searchPlanMateList(String planNo) {
        return planDAO.searchPlanMateListWithPlanNo(planNo);
    }

    @Override
    @Transactional
    public boolean updatePlan(String planNo, PlanDTO planDTO) {
        List<PlanVO> planVO = planDAO.getPlanInfoWithPlanNo(MemberPlanDTO.builder()
                .planNo(planNo)
                .memberNo(String.valueOf(planDTO.getMemberNo()))
                .build());

        if (planVO == null) {
            throw new NoResultException("해당 플랜 정보가 존재하지 않습니다.");
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
    public InviteCodeVO createInviteAuthCode(String planNo, String memberNo, String inviteTypeCode) {
        Encrypt encrypt = new Encrypt();
        String encryptString = encrypt.getEncrypt(encrypt.getSalt(), Const.SERVICE_NAME);

        PlanAuthCodeDTO planAuthCodeDTO = PlanAuthCodeDTO.builder()
                .planNo(planNo)
                .memberNo(memberNo)
                .inviteTypeCode(inviteTypeCode)
                .inviteCode(encryptString.substring(0, 6))
                .build();

        if (planDAO.insertInviteCode(planAuthCodeDTO) != 1) {
            throw new GuideMessageException("초대 인증 코드 생성 처리 중 오류가 발생하였습니다.");
        }

        return planDAO.getPlanInviteInfoWithInviteCodeNo(String.valueOf(planAuthCodeDTO.getInviteCodeNo()));
    }

    @Override
    @Transactional
    public boolean createNotification(NotificationDTO notificationDTO) {

        if (ConstCode.NOTIFICATION_TYPE_CODE_CHANGE_LEADER.equals(notificationDTO.getNotificationTypeCode())) {
            if (planDAO.insertNotification(notificationDTO) != planDAO.getPlanMateCnt(notificationDTO.getPlanNo())) {
                throw new GuideMessageException("리더 변경 알림 생성 중 오류가 발생하였습니다.");
            }
        } else if (ConstCode.NOTIFICATION_TYPE_CODE_TRIP_SCHEDULE.equals(notificationDTO.getNotificationTypeCode())) {
            if (planDAO.insertNotification(notificationDTO) != 1) {
                throw new GuideMessageException("데일리플랜 일정 알림 생성 처리 중 오류가 발생하였습니다.");
            }
        } else {
            if (planDAO.insertNotification(notificationDTO) != 1) {
                throw new GuideMessageException("초대 알림 생성 처리 중 오류가 발생하였습니다.");
            }
        }

        return true;
    }

    @Override
    public List<NotificationVO> searchNotificationList(String memberNo) {
        return planDAO.searchNotificationList(memberNo);
    }

    @Override
    public int getUnreadNotificationCnt(String memberNo) {
        return planDAO.getUnreadNotificationCnt(memberNo);
    }

    @Override
    public boolean updateNotificationReadDateTime(String memberNo, String notificationNo) {
        return planDAO.updateNotificationReadDateTime(UpdateNotificationReadDateTimeDTO.builder()
                .memberNo(memberNo)
                .notificationNo(notificationNo)
                .build()
        ) == 1;
    }

    @Override
    @Transactional
    public boolean exitPlan(ExitPlanDTO exitPlanDTO) {

        if (exitPlanDTO.getPlanNo().equals(exitPlanDTO.getMateNo()) || planDAO.getPlanMateCnt(exitPlanDTO.getPlanNo()) == 0) {
            throw new GuideMessageException("플랜 나가기 처리 중 오류가 발생하였습니다.");
        }

        if (exitPlanDTO.getMemberNo().equals(planDAO.getPlanLeaderMemberNo(exitPlanDTO.getPlanNo()))) {
            if (planDAO.getPlanMateCnt(exitPlanDTO.getPlanNo()) == 1) {
                if (planDAO.updatePlanUseYn(exitPlanDTO) != 1) {
                    throw new GuideMessageException("플랜 나가기 처리 중 오류가 발생하였습니다.");
                }
            } else {
                if (planDAO.getPlanMateCntWithMateNoAndPlanNo(exitPlanDTO) != 1) {
                    throw new GuideMessageException("플랜 리더 변경 처리 중 오류가 발생하였습니다.");
                }
                if (planDAO.updatePlanLeaderYn(exitPlanDTO) != 1) {
                    throw new GuideMessageException("플랜 리더 변경 처리 중 오류가 발생하였습니다.");
                }
            }
        }

        if (planDAO.deletePlanMate(exitPlanDTO) != 1) {
            throw new GuideMessageException("플랜 나가기 처리 중 오류가 발생하였습니다.");
        }

        return true;
    }

    @Override
    public InviteCodeVO getPlanInviteInfoWithInviteCodeNo(String inviteCodeNo) {
        return planDAO.getPlanInviteInfoWithInviteCodeNo(inviteCodeNo);
    }

    @Override
    public boolean insertPlanMate(PlanMateDTO planMateDTO) {
        if (planDAO.getPlanMateCntWithMemberNoAndPlanNo(planMateDTO) > 0) {
            throw new GuideMessageException("해당 플랜에 이미 가입된 회원입니다.");
        }

        return planDAO.insertPlanMate(planMateDTO) == 1;
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

    @Override
    public boolean insertPlanLike(MemberPlanDTO memberPlanDTO) {
        return planDAO.insertPlanLike(memberPlanDTO) == 1;
    }

    @Override
    public boolean deletePlanLike(MemberPlanDTO memberPlanDTO) {
        return planDAO.deletePlanLike(memberPlanDTO) > 0;
    }

    @Override
    public List<PlanBasicInfoVO> searchMyPlanLikeList(String memberNo) {
        return planDAO.searchMyPlanLikeList(memberNo);
    }

    @Override
    public List<PopularPlanVO> searchPopularPlanList(String memberNo) {
        return planDAO.searchPopularPlanList(memberNo);
    }

    @Override
    public List<PopularPlanVO> searchPopularPlanList() {
        return planDAO.searchPopularPlanList();
    }
}
