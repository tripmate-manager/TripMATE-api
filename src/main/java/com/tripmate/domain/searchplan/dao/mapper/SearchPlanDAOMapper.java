package com.tripmate.domain.searchplan.dao.mapper;

import com.tripmate.domain.plans.vo.PlanBasicInfoVO;
import com.tripmate.domain.searchplan.dto.SearchAttributeDTO;
import com.tripmate.domain.searchplan.dto.SearchKeywordDTO;
import com.tripmate.domain.searchplan.dto.SearchUserRecommendationDTO;

import java.util.List;

public interface SearchPlanDAOMapper {
    int insertSearchKeyword(SearchKeywordDTO searchKeywordDTO);
    List<PlanBasicInfoVO> searchPlanListByKeyword(SearchKeywordDTO searchKeywordDTO);
    List<PlanBasicInfoVO> searchPlanListByAttribute(SearchAttributeDTO searchAttributeDTO);
    List<String> searchPopularSearchKeyword();
    List<String> searchPopularHashtag();
    List<PlanBasicInfoVO> searchUserRecommendationPlanList(SearchUserRecommendationDTO searchUserRecommendationDTO);
}
