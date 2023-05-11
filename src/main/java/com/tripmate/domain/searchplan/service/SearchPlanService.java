package com.tripmate.domain.searchplan.service;

import com.tripmate.domain.plans.vo.PlanBasicInfoVO;
import com.tripmate.domain.searchplan.dto.SearchAttributeDTO;
import com.tripmate.domain.searchplan.dto.SearchKeywordDTO;

import java.util.List;

public interface SearchPlanService {
    List<PlanBasicInfoVO> searchPlanListByKeyword(SearchKeywordDTO searchKeywordDTO);
    List<PlanBasicInfoVO> searchPlanListByAttribute(SearchAttributeDTO searchAttributeDTO);
}
