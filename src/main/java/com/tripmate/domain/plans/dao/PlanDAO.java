package com.tripmate.domain.plans.dao;

import com.tripmate.domain.plans.dao.mapper.PlanDAOMapper;
import com.tripmate.domain.plans.dto.PlanDTO;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;
import com.tripmate.domain.plans.vo.PlanMateVO;
import com.tripmate.domain.plans.vo.PlanVO;
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

    public int insertPlanMate(PlanMateVO planMateVO) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertPlanMate(planMateVO);
    }

    public List<PlanVO> searchPlanListWithMemberNo(String memberNo) {
        return sqlSession.getMapper(PlanDAOMapper.class).searchPlanListWithMemberNo(memberNo);
    }

    public PlanVO getPlanInfoWithPlanNo(String planNo) {
        return sqlSession.getMapper(PlanDAOMapper.class).getPlanInfoWithPlanNo(planNo).get(0);
    }
}
