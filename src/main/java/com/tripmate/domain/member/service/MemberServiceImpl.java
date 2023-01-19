package com.tripmate.domain.member.service;

import com.tripmate.domain.member.dto.DuplicationCheckDTO;
import com.tripmate.domain.member.dao.MemberDAO;
import com.tripmate.domain.member.dto.MemberDTO;
import com.tripmate.domain.member.dto.MemberMailDTO;
import com.tripmate.domain.member.dto.SignInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberDAO memberDAO;

    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public int signUp(MemberDTO memberDTO) {
        return memberDAO.insertMemberInfo(memberDTO);
    }

    @Override
    public boolean isDuplicate(DuplicationCheckDTO duplicationCheckDTO) {
        return memberDAO.selectDuplicationCnt(duplicationCheckDTO) < 1;
    }

    @Override
    public void signUpMailConfirm(MemberMailDTO memberMailDTO) {
        memberDAO.updateSignUpEmailConfirm(memberMailDTO);
    }

    @Override
    public MemberDTO signIn(SignInDTO signInDTO) {
        return memberDAO.selectSignInMemberInfo(signInDTO);
    }
}
