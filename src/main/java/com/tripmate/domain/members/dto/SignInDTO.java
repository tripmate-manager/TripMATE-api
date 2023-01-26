package com.tripmate.domain.members.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "로그인 Request DTO")
public class SignInDTO {
    @Hidden
    private int memberNo;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[0-9a-zA-Z]{5,20}$",
            message = "영문, 숫자로 이루어진 5자 ~ 20자의 아이디만 입력 가능합니다.")
    @Schema(description = "아이디", example = "회원ID")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Schema(description = "비밀번호")
    private String memberPassword;

    @Hidden
    private int signInRequestCnt;
}
