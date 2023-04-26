package com.tripmate.domain.accountbook.dto;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "여행가계부 항목 추가 Request DTO")
public class AccountBookDTO {

    @Hidden
    @Schema(description = "여행가계부 항목 번호", example = "1")
    private String accountNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    @Schema(description = "회원 번호", example = "1")
    private String memberNo;

    @NotBlank(message = "플랜 번호를 입력해주세요.")
    @Schema(description = "플랜 번호", example = "1")
    private String planNo;

    @NotBlank(message = "게시글 타입 코드를 입력해주세요.")
    @Schema(description = "게시글 타입 코드(10: 숙소, 20: 관광지, 30: 식당, 90: 기타)", example = "10")
    private String postTypeCode;

    @NotBlank(message = "여행가계부 항목명을 입력해주세요.")
    @Schema(description = "여행가계부 항목명", example = "여행가계부 항목명")
    private String accountName;

    @NotBlank(message = "Day 그룹을 입력해주세요.")
    @Schema(description = "Day 그룹", example = "1")
    private String dayGroup;

    @NotBlank(message = "여행가계부 금액(사용 경비)을 입력해주세요.")
    @Schema(description = "여행가계부 금액", example = "10000")
    private String amount;
    
}
