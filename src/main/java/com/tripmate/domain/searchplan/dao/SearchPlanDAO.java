package com.tripmate.domain.searchplan.dao;

import com.tripmate.domain.searchplan.dao.mapper.SearchPlanDAOMapper;
import com.tripmate.domain.searchplan.dto.SearchAttributeDTO;
import com.tripmate.domain.searchplan.dto.SearchKeywordDTO;
import com.tripmate.domain.searchplan.vo.SearchPlanResultVO;
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

    public List<SearchPlanResultVO> searchPlanListByKeyword(SearchKeywordDTO searchKeywordDTO) {
        return sqlSession.getMapper(SearchPlanDAOMapper.class).searchPlanListByKeyword(searchKeywordDTO);
    }

    public List<SearchPlanResultVO> searchPlanListByAttribute(SearchAttributeDTO searchAttributeDTO) {
        return sqlSession.getMapper(SearchPlanDAOMapper.class).searchPlanListByAttribute(searchAttributeDTO);
    }
}
