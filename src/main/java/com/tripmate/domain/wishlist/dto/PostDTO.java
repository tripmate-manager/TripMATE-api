package com.tripmate.domain.wishlist.dto;

import com.tripmate.common.config.ValidationGroups;
import io.swagger.v3.oas.annotations.Hidden;
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
@Schema(description = "위시리스트 게시글 생성 Request DTO")
public class PostDTO {
    @Hidden
    private String postNo;

    @NotBlank(message = "플랜 번호를 입력해주세요.")
    private String planNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    private String memberNo;

    @NotBlank(message = "위시리스트 게시글 타입을 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "^[1239]0$", message = "게시글 타입코드는 10, 20, 30, 90만 입력 가능합니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "위시리스트 게시글 타입코드(10: 숙소, 20: 관광지, 30: 식당, 90: 기타", example = "10")
    private String postTypeCode;

    @NotBlank(message = "플랜 본문 내용을 입력해주세요.")
    private String postContents;

    @Schema(description = "장소 위치(주소)")
    private String spotAddress;

    @Schema(description = "장소 위도")
    private double spotLatitude;

    @Schema(description = "장소 경도")
    private double spotLongitude;

    @Schema(description = "정보 URL")
    private String informationUrl;

    @Schema(description = "요금")
    private String amount;

    @Schema(description = "정보 URL")
    private String businessHours;

    @Schema(description = "대표 메뉴")
    private String mainMenu;

    @Schema(description = "게시글 제목")
    private String postTitle;

    @Schema(description = "특이사항")
    private String remark;

    @Schema(description = "등록시간")
    private String registrationDateTime;
}
