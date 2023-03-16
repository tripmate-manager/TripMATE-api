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
@Schema(description = "초대 코드 정보 조회 Response VO")
public class InviteCodeVO {
    private int inviteCodeNo;

    private String planNo;

    private String inviteTypeCode;

    private String inviteCode;

    private String inviteCodeExpireDateTime;
}
