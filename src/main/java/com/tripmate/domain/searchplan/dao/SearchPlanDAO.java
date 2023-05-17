package com.tripmate.domain.searchplan.dao;

import com.tripmate.domain.plans.vo.PlanBasicInfoVO;
import com.tripmate.domain.searchplan.dao.mapper.SearchPlanDAOMapper;
import com.tripmate.domain.searchplan.dto.SearchAttributeDTO;
import com.tripmate.domain.searchplan.dto.SearchKeywordDTO;
import com.tripmate.domain.searchplan.dto.SearchUserRecommendationDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchPlanDAO {
    private final SqlSession sqlSession;

    public int insertSearchKeyword(SearchKeywordDTO searchKeywordDTO) {
        return sqlSession.getMapper(SearchPlanDAOMapper.class).insertSearchKeyword(searchKeywordDTO);
    }

    public List<PlanBasicInfoVO> searchPlanListByKeyword(SearchKeywordDTO searchKeywordDTO) {
        return sqlSession.getMapper(SearchPlanDAOMapper.class).searchPlanListByKeyword(searchKeywordDTO);
    }

    public List<PlanBasicInfoVO> searchPlanListByAttribute(SearchAttributeDTO searchAttributeDTO) {
        return sqlSession.getMapper(SearchPlanDAOMapper.class).searchPlanListByAttribute(searchAttributeDTO);
    }

    public List<String> searchPopularSearchKeyword() {
        return sqlSession.getMapper(SearchPlanDAOMapper.class).searchPopularSearchKeyword();
    }

    public List<String> searchPopularHashtag() {
        return sqlSession.getMapper(SearchPlanDAOMapper.class).searchPopularHashtag();
    }
}
