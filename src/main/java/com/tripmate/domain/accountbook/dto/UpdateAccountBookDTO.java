package com.tripmate.domain.accountbook.dto;


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
@Schema(description = "여행가계부 항목 수정 Request DTO")
public class UpdateAccountBookDTO {

    @NotBlank(message = "여행가계부 항목 번호를 입력해주세요.")
    @Schema(description = "여행가계부 항목 번호", example = "1")
    private String accountNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    @Schema(description = "회원 번호", example = "1")
    private String memberNo;

    @Schema(description = "플랜 번호", example = "1")
    private String planNo;

    @Schema(description = "게시글 번호", example = "1")
    private String postNo;

    @Schema(description = "정렬순서", example = "1")
    private String sortSequence;

    @Schema(description = "여행가계부 금액", example = "10000")
    private String amount;
    
}
