package com.tripmate.domain.plans.dao;

import com.tripmate.domain.plans.dao.mapper.PlanDAOMapper;
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
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanDAO {
    private final SqlSession sqlSession;

    @Autowired
    public PlanDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<PlanAttributeVO> searchPlanAttributeList(String attributeTypeCode) {
        return sqlSession.getMapper(PlanDAOMapper.class).searchPlanAttributeList(attributeTypeCode);
    }

    public List<PlanAddressVO> searchAddressList() {
        return sqlSession.getMapper(PlanDAOMapper.class).searchAddressList();
    }

    public List<PlanAddressVO> searchAddressList(String sidoName) {
        return sqlSession.getMapper(PlanDAOMapper.class).searchAddressList(sidoName);
    }

    public int insertPlanInfo(PlanDTO planDTO) {
        sqlSession.getMapper(PlanDAOMapper.class).insertPlanInfo(planDTO);

        return planDTO.getPlanNo();
    }

    public int insertTripAddress(List<PlanAddressVO> planAddressVOList) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertTripAddress(planAddressVOList);
    }

    public int insertPlanAttribute(List<PlanAttributeVO> planAttributeVOList) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertPlanAttribute(planAttributeVOList);
    }

    public int selectPlanAttributeNo(PlanAttributeVO planAttributeVO) {
        return sqlSession.getMapper(PlanDAOMapper.class).getPlanAttributeNo(planAttributeVO);
    }

    public int insertPlanAttributeMgmt(PlanAttributeVO planAttributeVO) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertPlanAttributeMgmt(planAttributeVO);
    }

    public int insertPlanMate(PlanMateDTO planMateDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertPlanMate(planMateDTO);
    }

    public List<PlanVO> searchPlanListWithMemberNo(String memberNo) {
        return sqlSession.getMapper(PlanDAOMapper.class).searchPlanListWithMemberNo(memberNo);
    }

    public List<PlanVO> getPlanInfoWithPlanNo(MemberPlanDTO memberPlanDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).getPlanInfoWithPlanNo(memberPlanDTO);
    }

    public List<PlanMateVO> searchPlanMateListWithPlanNo(String planNo) {
        return sqlSession.getMapper(PlanDAOMapper.class).searchPlanMateListWithPlanNo(planNo);
    }

    public int updatePlan(PlanDTO planDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).updatePlan(planDTO);
    }

    public int deletePlanAddressWithPlanNo(String planNo) {
        return sqlSession.getMapper(PlanDAOMapper.class).deletePlanAddressWithPlanNo(planNo);
    }

    public int deletePlanAttributeWithPlanNo(PlanAttributeDTO planAttributeDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).deletePlanAttributeWithPlanNo(planAttributeDTO);
    }

    public List<PlanMateVO> searchMemberListWithSearchKeyword(SearchMemberDTO searchMemberDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).searchMemberListWithSearchKeyword(searchMemberDTO);
    }

    public int insertInviteCode(PlanAuthCodeDTO planAuthCodeDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertInviteCode(planAuthCodeDTO);
    }

    public int insertNotification(NotificationDTO notificationDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertNotification(notificationDTO);
    }

    public List<NotificationVO> searchNotificationList(String memberNo) {
        return sqlSession.getMapper(PlanDAOMapper.class).searchNotificationList(memberNo);
    }

    public int getUnreadNotificationCnt(String memberNo) {
        return sqlSession.getMapper(PlanDAOMapper.class).getUnreadNotificationCnt(memberNo);
    }

    public int updateNotificationReadDateTime(UpdateNotificationReadDateTimeDTO updateNotificationReadDateTimeDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).updateNotificationReadDateTime(updateNotificationReadDateTimeDTO);
    }

    public String getPlanLeaderMemberNo(String planNo) {
        return sqlSession.getMapper(PlanDAOMapper.class).getPlanLeaderMemberNo(planNo);
    }

    public int updatePlanLeaderYn(ExitPlanDTO exitPlanDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).updatePlanLeaderYn(exitPlanDTO);
    }

    public int deletePlanMate(ExitPlanDTO exitPlanDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).deletePlanMate(exitPlanDTO);
    }

    public int getPlanMateCnt(String planNo) {
        return sqlSession.getMapper(PlanDAOMapper.class).getPlanMateCnt(planNo);
    }

    public int updatePlanUseYn(ExitPlanDTO exitPlanDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).updatePlanUseYn(exitPlanDTO);
    }

    public int getPlanMateCntWithMateNoAndPlanNo(ExitPlanDTO exitPlanDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).getPlanMateCntWithMateNoAndPlanNo(exitPlanDTO);
    }

    public InviteCodeVO getPlanInviteInfoWithInviteCodeNo(String inviteCodeNo) {
        return sqlSession.getMapper(PlanDAOMapper.class).getPlanInviteInfoWithInviteCodeNo(inviteCodeNo);
    }

    public int getPlanMateCntWithMemberNoAndPlanNo(PlanMateDTO planMateDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).getPlanMateCntWithMemberNoAndPlanNo(planMateDTO);
    }

    public int insertPlanLike(MemberPlanDTO memberPlanDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertPlanLike(memberPlanDTO);
    }

    public int deletePlanLike(MemberPlanDTO memberPlanDTO) {
        return sqlSession.getMapper(PlanDAOMapper.class).deletePlanLike(memberPlanDTO);
    }

    public List<PlanBasicInfoVO> searchMyPlanLikeList(String memberNo) {
        return sqlSession.getMapper(PlanDAOMapper.class).searchMyPlanLikeList(memberNo);
    }

    public List<PopularPlanVO> searchPopularPlanList(String memberNo) {
        return sqlSession.getMapper(PlanDAOMapper.class).searchPopularPlanList(memberNo);
    }

    public List<PopularPlanVO> searchPopularPlanList() {
        return sqlSession.getMapper(PlanDAOMapper.class).searchPopularPlanList();
    }
}
