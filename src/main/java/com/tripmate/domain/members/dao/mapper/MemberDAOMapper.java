package com.tripmate.domain.members.dao.mapper;

import com.tripmate.domain.ChangePasswordDTO;
import com.tripmate.domain.members.dto.DuplicationCheckDTO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.members.dto.MemberMailDTO;
import com.tripmate.domain.members.dto.SignInDTO;

public interface MemberDAOMapper {
    int insertMemberInfo(MemberDTO memberDTO);
    int selectDuplicationCnt(DuplicationCheckDTO duplicationCheckDTO);
    void insertEmailInfo(MemberMailDTO memberMailDTO);
    void updateSignUpEmailConfirm(MemberMailDTO memberMailDTO);
    MemberDTO selectSignInMemberInfo(SignInDTO signInDTO);
    void updateSignInRequestCnt(SignInDTO signInDTO);
    void updateSignInMemberStatus(SignInDTO signInDTO);
    MemberDTO selectSignInRequestCnt(SignInDTO signInDTO);
    String selectFindId(MemberDTO memberDTO);
    MemberDTO selectFindPasswordMemberInfo(MemberMailDTO memberMailDTO);
    void updateMemberTemporaryPassword(MemberDTO memberDTO);
    int selectAuthEmailCnt(MemberMailDTO memberMailDTO);
    void updateEmailInfo(MemberMailDTO memberMailDTO);
    boolean updateMemberPassword(ChangePasswordDTO changePasswordDTO);
    boolean updateMemberEmail(MemberDTO memberDTO);
}
