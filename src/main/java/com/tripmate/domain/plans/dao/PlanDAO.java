package com.tripmate.domain.plans.dao;

import com.tripmate.domain.plans.dao.mapper.PlanDAOMapper;
import com.tripmate.domain.plans.dto.CreatePlanDTO;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;
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

    public int insertTripAddress(PlanAddressVO planAddressVO) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertTripAddress(planAddressVO);
    }

    public int insertPlanAttribute(PlanAttributeVO planAttributeVO) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertPlanAttribute(planAttributeVO);
    }

    public int selectAttributeDuplicationCnt(PlanAttributeVO planAttributeVO) {
        return sqlSession.getMapper(PlanDAOMapper.class).selectPlanAttributeDuplicationCnt(planAttributeVO);
    }
    public int insertPlanAttributeMgmt(PlanAttributeVO planAttributeVO) {
        return sqlSession.getMapper(PlanDAOMapper.class).insertPlanAttributeMgmt(planAttributeVO);
    }
}
