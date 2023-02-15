package com.tripmate.api.v1.controller;

import com.tripmate.domain.common.vo.ResponseWrapper;
import com.tripmate.domain.plans.dto.CreatePlanDTO;
import com.tripmate.domain.plans.service.PlanService;
import com.tripmate.domain.plans.vo.PlanAddressVO;
import com.tripmate.domain.plans.vo.PlanAttributeVO;
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
    public ResponseWrapper<PlanAttributeVO> selectPlanAttributeList(@RequestParam(value = "attributeTypeCode") @NotBlank @Pattern(regexp = "^[12]0$") @Schema(example = "플랜속성타입코드(10: 해시태그, 20: 여행테마)") String attributeTypeCode) {
        return ResponseWrapper.<PlanAttributeVO>builder()
                .data(planService.selectPlanAttributeList(attributeTypeCode))
                .build();
    }

    @Operation(summary = "지역 정보 조회", description = "시도 지역 정보를 조회합니다.")
    @GetMapping("/trip-address")
    public ResponseWrapper<PlanAddressVO> selectAddressList() {
        return ResponseWrapper.<PlanAddressVO>builder()
                .data(planService.selectAddressList())
                .build();
    }

    @Operation(summary = "지역 정보 조회", description = "시도별 시군구 지역 정보를 조회합니다.")
    @GetMapping("/trip-address/{sidoName}")
    public ResponseWrapper<PlanAddressVO> selectAddressList(@PathVariable(value = "sidoName") @NotBlank @Schema(example = "시도명") String sidoName) {
        return ResponseWrapper.<PlanAddressVO>builder()
                .data(planService.selectAddressList(sidoName))
                .build();
    }

    @Operation(summary = "플랜 생성", description = "플랜을 생성합니다. (return: 플랜 생성 성공 여부)")
    @PostMapping
    public ResponseWrapper<Boolean> createPlan(@Valid @RequestBody CreatePlanDTO createPlanDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(planService.createPlan(createPlanDTO)))
                .build();
    }
}
