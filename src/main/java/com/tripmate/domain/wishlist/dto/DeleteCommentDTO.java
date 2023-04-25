package com.tripmate.domain.wishlist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "게시글 댓글 삭제 Request DTO")
public class DeleteCommentDTO {
    @NotBlank(message = "댓글 번호를 입력해주세요.")
    @Schema(description = "댓글 번호", example = "1")
    private String commentNo;

    @NotBlank(message = "댓글 작성자의 회원번호를 입력해주세요.")
    @Schema(description = "작성자 회원번호", example = "1")
    private String commenterNo;
}
