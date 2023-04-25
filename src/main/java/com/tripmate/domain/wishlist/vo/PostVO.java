package com.tripmate.domain.wishlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "위시리스트 게시글 조회 Response VO")
public class PostVO {
    @Schema(description = "게시글 번호", example = "1")
    private String postNo;

    @Schema(description = "플랜 번호", example = "1")
    private String planNo;

    @Schema(description = "게시글 타입 코드(10: 숙소, 20: 관광지, 30: 식당, 90: 기타)", example = "10")
    private String postTypeCode;

    @Schema(description = "게시글 본문", example = "게시글 본문")
    private String postContents;

    @Schema(description = "게시글 제목", example = "게시글 제목")
    private String postTitle;

    @Schema(description = "주소", example = "서울 강서구 마곡동로 36")
    private String spotAddress;

    @Schema(description = "정보 URL", example = "https://www.google.com/")
    private String informationUrl;

    @Schema(description = "이용 금액", example = "이용 금액")
    private String amount;

    @Schema(description = "영업 시간", example = "영업 시간")
    private String businessHours;

    @Schema(description = "대표 메뉴", example = "대표 메뉴")
    private String mainMenu;

    @Schema(description = "특이 사항", example = "특이 사항")
    private String remark;

    @Schema(description = "매핑여부(데일리플랜 북마크 추가 여부)", example = "Y")
    private String mappingYn;

    @Schema(description = "게시글 본문", example = "2023-01-01 15:00:00.0")
    private String registrationNo;

    @Schema(description = "게시글 작성 일시", example = "게시글 본문")
    private String registrationDateTime;

    @Schema(description = "댓글 개수", example = "1")
    private int commentCnt;
}
