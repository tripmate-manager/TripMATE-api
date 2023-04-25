package com.tripmate.domain.checklist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "체크리스트 조회 Response VO")
public class CheckListVO {
    @Schema(description = "플랜 번호", example = "1")
    private String planNo;

    @Schema(description = "체크리스트 항목 번호", example = "1")
    private String materialNo;

    @Schema(description = "체크리스트 타입코드(10: 개인, 20: 공용", example = "10")
    private String checkListTypeCode;

    @Schema(description = "체크리스트 항목명", example = "체크리스트 항목명")
    private String materialName;

    @Schema(description = "체크리스트 체크 여부", example = "Y")
    private String checkYn;

    @Schema(description = "체크 회원 번호", example = "1")
    private String checkMemberNo;

    @Schema(description = "플랜 리더 번호", example = "1")
    private String planLeaderNo;

    @Schema(description = "체크 회원 닉네임", example = "닉네임")
    private String checkMemberNickName;
}
