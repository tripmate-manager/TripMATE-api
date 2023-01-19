package com.tripmate.domain.member.service;

import com.tripmate.domain.member.dto.DuplicationCheckDTO;
import com.tripmate.domain.member.dto.MemberDTO;
import com.tripmate.domain.member.dto.MemberMailDTO;
import com.tripmate.domain.member.dto.SignInDTO;

public interface MemberService {
    int signUp(MemberDTO memberDTO);
    boolean isDuplicate(DuplicationCheckDTO duplicationCheckDTO);
    void signUpMailConfirm(MemberMailDTO memberMailDTO);
    MemberDTO signIn(SignInDTO signInDTO);
}
