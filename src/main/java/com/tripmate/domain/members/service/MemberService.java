package com.tripmate.domain.members.service;

import com.tripmate.domain.members.dto.ChangePasswordDTO;
import com.tripmate.domain.members.dto.DuplicationCheckDTO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.members.dto.MemberMailDTO;
import com.tripmate.domain.members.dto.SignInDTO;

public interface MemberService {
    int signUp(MemberDTO memberDTO);
    boolean isDuplicate(DuplicationCheckDTO duplicationCheckDTO);
    void signUpMailConfirm(MemberMailDTO memberMailDTO);
    MemberDTO signIn(SignInDTO signInDTO);
    String findId(MemberDTO memberDTO);
    boolean changePassword(ChangePasswordDTO changePasswordDTO);
}