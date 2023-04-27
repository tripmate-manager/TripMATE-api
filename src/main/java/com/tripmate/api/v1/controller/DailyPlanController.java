package com.tripmate.api.v1.controller;

import com.tripmate.common.config.ValidationSequence;
import com.tripmate.domain.common.vo.ResponseWrapper;
import com.tripmate.domain.dailyplans.dto.DailyPlanByDayDTO;
import com.tripmate.domain.dailyplans.dto.DailyPlanDTO;
import com.tripmate.domain.dailyplans.dto.DeleteDailyPlanDTO;
import com.tripmate.domain.dailyplans.dto.DeleteDailyPlanNotificationDTO;
import com.tripmate.domain.dailyplans.service.DailyPlanService;
import com.tripmate.domain.dailyplans.vo.DailyPlanCntVO;
import com.tripmate.domain.dailyplans.vo.DailyPlanVO;
import com.tripmate.domain.plans.dto.NotificationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Collections;

@Slf4j
@RestController
@Tag(name = "DailyPlan", description = "데일리플랜 API")
@RequestMapping("v1/dailyplans")
@Validated
@RequiredArgsConstructor
public class DailyPlanController {
    private final DailyPlanService dailyPlanService;

    @Operation(summary = "데일리플랜 추가(북마크 추가)", description = "해당 게시글을 데일리플랜에 추가합니다.(북마크기능)")
    @PostMapping("/dailyplan")
    public ResponseWrapper<Boolean> insertDailyPlan(@Validated(ValidationSequence.class) @RequestBody DailyPlanDTO dailyPlanDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(dailyPlanService.insertDailyPlan(dailyPlanDTO)))
                .build();
    }

    @Operation(summary = "데일리플랜 삭제(북마크 해제)", description = "해당 게시글을 데일리플랜에서 삭제합니다.(북마크기능)")
    @PostMapping("/dailyplan/{dailyPlanNo}")
    public ResponseWrapper<String> deleteDailyPlan(@PathVariable(value = "dailyPlanNo") @NotBlank @Schema(description = "데일리플랜 번호", example = "1") String dailyPlanNo,
                                                   @Valid @RequestBody DeleteDailyPlanDTO deleteDailyPlanDTO) {
        return ResponseWrapper.<String>builder()
                .data(dailyPlanService.deleteDailyPlan(dailyPlanNo, deleteDailyPlanDTO))
                .build();
    }

    @Operation(summary = "플랜 일자별 데일리플랜 개수 조회", description = "해당 플랜에 대해 일자별 데일리플랜 개수를 조회합니다.")
    @GetMapping("/dailyplan-count/{planNo}")
    public ResponseWrapper<DailyPlanCntVO> searchDailyPlanCntByDay(@PathVariable(value = "planNo") @NotBlank @Schema(description = "플랜번호", example = "1") String planNo) {
        return ResponseWrapper.<DailyPlanCntVO>builder()
                .data(dailyPlanService.searchDailyPlanCntByDay(planNo))
                .build();
    }

    @Operation(summary = "플랜 일자별 데일리플랜 목록 조회", description = "해당 플랜에 대해 일자별 데일리플랜 목록을 조회합니다.")
    @GetMapping("/dailyplan/{planNo}")
    public ResponseWrapper<DailyPlanVO> searchDailyPlanListByDay(@PathVariable(value = "planNo") @NotBlank @Schema(description = "플랜 번호", example = "1") String planNo,
                                                                 @RequestParam(value = "memberNo") @NotBlank @Schema(description = "회원 번호", example = "1") String memberNo,
                                                                 @RequestParam(value = "dayGroup") @NotBlank @Schema(description = "플랜 일자", example = "1") String dayGroup) {
        return ResponseWrapper.<DailyPlanVO>builder()
                .data(dailyPlanService.searchDailyPlanListByDay(DailyPlanByDayDTO.builder()
                        .planNo(planNo)
                        .memberNo(memberNo)
                        .dayGroup(dayGroup)
                        .build()))
                .build();
    }

    @Operation(summary = "데일리플랜 알림 삭제", description = "데일리플랜 알림을 삭제합니다.")
    @DeleteMapping("/notification/{dailyPlanNo}")
    public ResponseWrapper<Boolean> deleteDailyPlanNotification(@PathVariable(value = "dailyPlanNo") @Schema(description = "데일리플랜 번호", example = "1") String dailyPlanNo,
                                                                @RequestParam(value = "memberNo") @NotBlank @Schema(description = "회원 번호", example = "1") String memberNo) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(dailyPlanService.deleteDailyPlanNotification(DeleteDailyPlanNotificationDTO.builder()
                        .dailyPlanNo(dailyPlanNo)
                        .memberNo(memberNo).build())))
                .build();
    }

    @Operation(summary = "데일리플랜 알림 수정", description = "데일리플랜 알림을 수정합니다.")
    @PutMapping("/notification/{dailyPlanNo}")
    public ResponseWrapper<Boolean> updateDailyPlanNotification(@PathVariable(value = "dailyPlanNo") @Schema(description = "데일리플랜 번호", example = "1") String dailyPlanNo,
                                                                @Validated(ValidationSequence.class) @RequestBody NotificationDTO notificationDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(dailyPlanService.updateDailyPlanNotification(dailyPlanNo, notificationDTO)))
                .build();
    }
}
