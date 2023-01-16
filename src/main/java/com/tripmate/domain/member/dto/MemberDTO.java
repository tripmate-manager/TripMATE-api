package com.tripmate.domain.member.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "회원가입 Request DTO")
public class MemberDTO {
    @Hidden
    private int memberNo;

    @NonNull
    @Pattern(regexp="^[0-9a-zA-Z]{5,20}$",
            message = "영문, 숫자로 이루어진 5자 ~ 20자의 아이디만 입력 가능합니다.")
    @Schema(description = "아이디", example = "testid")
    private String memberId;

    @NonNull
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호만 입력 가능합니다.")
    @Schema(description = "비밀번호")
    private String memberPassword;

    @NonNull
    @Pattern(regexp = "^[가-힣]{2,20}|[a-zA-Z]{2,20}$", message = "영문이나 한글로 이루어진 2~20자의 이름만 입력 가능합니다.")
    @Schema(description = "이름", example = "홍길동")
    private String memberName;

    @NonNull
    @Schema(description = "닉네임", example = "닉네임")
    @Size(min = 1, max = 20)
    private String nickName;

    @NonNull
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @Schema(description = "이메일", example = "test@test.com")
    private String email;

    @NonNull
    @Pattern(regexp = "^[12]0$", message = "성별코드는 10, 20만 입력 가능랍니다.")
    @Schema(description = "성별코드(10: 남성, 20: 여성)", example = "10")
    private String genderCode;

    @NonNull
    @Pattern(regexp = "^\\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$", message="생년월일은 'yyyyMMdd' 형태로 입력가능합니다.")
    @Schema(description = "생년월일", pattern = "yyyyMMdd", example = "19980101")
    private String birthDay;

    @Pattern(regexp = "^[123]0$", message = "성별코드는 10, 20, 30만 입력 가능합니다.")
    @Schema(description = "회원상태코드(10: 인증완료, 20: 인증대기, 30: 탈퇴", example = "10")
    private String memberStatusCode;
}

