package com.tripmate.domain.searchplan.service;

import com.tripmate.domain.searchplan.dto.SearchAttributeDTO;
import com.tripmate.domain.searchplan.dto.SearchKeywordDTO;
import com.tripmate.domain.searchplan.vo.SearchPlanResultVO;

import java.util.List;

public interface SearchPlanService {
    List<SearchPlanResultVO> searchPlanListByKeyword(SearchKeywordDTO searchKeywordDTO);
    List<SearchPlanResultVO> searchPlanListByAttribute(SearchAttributeDTO searchAttributeDTO);
}
