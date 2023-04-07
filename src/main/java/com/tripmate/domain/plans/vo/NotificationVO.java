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

    private int planNo;

    private int postNo;

    private String planTitle;

    private String leaderName;

    private int dailyPlanNo;

    private String postTitle;

    private String notificationTypeCode;

    private int senderNo;

    private String senderName;

    private int receiverNo;

    private String readDateTime;

    private String notificationDateTime;

    private String useYn;
}
