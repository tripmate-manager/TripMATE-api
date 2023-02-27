package com.tripmate.api.v1.controller;

import com.tripmate.domain.common.vo.ResponseWrapper;
import com.tripmate.domain.plans.dto.PlanDTO;
import com.tripmate.domain.plans.service.PlanService;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;
import com.tripmate.domain.plans.vo.PlanMateVO;
import com.tripmate.domain.plans.vo.PlanVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collections;

@Slf4j
@RestController
@Tag(name = "플랜 API", description = "Plan API")
@RequestMapping("v1/plans")
@Validated
public class PlanController {
    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @Operation(summary = "플랜 속성 조회", description = "플랜 속성을 조회합니다.")
    @GetMapping("/plan-attributes")
    public ResponseWrapper<PlanAttributeVO> searchPlanAttributeList(@RequestParam(value = "attributeTypeCode") @NotBlank @Pattern(regexp = "^[12]0$") @Schema(example = "플랜속성타입코드(10: 해시태그, 20: 여행테마)") String attributeTypeCode) {
        return ResponseWrapper.<PlanAttributeVO>builder()
                .data(planService.searchPlanAttributeList(attributeTypeCode))
                .build();
    }

    @Operation(summary = "지역 정보 조회", description = "시도 지역 정보를 조회합니다.")
    @GetMapping("/trip-address")
    public ResponseWrapper<PlanAddressVO> searchAddressList() {
        return ResponseWrapper.<PlanAddressVO>builder()
                .data(planService.searchAddressList())
                .build();
    }

    @Operation(summary = "지역 정보 조회", description = "시도별 시군구 지역 정보를 조회합니다.")
    @GetMapping("/trip-address/{sidoName}")
    public ResponseWrapper<PlanAddressVO> searchAddressList(@PathVariable(value = "sidoName") @NotBlank @Schema(example = "시도명") String sidoName) {
        return ResponseWrapper.<PlanAddressVO>builder()
                .data(planService.searchAddressList(sidoName))
                .build();
    }

    @Operation(summary = "플랜 생성", description = "플랜을 생성합니다. (return: 플랜 생성 성공 여부)")
    @PostMapping
    public ResponseWrapper<Boolean> createPlan(@Valid @RequestBody PlanDTO planDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(planService.createPlan(planDTO)))
                .build();
    }

    @Operation(summary = "회원 플랜 조회", description = "회원의 플랜을 조회합니다. (return: 플랜 리스트)")
    @GetMapping
    public ResponseWrapper<PlanVO> searchMemberPlanList(@RequestParam(value = "memberNo") @NotBlank @Schema(example = "회원번호") String memberNo) {
        return ResponseWrapper.<PlanVO>builder()
                .data(planService.searchMemberPlanList(memberNo))
                .build();
    }

    @Operation(summary = "플랜 상세 조회", description = "플랜 상세 정보를 조회합니다. (return: 플랜 정보)")
    @GetMapping("/{planNo}")
    public ResponseWrapper<PlanVO> getPlanInfo(@Valid @PathVariable(value = "planNo") @Schema(example = "플랜번호") String planNo) {
        return ResponseWrapper.<PlanVO>builder()
                .data(Collections.singletonList(planService.getPlanInfo(planNo)))
                .build();
    }

    @Operation(summary = "플랜 메이트 조회", description = "입력한 플랜의 플랜 메이트 목록을 조회합니다. (return: 플랜 메이트 정보)")
    @GetMapping("/plan-mate/{planNo}")
    public ResponseWrapper<PlanMateVO> searchPlanMateList(@Valid @PathVariable(value = "planNo") @Schema(example = "플랜번호") String planNo) {
        return ResponseWrapper.<PlanMateVO>builder()
                .data(planService.searchPlanMateList(planNo))
                .build();
    }
}
