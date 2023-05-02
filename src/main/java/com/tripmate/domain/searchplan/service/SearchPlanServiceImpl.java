package com.tripmate.domain.searchplan.service;

import com.tripmate.common.exception.GuideMessageException;
import com.tripmate.domain.searchplan.dao.SearchPlanDAO;
import com.tripmate.domain.searchplan.dto.SearchKeywordDTO;
import com.tripmate.domain.searchplan.vo.SearchPlanResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchPlanServiceImpl implements SearchPlanService {
    private final SearchPlanDAO searchPlanDAO;

    @Override
    @Transactional
    public List<SearchPlanResultVO> searchPlanListByKeyword(SearchKeywordDTO searchKeywordDTO) {
        if (searchPlanDAO.insertSearchKeyword(searchKeywordDTO) != 1) {
            throw new GuideMessageException("검색 중 오류가 발생했습니다.");
        }

        return searchPlanDAO.searchPlanListByKeyword(searchKeywordDTO);
    }
}
