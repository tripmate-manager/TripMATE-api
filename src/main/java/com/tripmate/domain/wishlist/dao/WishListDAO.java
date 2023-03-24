package com.tripmate.domain.wishlist.dao;

import com.tripmate.domain.wishlist.dao.mapper.WishListDAOMapper;
import com.tripmate.domain.wishlist.dto.PostDTO;
import com.tripmate.domain.wishlist.vo.PostVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListDAO {
    private final SqlSession sqlSession;

    @Autowired
    public WishListDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int insertPostInfo(PostDTO postDTO) {
        return sqlSession.getMapper(WishListDAOMapper.class).insertPostInfo(postDTO);
    }

    public List<PostVO> searchWishList(String planNo) {
        return sqlSession.getMapper(WishListDAOMapper.class).searchWishList(planNo);
    }

    public PostVO getPostInfo(String postNo) {
        return sqlSession.getMapper(WishListDAOMapper.class).getPostInfo(postNo);
    }
}
