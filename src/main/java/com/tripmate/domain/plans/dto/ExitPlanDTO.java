package com.tripmate.domain.plans.dto;

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
@Schema(description = "플랜 나가기 Request DTO")
public class ExitPlanDTO {
    @Schema(description = "리더 권한 변경할 메이트번호 (플랜을 나가는 회원이 리더인 경우에만 해당 메이트의 리더 권한을 변경합니다.)", example = "1")
    private String mateNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    @Schema(description = "회원번호", example = "1")
    private String memberNo;

    @NotBlank(message = "플랜 번호를 입력해주세요.")
    @Schema(description = "플랜번호", example = "1")
    private String planNo;
}
