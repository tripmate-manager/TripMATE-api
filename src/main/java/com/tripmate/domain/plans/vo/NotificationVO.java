package com.tripmate.domain.plans.vo;

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
@Schema(description = "알림 목록 조회 Response VO")
public class NotificationVO {
    private int notificationNo;

    @Min(value = 1, message = "플랜 번호는 필수이며, 양수만 입력 가능합니다.")
    private int planNo;

    private String planTitle;

    private String leaderName;

    private int postNo;

    private String postTitle;

    @NotBlank(message = "알림타입코드를 입력해주세요.")
    @Pattern(regexp = "^[123]0$", message = "알림타입코드는 10, 20, 30만 입력 가능합니다.")
    private String notificationTypeCode;

    @Min(value = 1, message = "발신자 회원번호는 필수이며, 양수만 입력 가능합니다.")
    private int senderNo;

    private String senderName;

    @Min(value = 1, message = "수신자 회원번호는 필수이며, 양수만 입력 가능합니다.")
    private int receiverNo;

    private String readDateTime;

    private String notificationDateTime;

    private String useYn;
}
