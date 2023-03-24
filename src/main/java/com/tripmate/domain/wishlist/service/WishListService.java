package com.tripmate.domain.wishlist.service;

import com.tripmate.domain.wishlist.dto.CommentDTO;
import com.tripmate.domain.wishlist.dto.DeleteCommentDTO;
import com.tripmate.domain.wishlist.dto.PostDTO;
import com.tripmate.domain.wishlist.vo.CommentVO;
import com.tripmate.domain.wishlist.vo.PostVO;

import java.util.List;

public interface WishListService {
    String createPost(PostDTO postDTO);
    List<PostVO> searchWishList(String planNo);
    PostVO getPostInfo(String postNo);
    String createComment(CommentDTO commentDTO);
    List<CommentVO> searchCommentList(String postNo);
    Boolean deleteComment(String commentNo, DeleteCommentDTO deleteCommentDTO);
}
