package com.tripmate.domain.member.service;

import com.tripmate.domain.member.dao.MemberDAO;
import com.tripmate.domain.member.dto.MemberDTO;
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

    @Override
    public Integer getMemberIdCount(String memberId) {
        return memberDAO.selectMemberIdCount(memberId);
    }
}
