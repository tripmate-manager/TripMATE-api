package com.tripmate.api.v1.controller;

import com.tripmate.domain.common.vo.ResponseWrapper;
import com.tripmate.domain.plans.dto.ExitPlanDTO;
import com.tripmate.domain.plans.dto.NotificationDTO;
import com.tripmate.domain.plans.dto.PlanDTO;
import com.tripmate.domain.plans.service.PlanService;
import com.tripmate.domain.plans.vo.NotificationVO;
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
import org.springframework.web.bind.annotation.PutMapping;
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
    @PostMapping("/create-plan")
    public ResponseWrapper<Integer> createPlan(@Valid @RequestBody PlanDTO planDTO) {
        return ResponseWrapper.<Integer>builder()
                .data(Collections.singletonList(planService.createPlan(planDTO)))
                .build();
    }

    @Operation(summary = "회원 플랜 조회", description = "회원의 플랜을 조회합니다. (return: 플랜 리스트)")
    @GetMapping("/{memberNo}")
    public ResponseWrapper<PlanVO> searchMemberPlanList(@Valid @PathVariable(value = "memberNo") @NotBlank @Schema(example = "회원번호") String memberNo) {
        return ResponseWrapper.<PlanVO>builder()
                .data(planService.searchMemberPlanList(memberNo))
                .build();
    }

    @Operation(summary = "플랜 상세 조회", description = "플랜 상세 정보를 조회합니다. (return: 플랜 정보)")
    @GetMapping("/plan-detail/{planNo}")
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

    @Operation(summary = "플랜 수정", description = "플랜을 수정합니다. (return: 플랜 정보)")
    @PutMapping("/{planNo}")
    public ResponseWrapper<Boolean> updatePlan(@Valid @PathVariable(value = "planNo") @Schema(example = "플랜번호") String planNo, @Valid @RequestBody PlanDTO planDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(planService.updatePlan(planNo, planDTO)))
                .build();
    }

    @Operation(summary = "회원(플랜메이트) 검색", description = "입력한 아이디/닉네임/이메일 조건에 부합하는 회원을 조회합니다.")
    @GetMapping("/search-member")
    public ResponseWrapper<PlanMateVO> searchMemberList(@RequestParam(value = "searchDiviCode") @NotBlank @Pattern(regexp = "^[123]0$") @Schema(example = "검색구분코드(10: 아이디, 20: 닉네임, 30: 이메일)") String searchDiviCode,
                                                        @RequestParam(value = "searchKeyword") @NotBlank @Schema(example = "검색어") String searchKeyword) {
        return ResponseWrapper.<PlanMateVO>builder()
                .data(planService.searchMemberList(searchDiviCode, searchKeyword))
                .build();
    }

    @Operation(summary = "초대 인증코드 생성", description = "비회원 초대 시 인증코드를 생성합니다.")
    @PostMapping("/invite-code")
    public ResponseWrapper<String> createInviteAuthCode(@RequestParam(value = "planNo") @NotBlank @Schema(example = "플랜번호") String planNo,
                                                        @RequestParam(value = "inviteTypeCode") @NotBlank @Pattern(regexp = "^[12]0$") @Schema(example = "초대타입코드(10: 회원, 20: 비회원)") String inviteTypeCode) {
        return ResponseWrapper.<String>builder()
                .data(Collections.singletonList(planService.createInviteAuthCode(planNo, inviteTypeCode)))
                .build();
    }

    @Operation(summary = "알림 생성", description = "알림을 생성합니다.")
    @PostMapping("/notification")
    public ResponseWrapper<Boolean> createNotification(@Valid @RequestBody NotificationDTO notificationDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(planService.createNotification(notificationDTO)))
                .build();
    }

    @Operation(summary = "알림 조회", description = "알림을 조회합니다.")
    @GetMapping("/notification")
    public ResponseWrapper<NotificationVO> searchNotificationList(@Valid @RequestParam(value = "memberNo") @NotBlank @Schema(example = "회원번호") String memberNo) {
        return ResponseWrapper.<NotificationVO>builder()
                .data(planService.searchNotificationList(memberNo))
                .build();
    }

    @Operation(summary = "미확인 알림 카운트 조회", description = "읽지 않은 알림 카운트 수를 조회합니다.")
    @GetMapping("/notification/unread")
    public ResponseWrapper<Integer> getUnreadNotificationCnt(@Valid @RequestParam(value = "memberNo") @NotBlank @Schema(example = "회원번호") String memberNo) {
        return ResponseWrapper.<Integer>builder()
                .data(Collections.singletonList(planService.getUnreadNotificationCnt(memberNo)))
                .build();
    }

    @Operation(summary = "알림 확인 일시 수정", description = "알림 확인 일시를 수정합니다.")
    @PutMapping("/notification")
    public ResponseWrapper<Boolean> updateNotificationReadDateTime(@Valid @RequestParam(value = "memberNo") @NotBlank @Schema(example = "회원번호") String memberNo,
                                                                   @Valid @RequestParam(value = "notificationNo") @NotBlank @Schema(example = "알림번호") String notificationNo) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(planService.updateNotificationReadDateTime(memberNo, notificationNo)))
                .build();
    }

    @Operation(summary = "플랜 나가기", description = "플랜 나가기 처리합니다.")
    @PostMapping("/exit-plan")
    public ResponseWrapper<Boolean> exitPlanMember(@Valid @RequestBody ExitPlanDTO exitPlanDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(planService.exitPlan(exitPlanDTO)))
                .build();
    }
}
