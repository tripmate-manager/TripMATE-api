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
@Schema(description = "게시글 댓글 조회 Response VO")
public class CommentVO {
    @Schema(description = "댓글 번호", example = "1")
    private String commentNo;

    @Schema(description = "게시글 번호", example = "1")
    private String postNo;

    @Schema(description = "작성자 번호", example = "1")
    private String commenterNo;

    @Schema(description = "작성자 닉네임", example = "닉네임")
    private String nickName;

    @Schema(description = "댓글 그룹 번호", example = "1")
    private String commentGroupNo;

    @Schema(description = "대댓글 그룹 번호", example = "1")
    private String commentGroupNo2;

    @Schema(description = "댓글 depth", example = "1")
    private String commentDepth;

    @Schema(description = "댓글 내용", example = "댓글 내용")
    private String commentText;

    @Schema(description = "작성 일시", example = "2023-01-01 15:00:00.0")
    private String registrationDateTime;

    @Schema(description = "사용 여부", example = "Y")
    private String useYn;
}
