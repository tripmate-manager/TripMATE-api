package com.tripmate.domain.members.dao.mapper;

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
    MemberDTO selectSignInRequestCnt(SignInDTO signInDTO);
    String selectFindId(MemberDTO memberDTO);
}
