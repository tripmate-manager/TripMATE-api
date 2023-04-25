package com.tripmate.domain.plans.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "알림 목록 조회 Response VO")
public class NotificationVO {
    @Schema(description = "게시글 번호", example = "1")
    private int notificationNo;

    @Schema(description = "플랜 번호", example = "1")
    private int planNo;

    @Schema(description = "게시글 번호", example = "1")
    private int postNo;

    @Schema(description = "플랜 제목", example = "플랜 제목")
    private String planTitle;

    @Schema(description = "리더 이름", example = "리더 이름")
    private String leaderName;

    @Schema(description = "데일리플랜 게시글 번호 (일정 알림인 경우 입력한 게시글에 대한 알람을 등록합니다.)", example = "1")
    private int dailyPlanNo;

    @Schema(description = "게시글 제목", example = "게시글 제목")
    private String postTitle;

    @Schema(description = "알림타입코드(10: 여행일정알림, 20: 플랜리더변경알림, 30: 초대알림)", example = "10")
    private String notificationTypeCode;

    @Schema(description = "발신자 회원번호", example = "1")
    private int senderNo;

    @Schema(description = "발신자 이름", example = "발신자 이름")
    private String senderName;

    @Schema(description = "수신자 회원번호", example = "1")
    private int receiverNo;

    @Schema(description = "알림 확인 일시", example = "1")
    private String readDateTime;

    @Schema(description = "데일리플랜 알림 일시 (알림 수정인 경우 입력한 일시로 알림을 수정합니다.)", example = "2023-01-01 15:00:00.0")
    private String notificationDateTime;

    @Schema(description = "사용여부", example = "Y")
    private String useYn;
}
