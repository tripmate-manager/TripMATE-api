package com.tripmate.domain.checklist.dto;

import com.tripmate.common.config.ValidationGroups;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "체크리스트 항목 삭제 Request DTO")
public class DeleteCheckListDTO {
    @NotBlank(message = "플랜 번호를 입력해주세요.")
    @Schema(description = "플랜 번호", example = "1")
    private String planNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    @Schema(description = "회원 번호", example = "1")
    private String memberNo;

    @NotBlank(message = "체크리스트 항목 타입을 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "^[12]0$", message = "체크리스트 타입코드는 10, 20만 입력 가능합니다.", groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "체크리스트 타입코드(10: 개인, 20: 공용", example = "10")
    private String checkListTypeCode;

    @NotEmpty(message = "체크리스트 항목 번호를 입력해주세요.")
    @Schema(description = "체크리스트 항목 리스트")
    private List<String> materialNoList;
}