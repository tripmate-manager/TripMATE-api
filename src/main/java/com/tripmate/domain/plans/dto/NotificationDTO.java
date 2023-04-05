package com.tripmate.domain.plans.dto;

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
@Schema(description = "알림 DTO")
public class NotificationDTO {
    @NotBlank(message = "플랜 번호를 입력해주세요.")
    @Schema(description = "플랜 번호", example = "1")
    private String planNo;

    @Schema(description = "데일리플랜 게시글 번호 (일정 알림인 경우 입력한 게시글에 대한 알람을 등록합니다.)", example = "1")
    private String dailyPlanNo;

    @NotBlank(message = "알림타입코드를 입력해주세요.")
    @Pattern(regexp = "^[123]0$", message = "알림타입코드는 10, 20, 30만 입력 가능합니다.")
    @Schema(description = "알림타입코드(10: 여행일정알림, 20: 플랜리더변경알림, 30: 초대알림)", example = "10")
    private String notificationTypeCode;

    @NotBlank(message = "발신자 회원번호를 입력해주세요.")
    @Schema(description = "발신자 회원번호", example = "1")
    private String senderNo;

    @Schema(description = "수신자 회원번호", example = "1")
    private String receiverNo;

    @Pattern(regexp = "(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])(2[0-3]|[01][0-9])([0-5][0-9])([0-5][0-9])", message = "데일리플랜 알림 일시는 'yyyyMMddHHmmss' 형태로 입력해주세요.")
    @Schema(description = "데일리플랜 알림 일시 (알림 수정인 경우 입력한 일시로 알림을 수정합니다.)", example = "20230101150000")
    private String notificationDateTime;
}
