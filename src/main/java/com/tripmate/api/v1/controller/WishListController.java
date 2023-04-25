package com.tripmate.api.v1.controller;

import com.tripmate.common.config.ValidationSequence;
import com.tripmate.domain.common.vo.ResponseWrapper;
import com.tripmate.domain.wishlist.dto.CommentDTO;
import com.tripmate.domain.wishlist.dto.DeleteCommentDTO;
import com.tripmate.domain.wishlist.dto.PostDTO;
import com.tripmate.domain.wishlist.service.WishListService;
import com.tripmate.domain.wishlist.vo.CommentVO;
import com.tripmate.domain.wishlist.vo.PostVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@Slf4j
@RestController
@Tag(name = "WishList", description = "위시리스트 API")
@RequestMapping("v1/wishlist")
@Validated
@RequiredArgsConstructor
public class WishListController {
    private final WishListService wishListService;

    @Operation(summary = "게시글 생성", description = "위시리스트 게시글을 생성합니다.")
    @PostMapping("/post")
    public ResponseWrapper<String> createPost(@Validated(ValidationSequence.class) @RequestBody PostDTO postDTO) {
        return ResponseWrapper.<String>builder()
                .data(Collections.singletonList(wishListService.createPost(postDTO)))
                .build();
    }

    @Operation(summary = "위시리스트 조회", description = "해당 플랜의 위시리스트 목록을 조회합니다.")
    @GetMapping("/{planNo}")
    public ResponseWrapper<PostVO> searchWishList(@PathVariable(value = "planNo") @Schema(description = "플랜번호", example = "1") String planNo) {
        return ResponseWrapper.<PostVO>builder()
                .data(wishListService.searchWishList(planNo))
                .build();
    }

    @Operation(summary = "게시글 정보 조회", description = "해당 플랜의 위시리스트 목록을 조회합니다.")
    @GetMapping("/post/{postNo}")
    public ResponseWrapper<PostVO> getPostInfo(@PathVariable(value = "postNo") @Schema(description = "게시글번호", example = "1") String postNo) {
        return ResponseWrapper.<PostVO>builder()
                .data(Collections.singletonList(wishListService.getPostInfo(postNo)))
                .build();
    }

    @Operation(summary = "댓글 생성", description = "위시리스트 댓글을 생성합니다.")
    @PostMapping("/comment")
    public ResponseWrapper<String> createComment(@Validated(ValidationSequence.class) @RequestBody CommentDTO commentDTO) {
        return ResponseWrapper.<String>builder()
                .data(Collections.singletonList(wishListService.createComment(commentDTO)))
                .build();
    }

    @Operation(summary = "댓글 조회", description = "해당 플랜의 댓글 목록을 조회합니다.")
    @GetMapping("/comment/{postNo}")
    public ResponseWrapper<CommentVO> searchCommentList(@PathVariable(value = "postNo") @Schema(description = "게시글번호", example = "1") String postNo) {
        return ResponseWrapper.<CommentVO>builder()
                .data(wishListService.searchCommentList(postNo))
                .build();
    }

    @Operation(summary = "댓글 삭제", description = "게시글의 댓글을 삭제합니다.")
    @PostMapping("/comment/{commentNo}")
    public ResponseWrapper<Boolean> deleteComment(@PathVariable(value = "commentNo") @Schema(description = "댓글번호", example = "1") String commentNo,
                                                  @Valid @RequestBody DeleteCommentDTO deleteCommentDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(wishListService.deleteComment(commentNo, deleteCommentDTO)))
                .build();
    }

    @Operation(summary = "게시글 수정", description = "위시리스트 게시글을 수정합니다.")
    @PutMapping("/post/{postNo}")
    public ResponseWrapper<Boolean> updatePost(@PathVariable(value = "postNo") @Schema(description = "게시글번호", example = "1") String postNo,
                                               @Validated(ValidationSequence.class) @RequestBody PostDTO postDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(wishListService.updatePost(postNo, postDTO)))
                .build();
    }

    @Operation(summary = "게시글 삭제", description = "위시리스트 게시글을 삭제합니다.")
    @DeleteMapping("/post/{postNo}")
    public ResponseWrapper<Boolean> deletePost(@PathVariable(value = "postNo") @Schema(description = "게시글번호", example = "1") String postNo) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(wishListService.deletePost(postNo)))
                .build();
    }
}
