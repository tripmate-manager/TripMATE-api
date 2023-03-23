package com.tripmate.domain.wishlist.service;

import com.tripmate.domain.wishlist.dto.PostDTO;
import com.tripmate.domain.wishlist.vo.PostVO;

import java.util.List;

public interface WishListService {
    String createPost(PostDTO postDTO);
    List<PostVO> searchWishList(String planNo);
}
