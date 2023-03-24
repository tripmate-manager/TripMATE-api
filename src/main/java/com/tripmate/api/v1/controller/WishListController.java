package com.tripmate.api.v1.controller;

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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@Slf4j
@RestController
@Tag(name = "위시리스트 API", description = "WishList API")
@RequestMapping("v1/wishlist")
@Validated
public class WishListController {
    private final WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @Operation(summary = "게시글 생성", description = "위시리스트 게시글을 생성합니다.")
    @PostMapping("/create-post")
    public ResponseWrapper<String> createPost(@Valid @RequestBody PostDTO postDTO) {
        return ResponseWrapper.<String>builder()
                .data(Collections.singletonList(wishListService.createPost(postDTO)))
                .build();
    }

    @Operation(summary = "위시리스트 조회", description = "해당 플랜의 위시리스트 목록을 조회합니다.")
    @GetMapping("/{planNo}")
    public ResponseWrapper<PostVO> searchWishList(@PathVariable(value = "planNo") @Schema(example = "플랜번호") String planNo) {
        return ResponseWrapper.<PostVO>builder()
                .data(wishListService.searchWishList(planNo))
                .build();
    }

    @Operation(summary = "게시글 정보 조회", description = "해당 플랜의 위시리스트 목록을 조회합니다.")
    @GetMapping("/post/{postNo}")
    public ResponseWrapper<PostVO> getPostInfo(@PathVariable(value = "postNo") @Schema(example = "게시글번호") String postNo) {
        return ResponseWrapper.<PostVO>builder()
                .data(Collections.singletonList(wishListService.getPostInfo(postNo)))
                .build();
    }

    @Operation(summary = "댓글 생성", description = "위시리스트 댓글을 생성합니다.")
    @PostMapping("/comment")
    public ResponseWrapper<String> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        return ResponseWrapper.<String>builder()
                .data(Collections.singletonList(wishListService.createComment(commentDTO)))
                .build();
    }

    @Operation(summary = "댓글 조회", description = "해당 플랜의 댓글 목록을 조회합니다.")
    @GetMapping("/comment/{postNo}")
    public ResponseWrapper<CommentVO> searchCommentList(@PathVariable(value = "postNo") @Schema(example = "게시글번호") String postNo) {
        return ResponseWrapper.<CommentVO>builder()
                .data(wishListService.searchCommentList(postNo))
                .build();
    }

    @Operation(summary = "댓글 삭제", description = "게시글의 댓글을 삭제합니다.")
    @PostMapping("/comment/{commentNo}")
    public ResponseWrapper<Boolean> deleteComment(@PathVariable(value = "commentNo") @Schema(example = "댓글번호") String commentNo,
                                                  @RequestBody DeleteCommentDTO deleteCommentDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(wishListService.deleteComment(commentNo, deleteCommentDTO)))
                .build();
    }
}
