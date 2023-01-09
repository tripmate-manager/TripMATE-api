package com.tripmate.domain.common.dao;

import com.tripmate.domain.common.dao.mapper.MemberDAOMapper;
import com.tripmate.domain.common.dto.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
    private final SqlSession sqlSession;

    @Autowired
    public MemberDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void insertMemberInfo(MemberDTO memberDTO) {
        sqlSession.getMapper(MemberDAOMapper.class).insertMemberInfo(memberDTO);
    }
}
