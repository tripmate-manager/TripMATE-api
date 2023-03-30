package com.tripmate.domain.wishlist.dao.mapper;

import com.tripmate.domain.wishlist.dto.CommentDTO;
import com.tripmate.domain.wishlist.dto.DeleteCommentDTO;
import com.tripmate.domain.wishlist.dto.PostDTO;
import com.tripmate.domain.wishlist.vo.CommentVO;
import com.tripmate.domain.wishlist.vo.PostVO;

import java.util.List;

public interface WishListDAOMapper {
    int insertPostInfo(PostDTO postDTO);
    List<PostVO> searchWishList(String planNo);
    PostVO getPostInfo(String planNo);
    int insertComment(CommentDTO commentDTO);
    List<CommentVO> searchCommentList(String postNo);
    int getPlanMateCntWithPostNo(CommentDTO commentDTO);
    String getCommenterMemberNo(String commentNo);
    int updateCommentUseYn(DeleteCommentDTO deleteCommentDTO);
    int updatePost(PostDTO postDTO);
    int deletePost(String postNo);
}
