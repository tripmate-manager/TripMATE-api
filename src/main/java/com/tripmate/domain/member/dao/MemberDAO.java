package com.tripmate.domain.member.dao;

import com.tripmate.domain.member.dto.DuplicationCheckDTO;
import com.tripmate.domain.member.dao.mapper.MemberDAOMapper;
import com.tripmate.domain.member.dto.MemberDTO;
import com.tripmate.domain.member.dto.MemberMailDTO;
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

    public int insertMemberInfo(MemberDTO memberDTO) {
        sqlSession.getMapper(MemberDAOMapper.class).insertMemberInfo(memberDTO);
        return memberDTO.getMemberNo();
    }

    public int selectDuplicationCnt(DuplicationCheckDTO duplicationCheckDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).selectDuplicationCnt(duplicationCheckDTO);
    }

    public void insertEmailInfo(MemberMailDTO memberMailDTO) {
        sqlSession.getMapper(MemberDAOMapper.class).insertEmailInfo(memberMailDTO);
    }

    public int updateSignUpEmailConfirm(MemberMailDTO memberMailDTO) {
        sqlSession.getMapper(MemberDAOMapper.class).updateSignUpEmailConfirm(memberMailDTO);
        return memberMailDTO.getMemberNo();
    }
}
