package com.tripmate.domain.members.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "메일 DTO")
public class MemberMailDTO {
    @NonNull
    @NotBlank(message = "메일수신자 주소를 입력하세요")
    @Schema(description = "메일주소", example = "test@test.com")
    @Email
    private String to;

    @Schema(description = "메일 제목")
    private String subject;

    @Schema(description = "메일 본문")
    private String message;


    private String memberNo;

    @Pattern(regexp="^[0-9a-zA-Z]{5,20}$",
            message = "영문, 숫자로 이루어진 5자 ~ 20자의 아이디만 입력 가능합니다.")
    private String memberId;

    @Hidden
    @Size(max = 100, message = "100자 이하인 값만 입력 가능합니다.")
    private String key;

    @Pattern(regexp = "^[12]0$", message = "메일인증타입코드는 10, 20만 입력 가능합니다.")
    private String mailTypeCode;
}
