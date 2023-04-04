package com.tripmate.domain.dailyplans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "데일리플랜 등록 Request DTO")
public class DailyPlanDTO {
    @Schema(description = "데일리플랜 번호", example = "1")
    private String dailyPlanNo;

    @NotBlank(message = "플랜 번호를 입력해주세요.")
    @Schema(description = "플랜 번호", example = "1")
    private String planNo;

    @NotBlank(message = "게시글 번호를 입력해주세요.")
    @Schema(description = "게시글 번호", example = "1")
    private String postNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    @Schema(description = "회원 번호", example = "1")
    private String memberNo;

    @NotBlank(message = "데일리플랜 그룹 번호를 입력해주세요.")
    @Schema(description = "데일리플랜 그룹 번호", example = "1")
    private String dayGroupNo;

    @NotBlank(message = "데일리플랜 일시를 입력해주세요.")
    @Pattern(regexp = "(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])(2[0-3]|[01][0-9])([0-5][0-9])([0-5][0-9])", message = "데일리플랜 일시는 'yyyyMMddHHmmss' 형태로 입력해주세요.")
    @Schema(description = "데일리플랜 일시", example = "202301011500")
    private String dailyPlanDateTime;
}
