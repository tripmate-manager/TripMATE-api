package com.tripmate.domain.test.dao;

import com.tripmate.domain.test.dao.mapper.TestDAOMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {
    private final SqlSession sqlSession;

    @Autowired
    public TestDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public String test() {
        TestDAOMapper mapper = sqlSession.getMapper(TestDAOMapper.class);
        return mapper.test();
    }
}
