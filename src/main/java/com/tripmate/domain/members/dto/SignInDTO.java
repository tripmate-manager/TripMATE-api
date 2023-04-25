package com.tripmate.domain.members.dto;

import com.tripmate.common.config.ValidationGroups;
import io.swagger.v3.oas.annotations.Hidden;
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
@Schema(description = "로그인 Request DTO")
public class SignInDTO {
    @Hidden
    private int memberNo;

    @NotBlank(message = "아이디를 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "^[0-9a-zA-Z]{5,20}$"
            , message = "영문, 숫자로 이루어진 5자 ~ 20자의 아이디만 입력 가능합니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "회원 아이디", example = "testId")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Schema(description = "비밀번호", example = "password1!")
    private String memberPassword;

    @Hidden
    private int signInRequestCnt;

    @Pattern(regexp = "^[1234]0$", message = "회원상태코드는 10, 20, 30, 40만 입력 가능합니다.")
    @Schema(description = "회원상태코드(10: 인증완료, 20: 인증대기, 30: 탈퇴, 40: 임시비밀번호발급회원", example = "10")
    private String memberStatusCode;
}
