package com.tripmate.domain.plans.dao;

import com.tripmate.domain.plans.dao.mapper.PlanDAOMapper;
import com.tripmate.domain.plans.dto.CreatePlanDTO;
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

    public List<PlanAttributeVO> selectPlanAttributeList(String attributeTypeCode) {
        return sqlSession.getMapper(PlanDAOMapper.class).selectPlanAttributeList(attributeTypeCode);
    }

    public List<PlanAddressVO> selectAddressList() {
        return sqlSession.getMapper(PlanDAOMapper.class).selectAddressList();
    }

    public List<PlanAddressVO> selectAddressList(String sidoName) {
        return sqlSession.getMapper(PlanDAOMapper.class).selectAddressList(sidoName);
    }

    public int insertPlanInfo(CreatePlanDTO createPlanDTO) {
        sqlSession.getMapper(PlanDAOMapper.class).insertPlanInfo(createPlanDTO);

        return createPlanDTO.getPlanNo();
    }

    public int insertTripAddress(List<PlanAddressVO> planAddressVOList) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertTripAddress(planAddressVOList);
    }

    public int insertPlanAttribute(List<PlanAttributeVO> planAttributeVOList) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertPlanAttribute(planAttributeVOList);
    }

    public int selectPlanAttributeNo(PlanAttributeVO planAttributeVO) {
        return sqlSession.getMapper(PlanDAOMapper.class).selectPlanAttributeNo(planAttributeVO);
    }

    public int insertPlanAttributeMgmt(PlanAttributeVO planAttributeVO) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertPlanAttributeMgmt(planAttributeVO);
    }

    public int insertPlanMate(PlanMateVO planMateVO) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertPlanMate(planMateVO);
    }

    public List<PlanVO> selectPlanInfoWithMemberNo(String memberNo) {
        return sqlSession.getMapper(PlanDAOMapper.class).selectPlanInfoWithMbrNo(memberNo);
    }
}
