package com.tripmate.domain.searchplan.service;

import com.tripmate.common.exception.GuideMessageException;
import com.tripmate.domain.members.dao.MemberDAO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.plans.vo.PlanBasicInfoVO;
import com.tripmate.domain.searchplan.dao.SearchPlanDAO;
import com.tripmate.domain.searchplan.dto.SearchAttributeDTO;
import com.tripmate.domain.searchplan.dto.SearchKeywordDTO;
import com.tripmate.domain.searchplan.dto.SearchUserRecommendationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchPlanServiceImpl implements SearchPlanService {
    private final SearchPlanDAO searchPlanDAO;
    private final MemberDAO memberDAO;

    @Override
    @Transactional
    public List<PlanBasicInfoVO> searchPlanListByKeyword(SearchKeywordDTO searchKeywordDTO) {
        if (searchPlanDAO.insertSearchKeyword(searchKeywordDTO) != 1) {
            throw new GuideMessageException("검색 중 오류가 발생했습니다.");
        }

        return searchPlanDAO.searchPlanListByKeyword(searchKeywordDTO);
    }

    @Override
    public List<PlanBasicInfoVO> searchPlanListByAttribute(SearchAttributeDTO searchAttributeDTO) {
        return searchPlanDAO.searchPlanListByAttribute(searchAttributeDTO);
    }

    @Override
    public List<String> searchPopularSearchKeyword() {
        return searchPlanDAO.searchPopularSearchKeyword();
    }

    @Override
    public List<String> searchPopularHashtag() {
        return searchPlanDAO.searchPopularHashtag();
    }

    @Override
    public List<PlanBasicInfoVO> searchUserRecommendationPlanList(String memberNo) {
        MemberDTO memberInfo = memberDAO.getMemberInfoWithMemberNo(Integer.parseInt(memberNo));

        Date date = new Date();
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        int age = (Integer.parseInt(year.format(date)) - Integer.parseInt(memberInfo.getBirthDay().substring(0,4))) / 10 * 10;
        age = age == 0 ? 10 : age;

        return searchPlanDAO.searchUserRecommendationPlanList(SearchUserRecommendationDTO.builder()
                .memberNo(memberNo)
                .age(String.valueOf(age))
                .genderCode(memberInfo.getGenderCode())
                .build());
    }
}
