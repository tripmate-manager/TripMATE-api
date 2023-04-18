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
    private String planNo;

    private String materialNo;

    private String checkListTypeCode;

    private String materialName;

    private String checkYn;

    private String checkMemberNo;

    private String planLeaderNo;
}
