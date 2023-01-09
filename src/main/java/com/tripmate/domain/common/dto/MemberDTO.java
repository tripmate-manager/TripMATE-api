package com.tripmate.domain.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "회원가입 Request DTO")
public class MemberDTO {
    @NonNull
    @Schema(description = "아이디", example = "testid")
    private String memberId;

    @NonNull
    @Schema(description = "비밀번호")
    private String memberPassword;

    @NonNull
    @Schema(description = "닉네임", example = "닉네임")
    private String nickName;

    @NonNull
    @Schema(description = "이메일", example = "test@test.com")
    private String email;

    @NonNull
    @Schema(description = "성별코드", example = "10")
    private String genderCode;

    @NonNull
    @Schema(description = "생일", example = "980101")
    private String birthDay;
}

