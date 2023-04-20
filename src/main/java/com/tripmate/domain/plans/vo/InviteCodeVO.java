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
    @Schema(description = "초대 코드 번호", example = "1")
    private int inviteCodeNo;

    @Schema(description = "플랜 번호", example = "1")
    private String planNo;

    @Schema(description = "초대 타입 코드(10: 회원, 20: 비회원)", example = "10")
    private String inviteTypeCode;

    @Schema(description = "초대 코드", example = "ABCD12")
    private String inviteCode;

    @Schema(description = "초대 코드 만료 일시", example = "2023-01-01 15:00:00.0")
    private String inviteCodeExpireDateTime;
}
