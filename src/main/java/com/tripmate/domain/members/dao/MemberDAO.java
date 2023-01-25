package com.tripmate.domain.members.dao;

import com.tripmate.domain.members.dto.DuplicationCheckDTO;
import com.tripmate.domain.members.dao.mapper.MemberDAOMapper;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.members.dto.MemberMailDTO;
import com.tripmate.domain.members.dto.SignInDTO;
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

    public void updateSignUpEmailConfirm(MemberMailDTO memberMailDTO) {
        sqlSession.getMapper(MemberDAOMapper.class).updateSignUpEmailConfirm(memberMailDTO);
    }

    public MemberDTO selectSignInMemberInfo(SignInDTO signInDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).selectSignInMemberInfo(signInDTO);
    }

    public void updateSignInRequestCnt(SignInDTO signInDTO) {
        sqlSession.getMapper(MemberDAOMapper.class).updateSignInRequestCnt(signInDTO);
    }
    public MemberDTO selectSignInRequestCnt(SignInDTO signInDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).selectSignInRequestCnt(signInDTO);
    }
}
