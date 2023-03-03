package com.tripmate.domain.plans.dto;


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
@Schema(description = "플랜 인증코드 생성 Request DTO")
public class PlanAuthCodeDTO {
    @Hidden
    private int inviteCodeNo;

    @NotBlank(message = "플랜 번호를 입력해주세요.")
    @Schema(description = "플랜 번호")
    private String planNo;

    @NotBlank(message = "초대 타입 코드를 입력해주세요.")
    @Schema(description = "초대 타입 코드")
    private String inviteTypeCode;

    @Hidden
    private String inviteCode;
}
