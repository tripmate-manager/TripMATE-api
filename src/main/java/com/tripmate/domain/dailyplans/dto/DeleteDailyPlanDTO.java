package com.tripmate.domain.dailyplans.dto;

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
@Schema(description = "데일리플랜 삭제 Request DTO")
public class DeleteDailyPlanDTO {
    @NotBlank(message = "데일리플랜 번호를 입력해주세요.")
    @Schema(description = "데일리플랜 번호", example = "1")
    private String dailyPlanNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    @Schema(description = "회원 번호", example = "1")
    private String memberNo;
}
