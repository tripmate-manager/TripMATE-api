package com.tripmate.domain.members.dto;

import com.tripmate.common.config.ValidationGroups;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "회원가입 Request DTO")
public class MemberDTO {
    @Hidden
    private int memberNo;

    @NotBlank(message = "아이디를 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp="^[0-9a-zA-Z]{5,20}$"
            , message = "영문, 숫자로 이루어진 5자 ~ 20자의 아이디만 입력 가능합니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "아이디", example = "testId")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}"
            , message = "영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호만 입력 가능합니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "비밀번호", example = "password1!")
    private String memberPassword;

    @NotBlank(message = "이름을 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "^[가-힣]{2,20}|[a-zA-Z]{2,20}$", message = "영문이나 한글로 이루어진 2~20자의 이름만 입력 가능합니다.", groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "이름", example = "홍길동")
    private String memberName;

    @NotBlank(message = "닉네임을 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "^[가-힣0-9a-zA-Z~!@#$%^&*()_+|<>?:{}]{1,20}$"
            , message = "한영자, 숫자, 기호 입력 가능하며 1~20자의 닉네임만 입력 가능합니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "닉네임", example = "닉네임")
    private String nickName;

    @NotBlank(message = "이메일을 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Email(message = "이메일 형식에 맞지 않습니다.", groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "이메일", example = "test@test.com")
    private String email;

    @NotBlank(message = "성별코드를 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "^[12]0$", message = "성별코드는 10, 20만 입력 가능합니다.", groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "성별코드(10: 남성, 20: 여성)", example = "10")
    private String genderCode;

    @NotBlank(message = "생년월일을 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "^(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$", message="생년월일은 'yyyyMMdd' 형태로 입력가능합니다.", groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "생년월일", pattern = "yyyyMMdd", example = "19980101")
    private String birthDay;

    @Pattern(regexp = "^[1234]0$", message = "회원상태코드는 10, 20, 30, 40만 입력 가능합니다.")
    @Schema(description = "회원상태코드(10: 인증완료, 20: 인증대기, 30: 탈퇴, 40: 임시비밀번호발급회원", example = "10")
    private String memberStatusCode;

    @Hidden
    private int signInRequestCnt;
}

