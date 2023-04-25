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
    @Schema(description = "플랜 번호", example = "1")
    private String planNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    @Schema(description = "회원 번호", example = "1")
    private String memberNo;

    @NotBlank(message = "위시리스트 게시글 타입을 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "^[1239]0$", message = "게시글 타입코드는 10, 20, 30, 90만 입력 가능합니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "위시리스트 게시글 타입코드(10: 숙소, 20: 관광지, 30: 식당, 90: 기타)", example = "10")
    private String postTypeCode;

    @NotBlank(message = "플랜 본문 내용을 입력해주세요.")
    @Schema(description = "플랜 본문", example = "플랜 본문 내용")
    private String postContents;

    @Schema(description = "장소 위치(주소)", example = "서울 강서구 마곡동로 36")
    private String spotAddress;

    @Schema(description = "장소 위도", example = "37.558063687544404")
    private double spotLatitude;

    @Schema(description = "장소 경도", example = "126.83351057028376")
    private double spotLongitude;

    @Schema(description = "정보 URL", example = "https://www.google.com/")
    private String informationUrl;

    @Schema(description = "이용 금액", example = "이용 금액")
    private String amount;

    @Schema(description = "이용 시간", example = "이용 시간")
    private String businessHours;

    @Schema(description = "대표 메뉴", example = "대표 메뉴")
    private String mainMenu;

    @Schema(description = "게시글 제목", example = "게시글 제목")
    private String postTitle;

    @Schema(description = "특이사항", example = "특이사항 내용")
    private String remark;

    @Schema(description = "등록시간", example = "2023-01-01 15:00:00")
    private String registrationDateTime;
}
