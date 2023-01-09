package com.tripmate.domain.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "공통코드 상세정보 조회 Response VO")
public class CodeVO {
    @NonNull
    @Schema(description = "공통코드", example = "MB001")
    private String commonCode;
    @NonNull
    @Schema(description = "공통코드 상세코드", example = "10")
    private String commonDetailCode;
    @Schema(description = "공통코드 상세코드명", example = "일반회원가입")
    private String commonDetailName;
    @Schema(description = "그룹코드1")
    private String groupCode1;
    @Schema(description = "그룹코드2")
    private String groupCode2;
    @Schema(description = "그룹코드3")
    private String groupCode3;
    @Schema(description = "정렬순서", example = "100")
    private long sortSeq;
    @Schema(description = "사용여부", example = "Y")
    private String useYn;
}