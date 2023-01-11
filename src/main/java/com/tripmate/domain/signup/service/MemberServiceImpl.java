package com.tripmate.domain.signup.service;

import com.tripmate.domain.signup.dao.MemberDAO;
import com.tripmate.domain.signup.dto.MemberDTO;
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
    public void signUp(MemberDTO memberDTO) {
        memberDAO.insertMemberInfo(memberDTO);
    }
}
