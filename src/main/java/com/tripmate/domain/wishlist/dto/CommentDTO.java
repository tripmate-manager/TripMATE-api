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
@Schema(description = "게시글 댓글 생성 Request DTO")
public class CommentDTO {
    @Hidden
    @Schema(description = "댓글 번호")
    private String commentNo;

    @NotBlank(message = "게시글 번호를 입력해주세요.")
    @Schema(description = "게시글 번호", example = "1")
    private String postNo;

    @NotBlank(message = "댓글 작성자 회원번호를 입력해주세요.")
    @Schema(description = "작성자 회원번호", example = "1")
    private String commenterNo;

    @NotBlank(message = "댓글 그룹 번호를 입력해주세요.")
    @Schema(description = "댓글 그룹 번호", example = "1")
    private String commentGroupNo;

    @Schema(description = "대댓글 그룹 번호", example = "1")
    private String commentGroupNo2;

    @NotBlank(message = "댓글 depth를 입력해주세요.", groups = ValidationGroups.NotBlankGroup.class)
    @Pattern(regexp = "^[123]$", message = "댓글 depth는 1, 2, 3만 입력 가능합니다.", groups = ValidationGroups.PatternCheckGroup.class)
    @Schema(description = "댓글 depth", example = "1")
    private String commentDepth;

    @NotBlank(message = "댓글 내용을 입력해주세요.")
    @Schema(description = "댓글 내용", example = "댓글 내용")
    private String commentText;
}
