package com.tripmate.domain.member.dao.mapper;

import com.tripmate.domain.member.dto.DuplicationCheckDTO;
import com.tripmate.domain.member.dto.MemberDTO;
import com.tripmate.domain.member.dto.MemberMailDTO;
import com.tripmate.domain.member.dto.SignInDTO;

public interface MemberDAOMapper {
    int insertMemberInfo(MemberDTO memberDTO);
    int selectDuplicationCnt(DuplicationCheckDTO duplicationCheckDTO);
    void insertEmailInfo(MemberMailDTO memberMailDTO);
    void updateSignUpEmailConfirm(MemberMailDTO memberMailDTO);
    MemberDTO selectSignInMemberInfo(SignInDTO signInDTO);
}
