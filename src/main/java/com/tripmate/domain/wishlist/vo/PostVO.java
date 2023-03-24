package com.tripmate.domain.wishlist.vo;

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
@Schema(description = "위시리스트 게시글 조회 Response VO")
public class PostVO {
    @NotBlank
    private String postNo;

    @NotBlank(message = "플랜 번호를 입력해주세요.")
    private String planNo;

    @NotBlank(message = "위시리스트 게시글 타입을 입력해주세요.")
    @Pattern(regexp = "^[1239]0$", message = "게시글 타입코드는 10, 20, 30, 40만 입력 가능합니다.")
    private String postTypeCode;

    @NotBlank
    private String postContents;

    @NotBlank
    private String postTitle;

    private String spotAddress;

    private String informationUrl;

    private String amount;

    private String businessHours;

    private String mainMenu;

    private String remark;

    private String mappingYn;

    @NotBlank
    private String registrationDateTime;

    @NotBlank
    private int commentCnt;
}
