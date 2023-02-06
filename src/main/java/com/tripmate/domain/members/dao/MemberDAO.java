package com.tripmate.domain.members.dao;

import com.tripmate.domain.members.dao.mapper.MemberDAOMapper;
import com.tripmate.domain.members.dto.ChangePasswordDTO;
import com.tripmate.domain.members.dto.DuplicationCheckDTO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.members.dto.MemberMailDTO;
import com.tripmate.domain.members.dto.MypageInfoDTO;
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

    public void updateSignInRequestCntAndStatusCode(SignInDTO signInDTO) {
        sqlSession.getMapper(MemberDAOMapper.class).updateSignInReqCntAndMbrStatusCd(signInDTO);
    }

    public MemberDTO selectSignInRequestCnt(SignInDTO signInDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).selectSignInRequestCnt(signInDTO);
    }

    public String selectMemberIdWithNameAndEmail(MemberDTO memberDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).selectMbrIdWithMbrNmAndEmail(memberDTO);
    }

    public void updateMemberPasswordAndStatusCode(MemberDTO memberDTO) {
        sqlSession.getMapper(MemberDAOMapper.class).updateMbrPwdAndStatusCd(memberDTO);
    }

    public MemberDTO selectMemberNoAndStatusCode(MemberMailDTO memberMailDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).selectMbrNoAndStatusCd(memberMailDTO);
    }

    public int selectAuthEmailCnt(MemberMailDTO memberMailDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).selectAuthEmailCnt(memberMailDTO);
    }

    public void updateEmailInfo(MemberMailDTO memberMailDTO) {
        sqlSession.getMapper(MemberDAOMapper.class).updateEmailInfo(memberMailDTO);
    }

    public void updateMemberPassword(ChangePasswordDTO changePasswordDTO) {
        sqlSession.getMapper(MemberDAOMapper.class).updateMemberPassword(changePasswordDTO);
    }

    public int updateWithdrawMemberInfo(SignInDTO signInDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).updateWithdrawMemberInfo(signInDTO);
    }
}
