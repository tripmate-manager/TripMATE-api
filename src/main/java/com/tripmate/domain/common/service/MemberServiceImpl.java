package com.tripmate.domain.common.service;

import com.tripmate.domain.common.dao.MemberDAO;
import com.tripmate.domain.common.dto.MemberDTO;
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
