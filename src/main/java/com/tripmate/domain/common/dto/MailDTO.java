package com.tripmate.domain.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "메일 DTO")
public class MailDTO {
    @NonNull
    @NotBlank(message = "메일수신자 주소를 입력하세요")
    @Schema(description = "메일주소", example = "test@test.com")
    private String email;

    @NonNull
    @NotBlank(message = "메일 제목을 입력하세요")
    @Schema(description = "메일 제목")
    private String subject;

    @NonNull
    @NotBlank(message = "본문을 입력하세요")
    @Schema(description = "메일 본문")
    private String message;
}
