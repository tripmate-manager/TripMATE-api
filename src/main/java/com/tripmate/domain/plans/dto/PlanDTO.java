package com.tripmate.domain.plans.dto;

import com.tripmate.common.config.ValidationGroups;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "플랜 생성/수정 Request DTO")
public class PlanDTO {
    @Min(value = 1, message = "회원 번호는 필수이며, 양수만 입력 가능합니다.")
    @Schema(description = "회원 번호", example = "1")
    private int memberNo;

    @Hidden
    private int planNo;

    @NotBlank(message = "플랜 제목을 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Size(max = 20, groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "플랜 제목", example = "플랜 제목")
    private String planTitle;

    @NotBlank(message = "플랜 설명을 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Size(max = 100, groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "플랜 설명", pattern = "yyyy.MM.dd", example = "2023.01.01")
    private String planDescription;

    @NotBlank(message = "플랜 공개 여부를 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "^[YN]$", message = "플랜 공개 여부는 Y, N만 입력 가능합니다.", groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "플랜공개여부", example = "Y")
    private String publicYn;

    @NotBlank(message = "여행 시작 일자를 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "(19|20)\\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[01])"
            , message = "여행 시작 일자는 'yyyy.MM.dd' 형태로 입력가능합니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "여행 시작 일자", pattern = "yyyy.MM.dd", example = "2023.01.01")
    private String tripStartDate;

    @NotBlank(message = "여행 종료 일자를 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "(19|20)\\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[01])"
            , message = "여행 종료 일자는 'yyyy.MM.dd' 형태로 입력가능합니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "여행 종료 일자", pattern = "yyyy.MM.dd", example = "2023.01.01")
    private String tripEndDate;

    @NotEmpty(message = "여행지를 입력해주세요.")
    @Schema(description = "플랜 지역 리스트")
    private List<Integer> planAddressList;

    @Schema(description = "플랜 여행테마 속성 리스트")
    private List<Integer> planThemeList;

    @Schema(description = "플랜 해시태그 속성 리스트")
    private List<String> planHashtagList;
}
