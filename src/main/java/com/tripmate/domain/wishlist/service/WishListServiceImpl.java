package com.tripmate.domain.wishlist.service;

import com.tripmate.common.exception.GuideMessageException;
import com.tripmate.common.exception.WrongParameterException;
import com.tripmate.domain.wishlist.dao.WishListDAO;
import com.tripmate.domain.wishlist.dto.CommentDTO;
import com.tripmate.domain.wishlist.dto.DeleteCommentDTO;
import com.tripmate.domain.wishlist.dto.PostDTO;
import com.tripmate.domain.wishlist.vo.CommentVO;
import com.tripmate.domain.wishlist.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WishListServiceImpl implements WishListService {
    private final WishListDAO wishListDAO;

    @Autowired
    public WishListServiceImpl(WishListDAO wishListDAO) {
        this.wishListDAO = wishListDAO;
    }

    @Override
    public String createPost(PostDTO postDTO) {
        if (wishListDAO.insertPostInfo(postDTO) <= 0) {
            throw new GuideMessageException("게시글 생성 중 오류가 발생하였습니다.");
        }

        return postDTO.getPostNo();
    }

    @Override
    public List<PostVO> searchWishList(String planNo) {
        return wishListDAO.searchWishList(planNo);
    }

    @Override
    public PostVO getPostInfo(String postNo) {
        return wishListDAO.getPostInfo(postNo);
    }

    @Override
    public String createComment(CommentDTO commentDTO) {
        if (wishListDAO.getPlanMateCntWithPostNo(commentDTO) == 0) {
            throw new GuideMessageException("댓글 작성자 정보가 유효하지 않습니다.");
        }

        if (wishListDAO.insertComment(commentDTO) <= 0) {
            throw new GuideMessageException("댓글 생성 중 오류가 발생하였습니다.");
        }

        return commentDTO.getCommentNo();
    }

    @Override
    public List<CommentVO> searchCommentList(String postNo) {
        return wishListDAO.searchCommentList(postNo);
    }

    @Override
    public boolean deleteComment(String commentNo, DeleteCommentDTO deleteCommentDTO) {
        if (!commentNo.equals(deleteCommentDTO.getCommentNo())) {
            throw new WrongParameterException("댓글 삭제 처리 중 오류가 발생하였습니다.");
        }

        if (!deleteCommentDTO.getCommenterNo().equals(wishListDAO.getCommenterMemberNo(commentNo))) {
            throw new GuideMessageException("댓글 삭제 권한이 없는 회원입니다.");
        }

        return wishListDAO.updateCommentUseYn(deleteCommentDTO) == 1;
    }
}
