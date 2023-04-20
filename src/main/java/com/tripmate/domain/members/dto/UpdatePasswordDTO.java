package com.tripmate.domain.members.dto;

import com.tripmate.common.config.ValidationGroups;
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
public class UpdatePasswordDTO {

    private int memberNo;

    @NotBlank(message = "비밀번호를 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}"
            , message = "영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호만 입력 가능합니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "비밀번호")
    private String memberPassword;

    @NotBlank(message = "새 비밀번호를 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}"
            , message = "영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호만 입력 가능합니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "새 비밀번호")
    private String newMemberPassword;
}
