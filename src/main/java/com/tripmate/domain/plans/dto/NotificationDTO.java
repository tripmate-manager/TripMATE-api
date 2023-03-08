package com.tripmate.domain.plans.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "알림 DTO")
public class NotificationDTO {
    @Min(value = 1, message = "플랜 번호는 필수이며, 양수만 입력 가능합니다.")
    @Schema(description = "플랜 번호")
    private int planNo;

    @Schema(description = "데일리플랜 게시글 번호")
    private int postNo;

    @NotBlank(message = "알림타입코드를 입력해주세요.")
    @Pattern(regexp = "^[123]0$", message = "알림타입코드는 10, 20, 30만 입력 가능합니다.")
    @Schema(description = "알림타입코드(10: 여행일정알림, 20: 플랜리더변경알림, 30: 초대알림)")
    private String notificationTypeCode;

    @Min(value = 1, message = "발신자 회원번호는 필수이며, 양수만 입력 가능합니다.")
    @Schema(description = "발신자 회원번호")
    private int senderNo;

    @Min(value = 1, message = "수신자 회원번호는 필수이며, 양수만 입력 가능합니다.")
    @Schema(description = "수신자 회원번호")
    private int receiverNo;

    @Hidden
    private String readDateTime;

    @Hidden
    private String notificationDateTime;
}
