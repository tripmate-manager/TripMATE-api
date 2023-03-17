package com.tripmate.domain.wishlist.dao.mapper;

import com.tripmate.domain.wishlist.dto.PostDTO;
import com.tripmate.domain.wishlist.vo.PostVO;

import java.util.List;

public interface WishListDAOMapper {
    int insertPostInfo(PostDTO postDTO);
    List<PostVO> searchWishList(String planNo);
}
