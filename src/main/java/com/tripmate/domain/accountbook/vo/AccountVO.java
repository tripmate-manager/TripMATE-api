package com.tripmate.domain.accountbook.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "여행가계부 조회 Response VO")
public class AccountVO {

    @Schema(description = "여행가계부 항목 번호", example = "1")
    private String accountNo;

    @Schema(description = "데일리플랜 번호", example = "1")
    private String dailyPlanNo;

    @Schema(description = "게시글 번호", example = "1")
    private String postNo;

    @Schema(description = "게시글 타입 코드(10: 숙소, 20: 관광지, 30: 식당, 90: 기타)", example = "10")
    private String postTypeCode;

    @Schema(description = "여행가계부 항목명", example = "여행가계부 항목명")
    private String accountName;

    @Schema(description = "정렬순서", example = "1")
    private String sortSequence;

    @Schema(description = "여행가계부 금액", example = "10000")
    private String amount;
    
}
