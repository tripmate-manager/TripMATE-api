package com.tripmate.domain.wishlist.service;

import com.tripmate.common.exception.GuideMessageException;
import com.tripmate.domain.wishlist.dao.WishListDAO;
import com.tripmate.domain.wishlist.dto.PostDTO;
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
        if (wishListDAO.insertPostInfo(postDTO) <= 0 ) {
            throw new GuideMessageException("게시글 생성 중 오류가 발생하였습니다.");
        }

        return postDTO.getPostNo();
    }

    @Override
    public List<PostVO> searchWishList(String planNo) {
        return wishListDAO.searchWishList(planNo);
    }
}
