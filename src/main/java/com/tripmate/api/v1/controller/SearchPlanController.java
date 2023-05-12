package com.tripmate.api.v1.controller;

import com.tripmate.domain.common.vo.ResponseWrapper;
import com.tripmate.domain.plans.vo.PlanBasicInfoVO;
import com.tripmate.domain.searchplan.dto.SearchAttributeDTO;
import com.tripmate.domain.searchplan.dto.SearchKeywordDTO;
import com.tripmate.domain.searchplan.service.SearchPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Slf4j
@RestController
@Tag(name = "Search", description = "검색 API")
@RequestMapping("v1/search-plan")
@Validated
@RequiredArgsConstructor
public class SearchPlanController {
    private final SearchPlanService searchPlanService;

    @Operation(summary = "일반 검색 (키워드 검색)", description = "해당 키워드가 포함된 플랜 목록을 조회합니다.")
    @GetMapping("/keyword")
    public ResponseWrapper<PlanBasicInfoVO> searchPlanByKeyword(@RequestParam(value = "memberNo") @Schema(description = "회원 번호", example = "1") @NotBlank String memberNo,
                                                                @RequestParam(value = "keyword") @Schema(description = "검색어", example = "검색어") @NotBlank @Size(min = 1, max = 100) String keyword) {
        return ResponseWrapper.<PlanBasicInfoVO>builder()
                .data(searchPlanService.searchPlanListByKeyword(SearchKeywordDTO.builder()
                        .memberNo(memberNo)
                        .keyword(keyword)
                        .build()))
                .build();
    }

    @Operation(summary = "속성 검색", description = "속성에 부합하는 플랜 목록을 조회합니다.")
    @PostMapping("/attribute")
    public ResponseWrapper<PlanBasicInfoVO> searchPlanByKeyword(@Valid @RequestBody SearchAttributeDTO searchAttributeDTO) {
        return ResponseWrapper.<PlanBasicInfoVO>builder()
                .data(searchPlanService.searchPlanListByAttribute(searchAttributeDTO))
                .build();
    }

    @Operation(summary = "인기 검색어(추천 검색어) 조회", description = "인기 검색어(추천 검색어)를 조회합니다.")
    @GetMapping("/popular-keyword")
    public ResponseWrapper<String> searchPopularSearchKeyword() {
        return ResponseWrapper.<String>builder()
                .data(searchPlanService.searchPopularSearchKeyword())
                .build();
    }

    @Operation(summary = "인기 해시태그 조회", description = "인기 검색 키워드를 조회합니다.")
    @GetMapping("/popular-hashtag")
    public ResponseWrapper<String> searchPopularHashtag() {
        return ResponseWrapper.<String>builder()
                .data(searchPlanService.searchPopularHashtag())
                .build();
    }
}
