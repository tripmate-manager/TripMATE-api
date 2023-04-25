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
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "메일 DTO")
public class MemberMailDTO {

    @NotBlank(message = "메일수신자 주소를 입력하세요", groups = ValidationGroups.NotBlankGroup.class)
    @Email(groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "메일주소", example = "test@test.com")
    private String to;

    @Schema(description = "메일 제목", example = "메일 제목")
    private String subject;

    @Schema(description = "메일 본문", example = "메일 본문 내용")
    private String message;

    @Schema(description = "회원 번호", example = "1")
    private int memberNo;

    @Pattern(regexp="^[0-9a-zA-Z]{5,20}$",
            message = "영문, 숫자로 이루어진 5자 ~ 20자의 아이디만 입력 가능합니다.")
    @Schema(description = "회원 아이디", example = "testId")
    private String memberId;

    @Hidden
    private String key;

    @Pattern(regexp = "^[123]0$", message = "메일인증타입코드는 10, 20, 30만 입력 가능합니다. (10: 회원가입 인증, 20: 이메일 변경, 30: 임시비밀번호 발급)")
    @Schema(description = "메일인증타입코드", example = "10")
    private String mailTypeCode;
}
