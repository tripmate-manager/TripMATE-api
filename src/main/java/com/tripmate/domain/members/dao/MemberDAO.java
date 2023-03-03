package com.tripmate.domain.members.dao;

import com.tripmate.domain.members.dao.mapper.MemberDAOMapper;
import com.tripmate.domain.members.dto.UpdatePasswordDTO;
import com.tripmate.domain.members.dto.DuplicationCheckDTO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.members.dto.MemberMailDTO;
import com.tripmate.domain.members.dto.MypageDTO;
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

    public int getDuplicationCnt(DuplicationCheckDTO duplicationCheckDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).getDuplicationCnt(duplicationCheckDTO);
    }

    public void insertEmailInfo(MemberMailDTO memberMailDTO) {
        sqlSession.getMapper(MemberDAOMapper.class).insertEmailInfo(memberMailDTO);
    }

    public int updateSignUpEmailConfirm(MemberMailDTO memberMailDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).updateSignUpEmailConfirm(memberMailDTO);
    }

    public MemberDTO getSignInMemberInfo(SignInDTO signInDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).getSignInMemberInfo(signInDTO);
    }

    public void updateSignInRequestCntAndStatusCode(SignInDTO signInDTO) {
        sqlSession.getMapper(MemberDAOMapper.class).updateSignInReqestCntAndMemberStatusCd(signInDTO);
    }

    public MemberDTO getSignInRequestCnt(SignInDTO signInDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).getSignInRequestCnt(signInDTO);
    }

    public String getMemberIdWithNameAndEmail(MemberDTO memberDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).getMemberIdWithMemberNameAndEmail(memberDTO);
    }

    public void updateMemberPasswordAndStatusCode(MemberDTO memberDTO) {
        sqlSession.getMapper(MemberDAOMapper.class).updateMemberPasswordAndStatusCd(memberDTO);
    }

    public MemberDTO getMemberNoAndStatusCode(MemberMailDTO memberMailDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).getMemberNoAndStatusCd(memberMailDTO);
    }

    public int getAuthEmailCnt(MemberMailDTO memberMailDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).getAuthEmailCnt(memberMailDTO);
    }

    public void updateEmailInfo(MemberMailDTO memberMailDTO) {
        sqlSession.getMapper(MemberDAOMapper.class).updateEmailInfo(memberMailDTO);
    }

    public int updateMemberPassword(UpdatePasswordDTO updatePasswordDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).updateMemberPassword(updatePasswordDTO);
    }

    public int updateWithdrawMemberInfo(int memberNo) {
        return sqlSession.getMapper(MemberDAOMapper.class).updateWithdrawMemberInfo(memberNo);
    }

    public MemberDTO getMemberInfoWithMemberNo(int memberNo) {
        return sqlSession.getMapper(MemberDAOMapper.class).getMemberInfoWithMemberNo(memberNo);
    }

    public MemberDTO getMemberInfoWithMemberNoAndMemberPassword(UpdatePasswordDTO updatePasswordDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).getMemberInfoWithMemberNoAndMemberPassword(updatePasswordDTO);
    }

    public int updateMemberInfo(MypageDTO mypageDTO) {
        return sqlSession.getMapper(MemberDAOMapper.class).updateMemberInfo(mypageDTO);
    }
}
