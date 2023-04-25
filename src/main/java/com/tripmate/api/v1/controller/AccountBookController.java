package com.tripmate.api.v1.controller;

import com.tripmate.domain.accountbook.dto.AccountBookDTO;
import com.tripmate.domain.accountbook.dto.UpdateAccountBookDTO;
import com.tripmate.domain.accountbook.service.AccountBookService;
import com.tripmate.domain.accountbook.vo.AccountBookVO;
import com.tripmate.domain.common.vo.ResponseWrapper;
import com.tripmate.domain.dailyplans.dto.DailyPlanByDayDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@Slf4j
@RestController
@Tag(name = "AccountBook", description = "여행가계부 API")
@RequestMapping("v1/accountbook")
@Validated
@RequiredArgsConstructor
public class AccountBookController {
    private final AccountBookService accountBookService;

    @Operation(summary = "일자별 여행 가계부 목록 조회", description = "일자별 여행 가계부 목록 조회을 조회합니다.")
    @GetMapping("/{planNo}/{dayGroup}")
    public ResponseWrapper<AccountBookVO> searchAccountListByDay(@PathVariable(value = "planNo") @Schema(description = "플랜번호", example = "1") String planNo,
                                                                  @PathVariable(value = "dayGroup") @Schema(description = "Day 그룹", example = "1") String dayGroup) {
        return ResponseWrapper.<AccountBookVO>builder()
                .data(Collections.singletonList(accountBookService.searchAccountListByDay(DailyPlanByDayDTO.builder()
                        .planNo(planNo)
                        .dayGroup(dayGroup).build())))
                .build();
    }

    @Operation(summary = "여행 가계부 항목 추가", description = "여행 가계부 항목을 추가합니다.")
    @PostMapping("/{planNo}")
    public ResponseWrapper<Boolean> insertAccount(@PathVariable(value = "planNo") @Schema(description = "플랜번호", example = "1") String planNo,
                                                  @Valid @RequestBody AccountBookDTO accountBookDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(accountBookService.insertAccountWithAccountBookDTO(planNo, accountBookDTO)))
                .build();
    }

    @Operation(summary = "여행 가계부 항목 금액 변경", description = "여행 가계부 항목의 금액을 변경합니다.")
    @PutMapping("/amount/{planNo}")
    public ResponseWrapper<Boolean> updateAccountAmount(@PathVariable(value = "planNo") @Schema(description = "플랜번호", example = "1") String planNo,
                                                  @Valid @RequestBody UpdateAccountBookDTO updateAccountBookDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(accountBookService.updateAccountAmount(planNo, updateAccountBookDTO)))
                .build();
    }

    @Operation(summary = "여행 가계부 항목 정렬 순서 변경", description = "여행 가계부 항목의 정렬 순서를 변경합니다.")
    @PutMapping("/sorting/{planNo}")
    public ResponseWrapper<Boolean> updateAccountSortSequence(@PathVariable(value = "planNo") @Schema(description = "플랜번호", example = "1") String planNo,
                                                        @Valid @RequestBody UpdateAccountBookDTO updateAccountBookDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(accountBookService.updateAccountSortSequence(planNo, updateAccountBookDTO)))
                .build();
    }

}
