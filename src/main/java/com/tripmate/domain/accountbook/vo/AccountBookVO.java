package com.tripmate.domain.accountbook.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "여행가계부 조회 Response VO")
public class AccountBookVO {

    @Schema(description = "플랜 번호", example = "1")
    private String planNo;

    @Schema(description = "Day 그룹", example = "1")
    private String dayGroup;

    @Schema(description = "여행 기간", example = "1")
    private String tripTerm;

    @Schema(description = "플랜 일자별 사용 경비 총액", example = "10,000")
    private int dayAmountSum;

    @Schema(description = "플랜메이트별 사용 경비 총액", example = "10,000")
    private int planMateAmountSum;

    @Schema(description = "플랜 사용 경비 총액", example = "50,000")
    private int planAmountSum;

    List<AccountVO> accountList;
}
