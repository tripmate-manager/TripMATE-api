package com.tripmate.domain.plans.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "플랜메이트 조회/검색 Response VO")
public class PlanMateVO {
    @Schema(description = "플랜메이트번호", example = "1")
    private int mateNo;

    @Schema(description = "플랜번호", example = "1")
    private int planNo;

    @Schema(description = "회원번호", example = "1")
    private int memberNo;

    @Pattern(regexp = "^[YN]$", message = "플랜 리더(생성자) 여부는 Y, N만 입력 가능합니다.")
    @Schema(description = "플랜리더여부", example = "Y")
    private String leadYn;

    @Schema(description = "회원 아이디", example = "회원 아이디")
    private String memberId;

    @Schema(description = "회원 이름", example = "회원 이름")
    private String memberName;

    @Schema(description = "닉네임", example = "닉네임")
    private String nickName;
}
