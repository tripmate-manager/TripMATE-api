package com.tripmate.domain.searchplan.dao.mapper;

import com.tripmate.domain.searchplan.dto.SearchAttributeDTO;
import com.tripmate.domain.searchplan.dto.SearchKeywordDTO;
import com.tripmate.domain.searchplan.vo.SearchPlanResultVO;

import java.util.List;

public interface SearchPlanDAOMapper {
    int insertSearchKeyword(SearchKeywordDTO searchKeywordDTO);
    List<SearchPlanResultVO> searchPlanListByKeyword(SearchKeywordDTO searchKeywordDTO);
    List<SearchPlanResultVO> searchPlanListByAttribute(SearchAttributeDTO searchAttributeDTO);
}
