package com.tripmate.domain.member.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "로그인 Request DTO")
public class SignInDTO {
    @NonNull
    @Pattern(regexp = "^[0-9a-zA-Z]{5,20}$",
            message = "영문, 숫자로 이루어진 5자 ~ 20자의 아이디만 입력 가능합니다.")
    @Schema(description = "아이디", example = "testid")
    private String memberId;

    @NonNull
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호만 입력 가능합니다.")
    @Schema(description = "비밀번호")
    private String memberPassword;
}