package com.tripmate.api.v1.controller;

import com.tripmate.domain.common.vo.ResponseWrapper;
import com.tripmate.domain.dailyplans.dto.DailyPlanByDayDTO;
import com.tripmate.domain.dailyplans.dto.DailyPlanCntVO;
import com.tripmate.domain.dailyplans.dto.DailyPlanDTO;
import com.tripmate.domain.dailyplans.dto.DailyPlanVO;
import com.tripmate.domain.dailyplans.dto.DeleteDailyPlanDTO;
import com.tripmate.domain.dailyplans.service.DailyPlanService;
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
import java.util.Collections;

@Slf4j
@RestController
@Tag(name = "데일리플랜 API", description = "DailyPlan API")
@RequestMapping("v1/dailyplans")
@Validated
public class DailyPlanController {
    private final DailyPlanService dailyPlanService;

    @Autowired
    public DailyPlanController(DailyPlanService dailyPlanService) {
        this.dailyPlanService = dailyPlanService;
    }

    @Operation(summary = "데일리플랜 추가(북마크 추가)", description = "해당 게시글을 데일리플랜에 추가합니다.(북마크기능)")
    @PostMapping("/dailyplan")
    public ResponseWrapper<Boolean> insertDailyPlan(@Valid @RequestBody DailyPlanDTO dailyPlanDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(dailyPlanService.insertDailyPlan(dailyPlanDTO)))
                .build();
    }

    @Operation(summary = "데일리플랜 삭제(북마크 해제)", description = "해당 게시글을 데일리플랜에서 삭제합니다.(북마크기능)")
    @PostMapping("/dailyplan/{dailyPlanNo}")
    public ResponseWrapper<Boolean> deleteDailyPlan(@PathVariable(value = "dailyPlanNo") @Schema(example = "데일리플랜 번호") String dailyPlanNo,
                                                    @Valid @RequestBody DeleteDailyPlanDTO deleteDailyPlanDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(dailyPlanService.deleteDailyPlan(dailyPlanNo, deleteDailyPlanDTO)))
                .build();
    }

    @Operation(summary = "플랜 일자별 데일리플랜 개수 조회", description = "해당 플랜에 대해 일자별 데일리플랜 개수를 조회합니다.")
    @GetMapping("/dailyplan-count/{planNo}")
    public ResponseWrapper<DailyPlanCntVO> searchDailyPlanCntByDay(@PathVariable(value = "planNo") @Schema(example = "플랜 번호") String planNo) {
        return ResponseWrapper.<DailyPlanCntVO>builder()
                .data(dailyPlanService.searchDailyPlanCntByDay(planNo))
                .build();
    }

    @Operation(summary = "플랜 일자별 데일리플랜 목록 조회", description = "해당 플랜에 대해 일자별 데일리플랜 목록을 조회합니다.")
    @GetMapping("/dailyplan/{planNo}")
    public ResponseWrapper<DailyPlanVO> searchDailyPlanListByDay(@PathVariable(value = "planNo") @Schema(example = "플랜 번호") String planNo,
                                                                 @RequestParam(value = "dayGroup") @Schema(example = "플랜 일자") @NotBlank String dayGroup) {
        return ResponseWrapper.<DailyPlanVO>builder()
                .data(dailyPlanService.searchDailyPlanListByDay(DailyPlanByDayDTO.builder()
                                                                .planNo(planNo)
                                                                .dayGroup(dayGroup)
                                                                .build()))
                .build();
    }
}
